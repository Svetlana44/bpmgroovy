# Скрипт для запуска инфраструктуры BPMSoft (PostgreSQL и Redis)
Write-Host "Запуск инфраструктуры BPMSoft..." -ForegroundColor Green

# Проверка наличия Docker
if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
    Write-Host "Ошибка: Docker не установлен или не доступен в PATH" -ForegroundColor Red
    Write-Host "Установите Docker Desktop для Windows: https://www.docker.com/products/docker-desktop/" -ForegroundColor Yellow
    exit 1
}

# Проверка, запущен ли Docker
try {
    docker info | Out-Null
} catch {
    Write-Host "Ошибка: Docker не запущен. Запустите Docker Desktop и повторите попытку." -ForegroundColor Red
    exit 1
}

# Запуск контейнеров
Write-Host "Запуск контейнеров PostgreSQL и Redis..." -ForegroundColor Cyan
docker-compose up -d

if ($LASTEXITCODE -eq 0) {
    Write-Host "`nИнфраструктура успешно запущена!" -ForegroundColor Green
    Write-Host "`nПараметры подключения:" -ForegroundColor Yellow
    Write-Host "PostgreSQL: localhost:5432, база: bpmsoft, пользователь: bpmsoft_user, пароль: BPMAdmin123!" -ForegroundColor White
    Write-Host "Redis: localhost:6379, пароль: BPMAdmin123!" -ForegroundColor White
    Write-Host "`nПроверка статуса: docker-compose ps" -ForegroundColor Cyan
    Write-Host "Просмотр логов: docker-compose logs -f" -ForegroundColor Cyan
    Write-Host "Остановка: docker-compose stop" -ForegroundColor Cyan
} else {
    Write-Host "Ошибка при запуске контейнеров. Проверьте логи: docker-compose logs" -ForegroundColor Red
    exit 1
}

