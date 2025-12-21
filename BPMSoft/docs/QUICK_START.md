# Быстрый старт BPMSoft

## Что уже сделано

✅ Создан `scripts/docker-compose.yml` для PostgreSQL и Redis  
✅ Созданы скрипты управления в папке `scripts/`  
✅ Создана подробная инструкция (`DEPLOYMENT.md`)

## Что нужно сделать вам

### 1. Установить Docker Desktop (если еще не установлен)
- Скачайте с [официального сайта](https://www.docker.com/products/docker-desktop/)
- Установите и запустите Docker Desktop
- Убедитесь, что включена поддержка Linux контейнеров (в современных версиях используется WSL2)
- Проверьте, что Docker запущен: `docker --version`

### 2. Установить .NET 8 Runtime или SDK (если еще не установлен)
- BPMSoft работает на .NET 8
- Скачайте с [официального сайта Microsoft](https://dotnet.microsoft.com/download/dotnet/8.0)
- Выберите ".NET 8.0 Runtime" (для запуска) или ".NET 8.0 SDK" (для разработки)
- Установите и проверьте: `dotnet --version`

### 3. Запустить инфраструктуру

```powershell
# В PowerShell из папки scripts
cd BPMSoft\scripts
.\start-infrastructure.ps1
```

Или вручную:
```powershell
cd BPMSoft\scripts
docker-compose up -d
```

### 4. Распаковать и настроить BPMSoft

1. Распакуйте архив: `C:\Users\playg\Downloads\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL.zip`
2. Найдите файлы конфигурации (обычно `appsettings.json` или `web.config`)
3. Настройте строки подключения:

**PostgreSQL:**
```
Host=localhost;Port=5432;Database=bpmsoft;Username=bpmsoft_user;Password=ВАШ_ПАРОЛЬ
```

**Redis:**
```
localhost:6379
```

**Примечание:** Redis работает без пароля

### 5. Восстановить базу данных из бэкапа

В архиве BPMSoft есть бэкап базы данных в папке `db`. Необходимо восстановить его перед первым запуском.

**Способ 1: Использовать скрипт (рекомендуется)**

```powershell
.\restore-database.ps1 -BackupPath "C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.backup"
```

**Способ 2: Вручную через docker**

```powershell
# Найти путь к бэкапу (пример)
$backupPath = "C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.backup"

# Скопировать бэкап в контейнер
docker cp $backupPath bpmsoft-postgres:/tmp/bpmsoft.backup

# Восстановить базу данных
docker exec bpmsoft-postgres pg_restore -U bpmsoft_user -d bpmsoft -c /tmp/bpmsoft.backup
```

**Примечание:** Если файл имеет расширение `.sql` вместо `.backup`, скрипт автоматически определит тип и выполнит восстановление через `psql`.

Подробные инструкции см. в `DEPLOYMENT.md`, раздел "Шаг 3: Инициализация базы данных"

### 6. Запустить приложение BPMSoft

**Вариант A: Через Kestrel (проще для разработки)**
```powershell
cd C:\BPMSoft\  # путь к распакованному приложению
dotnet run
# или
.\BPMSoft.Web.exe
```

**Вариант B: Через IIS (для продакшена)**
- **IIS полностью бесплатен** и входит в состав Windows
- Установите IIS (см. `IIS_INSTALLATION.md` для подробной инструкции)
- Установите ASP.NET Core Hosting Bundle для .NET 8
- См. подробную инструкцию в `DEPLOYMENT.md`, раздел "Шаг 2.4"

## Параметры подключения

**PostgreSQL 16:**
- Host: `localhost`
- Port: `5432`
- Database: `bpmsoft`
- User: `bpmsoft_user`
- Password: см. `scripts/docker-compose.yml` (параметр `POSTGRES_PASSWORD`)
- Доступны утилиты: `pg_dump`, `pg_restore`, `psql`, `pg_dumpall`, `pg_basebackup`

**Redis 6:**
- Host: `localhost`
- Port: `6379`
- Password: не требуется (без пароля)

## Полезные команды

```powershell
# Проверить статус контейнеров
docker-compose ps

# Просмотр логов
docker-compose logs -f

# Остановить инфраструктуру
.\stop-infrastructure.ps1
# или
docker-compose stop

# Перезапустить инфраструктуру
docker-compose restart

# Полностью удалить контейнеры (данные сохранятся)
docker-compose down

# Удалить контейнеры и данные
docker-compose down -v

# Создать бэкап PostgreSQL
docker exec bpmsoft-postgres pg_dump -U bpmsoft_user -d bpmsoft > backup_$(Get-Date -Format "yyyyMMdd_HHmmss").sql

# Проверить версию PostgreSQL и доступность утилит
docker exec bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft -c "SELECT version();"
docker exec bpmsoft-postgres pg_dump --version
```

## Рекомендация по выбору способа развертывания

**✅ Рекомендуется: Docker для инфраструктуры + Kestrel для приложения**

Это самый простой и быстрый способ для локальной разработки:
- Не требует настройки IIS
- Легко запустить и остановить
- Изолированные сервисы в контейнерах

**Альтернатива: IIS**

Используйте IIS, если:
- Нужна интеграция с другими Windows-приложениями
- Требуется продакшен-окружение на Windows Server
- Нужны возможности IIS (load balancing, SSL termination и т.д.)

## Что еще может потребоваться

1. **SSL сертификаты** - для HTTPS (можно использовать самоподписанные для разработки)
2. **Настройка брандмауэра** - разрешить порты приложения (5002, 5000, 5001)
3. **Восстановление БД из бэкапа** - необходимо восстановить БД из файла бэкапа в папке `db` архива (см. шаг 5)
4. **Учетные данные по умолчанию** - Login: `Supervisor`, Password: см. документацию приложения

## Подробная документация

См. `DEPLOYMENT.md` для детальной инструкции по развертыванию.

## Устранение неполадок

**Контейнеры не запускаются:**
- Проверьте, что Docker Desktop запущен
- Проверьте, что порты 5432 и 6379 свободны
- Перейдите в папку `BPMSoft\scripts` и смотрите логи: `docker-compose logs`

**Приложение не подключается к БД:**
- Убедитесь, что контейнеры запущены: `cd BPMSoft\scripts; docker-compose ps`
- Проверьте строку подключения в конфигурации
- Проверьте логи приложения

