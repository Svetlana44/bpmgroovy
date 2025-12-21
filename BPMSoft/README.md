# BPMSoft - Локальное развертывание

Этот каталог содержит все необходимые файлы для локального развертывания BPMSoft.

## Структура каталога

```
BPMSoft/
├── docs/              # Документация и инструкции
├── scripts/           # Скрипты и конфигурационные файлы
└── README.md          # Этот файл
```

## Быстрый старт

1. **Запустите инфраструктуру:** [`scripts/start-infrastructure.ps1`](scripts/start-infrastructure.ps1)
2. **Восстановите базу данных:** [`scripts/restore-database.ps1`](scripts/restore-database.ps1)
3. **Запустите приложение:** см. [`ЗАПУСК_ПРИЛОЖЕНИЯ.md`](ЗАПУСК_ПРИЛОЖЕНИЯ.md)

## Документация

### Основные инструкции

- **[ЗАПУСК_ПРИЛОЖЕНИЯ.md](ЗАПУСК_ПРИЛОЖЕНИЯ.md)** - Инструкция по запуску приложения
- **[QUICK_START.md](docs/QUICK_START.md)** - Быстрый старт для развертывания
- **[DEPLOYMENT.md](docs/DEPLOYMENT.md)** - Подробная инструкция по развертыванию

## Скрипты

### Управление инфраструктурой

- **[start-infrastructure.ps1](scripts/start-infrastructure.ps1)** - Запуск PostgreSQL и Redis
- **[stop-infrastructure.ps1](scripts/stop-infrastructure.ps1)** - Остановка инфраструктуры
- **[docker-compose.yml](scripts/docker-compose.yml)** - Конфигурация Docker для инфраструктуры

### Работа с базой данных

- **[restore-database.ps1](scripts/restore-database.ps1)** - Восстановление БД из бэкапа
- **[backup-postgres.ps1](scripts/backup-postgres.ps1)** - Создание бэкапа PostgreSQL

### Конфигурация

- **[connection-params.example.txt](scripts/connection-params.example.txt)** - Шаблон параметров подключения
- **[connection-params.local.txt](scripts/connection-params.local.txt)** - Локальные параметры подключения (в .gitignore)

## Порядок действий

1. **Установите необходимое ПО:**
   - Docker Desktop
   - .NET 8 Runtime или SDK

2. **Запустите инфраструктуру:**
   ```powershell
   cd scripts
   .\start-infrastructure.ps1
   ```

3. **Восстановите базу данных:**
   ```powershell
   .\restore-database.ps1 -BackupPath "путь\к\бэкапу.backup"
   ```

4. **Запустите приложение:**
   ```powershell
   cd "C:\inetpub\wwwroot\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL"
   dotnet BPMSoft.WebHost.dll
   ```
   
   **Важно:** Приложение запускается в консоли. Не закрывайте окно консоли, пока приложение работает. Для остановки нажмите `Ctrl+C`.

5. **Откройте в браузере:**
   - `http://localhost:5001/Login/Login.html`

## Параметры подключения

Параметры подключения к инфраструктуре находятся в файле:
- [`scripts/connection-params.local.txt`](scripts/connection-params.local.txt)

**Важно:** Этот файл находится в `.gitignore` и не попадет в репозиторий.

**PostgreSQL:**
- Host: `localhost`
- Port: `5432`
- Database: `bpmsoft`
- User: `bpmsoft_user`
- Password: `ваш_пароль` (см. `scripts/docker-compose.yml`)

**Redis:**
- Host: `localhost`
- Port: `6379`
- Database: `1`
- Password: не требуется (без пароля)

## Рабочая конфигурация

Приложение настроено для запуска напрямую через Kestrel в консоли:
- **HTTP**: `http://localhost:5001`
- **HTTPS**: `https://localhost:5003`
- **Запуск**: `dotnet BPMSoft.WebHost.dll` (в консоли PowerShell/CMD)
- **Важно**: Окно консоли должно оставаться открытым, пока приложение работает

Подробная инструкция: [`ЗАПУСК_ПРИЛОЖЕНИЯ.md`](ЗАПУСК_ПРИЛОЖЕНИЯ.md)

## Дополнительная информация

- Все скрипты написаны для PowerShell
- Для работы скриптов может потребоваться запуск от имени администратора
- Docker Desktop должен быть запущен перед использованием скриптов инфраструктуры
