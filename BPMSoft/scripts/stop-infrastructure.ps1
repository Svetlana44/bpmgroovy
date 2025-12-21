# Скрипт для остановки инфраструктуры BPMSoft
Write-Host "Остановка инфраструктуры BPMSoft..." -ForegroundColor Yellow

$scriptPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptPath
docker-compose stop

if ($LASTEXITCODE -eq 0) {
    Write-Host "Инфраструктура остановлена." -ForegroundColor Green
    Write-Host "Для полного удаления контейнеров выполните: docker-compose down" -ForegroundColor Cyan
    Write-Host "Для удаления контейнеров и данных: docker-compose down -v" -ForegroundColor Red
} else {
    Write-Host "Ошибка при остановке контейнеров." -ForegroundColor Red
    exit 1
}

