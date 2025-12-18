# Скрипт для создания бэкапа Redis
$timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
$backupDir = "backups"
$containerName = "bpmsoft-redis"
$redisPassword = "BPMAdmin123!"

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

Write-Host "Создание бэкапа Redis..." -ForegroundColor Cyan

# Инициировать сохранение в фоне
docker exec $containerName redis-cli -a $redisPassword BGSAVE

# Подождать завершения сохранения
Write-Host "Ожидание завершения сохранения..." -ForegroundColor Yellow
Start-Sleep -Seconds 2

# Проверить статус сохранения
$saveStatus = docker exec $containerName redis-cli -a $redisPassword LASTSAVE
Write-Host "Последнее сохранение: $saveStatus" -ForegroundColor Cyan

# Скопировать файл дампа
$filename = "$backupDir\redis_backup_$timestamp.rdb"
docker cp "$containerName`:/data/dump.rdb" $filename

if ($LASTEXITCODE -eq 0) {
    Write-Host "`nБэкап успешно создан: $filename" -ForegroundColor Green
    $fileSize = (Get-Item $filename).Length / 1KB
    Write-Host "Размер файла: $([math]::Round($fileSize, 2)) KB" -ForegroundColor Cyan
    
    Write-Host "`nДля восстановления:" -ForegroundColor Yellow
    Write-Host "1. Остановите Redis: docker-compose stop redis" -ForegroundColor White
    Write-Host "2. Скопируйте бэкап: docker cp $filename bpmsoft-redis:/data/dump.rdb" -ForegroundColor White
    Write-Host "3. Запустите Redis: docker-compose start redis" -ForegroundColor White
} else {
    Write-Host "Ошибка при создании бэкапа" -ForegroundColor Red
    exit 1
}

