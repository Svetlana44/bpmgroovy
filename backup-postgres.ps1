# Скрипт для создания бэкапа PostgreSQL
param(
    [string]$Format = "sql",
    [switch]$SchemaOnly,
    [switch]$DataOnly
)

$timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
$backupDir = "backups"
$containerName = "bpmsoft-postgres"
$dbName = "bpmsoft"
$dbUser = "bpmsoft_user"

# Создать директорию для бэкапов, если не существует
if (-not (Test-Path $backupDir)) {
    New-Item -ItemType Directory -Path $backupDir | Out-Null
    Write-Host "Создана директория для бэкапов: $backupDir" -ForegroundColor Green
}

# Проверить, что контейнер запущен
$containerStatus = docker ps --filter "name=$containerName" --format "{{.Status}}"
if (-not $containerStatus) {
    Write-Host "Ошибка: Контейнер $containerName не запущен!" -ForegroundColor Red
    Write-Host "Запустите контейнеры: docker-compose up -d" -ForegroundColor Yellow
    exit 1
}

Write-Host "Создание бэкапа базы данных $dbName..." -ForegroundColor Cyan

$options = ""
if ($SchemaOnly) {
    $options = "--schema-only"
    $filename = "$backupDir\backup_schema_$timestamp.sql"
    Write-Host "Режим: только схема (без данных)" -ForegroundColor Yellow
} elseif ($DataOnly) {
    $options = "--data-only"
    $filename = "$backupDir\backup_data_$timestamp.sql"
    Write-Host "Режим: только данные (без схемы)" -ForegroundColor Yellow
} else {
    if ($Format -eq "dump") {
        $options = "-F c -f /tmp/backup.dump"
        $filename = "$backupDir\backup_$timestamp.dump"
        Write-Host "Формат: сжатый dump" -ForegroundColor Yellow
    } else {
        $filename = "$backupDir\backup_$timestamp.sql"
        Write-Host "Формат: SQL" -ForegroundColor Yellow
    }
}

if ($Format -eq "dump") {
    # Создать сжатый бэкап
    docker exec $containerName pg_dump -U $dbUser -d $dbName $options
    
    if ($LASTEXITCODE -eq 0) {
        docker cp "$containerName`:/tmp/backup.dump" $filename
        if ($LASTEXITCODE -eq 0) {
            Write-Host "`nБэкап успешно создан: $filename" -ForegroundColor Green
            $fileSize = (Get-Item $filename).Length / 1MB
            Write-Host "Размер файла: $([math]::Round($fileSize, 2)) MB" -ForegroundColor Cyan
        } else {
            Write-Host "Ошибка при копировании файла из контейнера" -ForegroundColor Red
            exit 1
        }
    } else {
        Write-Host "Ошибка при создании бэкапа" -ForegroundColor Red
        exit 1
    }
} else {
    # Создать SQL бэкап
    docker exec $containerName pg_dump -U $dbUser -d $dbName $options | Out-File -FilePath $filename -Encoding UTF8
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`nБэкап успешно создан: $filename" -ForegroundColor Green
        $fileSize = (Get-Item $filename).Length / 1MB
        Write-Host "Размер файла: $([math]::Round($fileSize, 2)) MB" -ForegroundColor Cyan
    } else {
        Write-Host "Ошибка при создании бэкапа" -ForegroundColor Red
        exit 1
    }
}

Write-Host "`nДля восстановления используйте:" -ForegroundColor Yellow
if ($Format -eq "dump") {
    Write-Host "docker cp $filename bpmsoft-postgres:/tmp/backup.dump" -ForegroundColor White
    Write-Host "docker exec bpmsoft-postgres pg_restore -U $dbUser -d $dbName -c /tmp/backup.dump" -ForegroundColor White
} else {
    Write-Host "Get-Content $filename | docker exec -i $containerName psql -U $dbUser -d $dbName" -ForegroundColor White
}

