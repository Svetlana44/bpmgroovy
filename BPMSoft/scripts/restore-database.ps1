# Скрипт для восстановления базы данных BPMSoft из бэкапа
param(
    [Parameter(Mandatory=$true)]
    [string]$BackupPath,
    
    [string]$ContainerName = "bpmsoft-postgres",
    [string]$DbName = "bpmsoft",
    [string]$DbUser = "bpmsoft_user"
)

Write-Host "Восстановление базы данных BPMSoft из бэкапа..." -ForegroundColor Green

# Проверить, что файл существует
if (-not (Test-Path $BackupPath)) {
    Write-Host "Ошибка: Файл бэкапа не найден: $BackupPath" -ForegroundColor Red
    exit 1
}

# Проверить, что контейнер запущен
$containerStatus = docker ps --filter "name=$ContainerName" --format "{{.Status}}"
if (-not $containerStatus) {
    Write-Host "Ошибка: Контейнер $ContainerName не запущен!" -ForegroundColor Red
    Write-Host "Запустите контейнеры: docker-compose up -d" -ForegroundColor Yellow
    exit 1
}

Write-Host "`nФайл бэкапа: $BackupPath" -ForegroundColor Cyan
$fileSize = (Get-Item $BackupPath).Length / 1MB
Write-Host "Размер файла: $([math]::Round($fileSize, 2)) MB" -ForegroundColor Cyan

# Определить тип файла по расширению
$extension = [System.IO.Path]::GetExtension($BackupPath).ToLower()

if ($extension -eq ".sql") {
    Write-Host "`nВосстановление из SQL файла..." -ForegroundColor Yellow
    
    # Проверить, существует ли база данных
    $dbExists = docker exec $ContainerName psql -U $DbUser -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='$DbName'"
    
    if (-not $dbExists) {
        Write-Host "Создание базы данных $DbName..." -ForegroundColor Cyan
        docker exec -i $ContainerName psql -U $DbUser -d postgres -c "CREATE DATABASE $DbName;"
    }
    
    # Восстановить из SQL
    Get-Content $BackupPath | docker exec -i $ContainerName psql -U $DbUser -d $DbName
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`nБаза данных успешно восстановлена из SQL файла!" -ForegroundColor Green
    } else {
        Write-Host "`nОшибка при восстановлении базы данных" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "`nВосстановление из бэкапа PostgreSQL..." -ForegroundColor Yellow
    
    # Скопировать файл в контейнер
    Write-Host "Копирование файла в контейнер..." -ForegroundColor Cyan
    docker cp $BackupPath "$ContainerName`:/tmp/bpmsoft_restore.backup"
    
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Ошибка при копировании файла в контейнер" -ForegroundColor Red
        exit 1
    }
    
    # Проверить, существует ли база данных
    $dbExists = docker exec $ContainerName psql -U $DbUser -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='$DbName'"
    
    if (-not $dbExists) {
        Write-Host "Создание базы данных $DbName..." -ForegroundColor Cyan
        docker exec -i $ContainerName psql -U $DbUser -d postgres -c "CREATE DATABASE $DbName;"
    }
    
    # Создать роль sa, если её нет (для совместимости с бэкапами из SQL Server)
    # Роль sa используется в бэкапах BPMSoft как системный администратор БД
    Write-Host "Создание роли sa (если не существует)..." -ForegroundColor Cyan
    $saExists = docker exec $ContainerName psql -U $DbUser -d postgres -tAc "SELECT 1 FROM pg_catalog.pg_roles WHERE rolname = 'sa';"
    if (-not $saExists) {
        docker exec $ContainerName psql -U $DbUser -d postgres -c "CREATE ROLE sa WITH SUPERUSER CREATEDB CREATEROLE LOGIN;"
        Write-Host "Роль sa создана успешно" -ForegroundColor Green
    } else {
        Write-Host "Роль sa уже существует" -ForegroundColor Cyan
    }
    
    # Восстановить из бэкапа
    # --no-owner: игнорировать команды OWNER TO (все объекты будут принадлежать текущему пользователю)
    # --no-privileges: игнорировать команды GRANT/REVOKE (права будут установлены по умолчанию)
    # -c: очистить базу перед восстановлением
    Write-Host "Восстановление базы данных..." -ForegroundColor Cyan
    docker exec $ContainerName pg_restore -U $DbUser -d $DbName --no-owner --no-privileges -c /tmp/bpmsoft_restore.backup
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`nБаза данных успешно восстановлена!" -ForegroundColor Green
        
        # Удалить временный файл из контейнера
        docker exec $ContainerName rm /tmp/bpmsoft_restore.backup
    } else {
        Write-Host "`nОшибка при восстановлении базы данных" -ForegroundColor Red
        Write-Host "Проверьте логи выше для деталей" -ForegroundColor Yellow
        exit 1
    }
}

# Проверить восстановление
Write-Host "`nПроверка восстановления..." -ForegroundColor Cyan
$tableCount = docker exec $ContainerName psql -U $DbUser -d $DbName -tAc "SELECT count(*) FROM information_schema.tables WHERE table_schema = 'public';"

if ($tableCount -gt 0) {
    Write-Host "База данных восстановлена успешно! Количество таблиц: $tableCount" -ForegroundColor Green
} else {
    Write-Host "Предупреждение: Не найдено таблиц в базе данных" -ForegroundColor Yellow
}

Write-Host "`nДля проверки списка таблиц выполните:" -ForegroundColor Cyan
Write-Host "docker exec $ContainerName psql -U $DbUser -d $DbName -c `"\dt`"" -ForegroundColor White

