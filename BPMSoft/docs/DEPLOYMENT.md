# Инструкция по развертыванию BPMSoft локально

## Обзор

Данная инструкция описывает процесс локального развертывания BPMSoft на Windows с использованием Docker для инфраструктурных компонентов (PostgreSQL и Redis).

## Рекомендация по выбору способа развертывания

### ✅ Рекомендуется: Docker контейнеры для инфраструктуры + IIS/Kestrel для приложения

**Преимущества:**
- Изоляция сервисов (PostgreSQL и Redis в контейнерах)
- Простое управление зависимостями
- Легкое обновление и перезапуск
- Не засоряет систему установками
- Кроссплатформенность (можно перенести на Linux)

**Недостатки:**
- Требуется Docker Desktop для Windows

### Альтернатива: Установка через IIS

**Преимущества:**
- Нативная интеграция с Windows
- Знакомый интерфейс для Windows-администраторов

**Недостатки:**
- Сложнее управление зависимостями
- Требует ручной установки PostgreSQL и Redis
- Больше шагов настройки

## Предварительные требования

1. **Docker Desktop для Windows**
   - Скачайте и установите с [официального сайта](https://www.docker.com/products/docker-desktop/)
   - Убедитесь, что включена поддержка Linux контейнеров
   - Проверьте, что Docker запущен: `docker --version`

2. **.NET 8 Runtime или SDK**
   - BPMSoft работает на .NET 8
   - Скачайте с [официального сайта Microsoft](https://dotnet.microsoft.com/download/dotnet/8.0)
   - Проверьте установку: `dotnet --version`

3. **IIS (опционально, для развертывания через IIS)**
   - IIS полностью бесплатен и входит в состав Windows
   - **Подробная инструкция по установке:** см. `IIS_INSTALLATION.md`
   - Кратко: "Программы и компоненты" → "Включение или отключение компонентов Windows" → включите "Службы IIS"
   - После установки IIS установите ASP.NET Core Hosting Bundle для .NET 8

4. **Архив BPMSoft**
   - У вас уже есть: `C:\Users\playg\Downloads\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL.zip`

## Шаг 1: Развертывание инфраструктуры (PostgreSQL и Redis)

### 1.1. Запуск контейнеров

Откройте PowerShell в папке `BPMSoft\scripts` и выполните:

```powershell
cd BPMSoft\scripts
docker-compose up -d
```

Или используйте скрипт:
```powershell
cd BPMSoft\scripts
.\start-infrastructure.ps1
```

Эта команда:
- Создаст сеть `bpmsoft-network`
- Запустит PostgreSQL 16 на порту 5432 (с полным набором утилит: pg_dump, pg_restore, psql и др.)
- Запустит Redis 6 на порту 6379
- Создаст постоянные тома для данных

### 1.2. Проверка работы контейнеров

```powershell
# Проверить статус контейнеров
docker-compose ps

# Проверить логи PostgreSQL
docker logs bpmsoft-postgres

# Проверить логи Redis
docker logs bpmsoft-redis

# Проверить подключение к PostgreSQL и версию
docker exec -it bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft -c "SELECT version();"

# Проверить доступность утилит PostgreSQL
docker exec -it bpmsoft-postgres pg_dump --version
docker exec -it bpmsoft-postgres psql --version

# Проверить подключение к Redis
docker exec -it bpmsoft-redis redis-cli -a ВАШ_ПАРОЛЬ ping
```

### 1.3. Параметры подключения

**PostgreSQL:**
- Host: `localhost` (или `127.0.0.1`)
- Port: `5432`
- Database: `bpmsoft`
- User: `bpmsoft_user`
- Password: см. `scripts/docker-compose.yml` (параметр `POSTGRES_PASSWORD`)
- Connection String: `Host=localhost;Port=5432;Database=bpmsoft;Username=bpmsoft_user;Password=ВАШ_ПАРОЛЬ`

**Redis:**
- Host: `localhost` (или `127.0.0.1`)
- Port: `6379`
- Password: см. `scripts/docker-compose.yml` (если настроен)
- Connection String: `localhost:6379,password=ВАШ_ПАРОЛЬ` (если с паролем) или `localhost:6379` (если без пароля)

## Шаг 2: Развертывание приложения BPMSoft

### 2.1. Распаковка архива

Распакуйте архив `BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL.zip` в удобную директорию, например:
```
C:\BPMSoft\
```

### 2.2. Настройка конфигурации приложения

Найдите файлы конфигурации (обычно `appsettings.json` или `web.config`) и настройте:

**Параметры подключения к PostgreSQL:**
```json
{
  "ConnectionStrings": {
    "DefaultConnection": "Host=localhost;Port=5432;Database=bpmsoft;Username=bpmsoft_user;Password=ВАШ_ПАРОЛЬ;Pooling=true;"
  }
}
```

**Параметры подключения к Redis:**
```json
{
  "Redis": {
    "ConnectionString": "localhost:6379,password=ВАШ_ПАРОЛЬ",
    "InstanceName": "BPMSoft:"
  }
}
```

**Примечание:** Замените `ВАШ_ПАРОЛЬ` на пароль из `scripts/docker-compose.yml`

### 2.3. Вариант A: Запуск через Kestrel (рекомендуется для разработки)

1. Откройте командную строку в директории с распакованным BPMSoft
2. Выполните:
```powershell
dotnet run
```
или если есть готовый исполняемый файл:
```powershell
.\BPMSoft.Web.exe
```

Приложение будет доступно по адресу, указанному в конфигурации (обычно `http://localhost:5000` или `https://localhost:5001`)

### 2.4. Вариант B: Развертывание через IIS

**Важно:** Перед развертыванием убедитесь, что:
- IIS установлен (см. `IIS_INSTALLATION.md` для подробной инструкции)
- Установлен ASP.NET Core Hosting Bundle для .NET 8

1. **Создайте сайт в IIS:**
   - Откройте IIS Manager
   - Правый клик на "Sites" → "Add Website"
   - Site name: `BPMSoft`
   - Physical path: путь к распакованному приложению
   - Binding: выберите порт (например, 5002 для HTTPS или 80 для HTTP)

2. **Настройте Application Pool:**
   - Создайте новый Application Pool
   - .NET CLR Version: "No Managed Code" (для .NET Core/8)
   - Managed Pipeline Mode: Integrated
   - Установите Identity с правами доступа к БД

3. **Установите ASP.NET Core Hosting Bundle:**
   - Скачайте и установите [ASP.NET Core Hosting Bundle для .NET 8](https://dotnet.microsoft.com/download/dotnet/8.0)

4. **Настройте права доступа:**
   - Дайте права Application Pool Identity на директорию приложения
   - Убедитесь, что есть права на запись в папки логов и временных файлов

## Шаг 3: Инициализация базы данных

В архиве BPMSoft находится бэкап базы данных, который необходимо восстановить перед первым запуском приложения.

### 3.1. Найти файл бэкапа

Файл бэкапа обычно находится в папке `db` распакованного архива, например:
```
C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.backup
```

Или в другой директории, куда вы распаковали архив:
```
<путь_к_распакованному_архиву>\db\BPMSoft_Full_House_1.6.0.190.backup
```

### 3.2. Восстановление базы данных из бэкапа

**Вариант A: Восстановление через docker exec (рекомендуется)**

1. Создайте роль `sa`, если её нет (для совместимости с бэкапами BPMSoft):
```powershell
docker exec bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "CREATE ROLE sa WITH SUPERUSER CREATEDB CREATEROLE LOGIN;"
```

2. Скопируйте файл бэкапа в контейнер:
```powershell
docker cp "C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.backup" bpmsoft-postgres:/tmp/bpmsoft.backup
```

3. Восстановите базу данных (используйте `--no-owner` и `--no-privileges` для избежания ошибок с ролью sa):
```powershell
docker exec bpmsoft-postgres pg_restore -U bpmsoft_user -d bpmsoft --no-owner --no-privileges -c /tmp/bpmsoft.backup
```

**Примечание:** Опции `--no-owner` и `--no-privileges` игнорируют команды установки владельцев и прав из бэкапа, что позволяет избежать ошибок с несуществующими ролями. Все объекты будут принадлежать пользователю `bpmsoft_user`.

**Вариант B: Восстановление через psql (если бэкап в SQL формате)**

Если файл имеет расширение `.sql` вместо `.backup`:
```powershell
Get-Content "C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.sql" | docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft
```

**Вариант C: Восстановление с пересозданием базы данных**

Если нужно полностью пересоздать базу данных:
```powershell
# Удалить существующую базу (если есть)
docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "DROP DATABASE IF EXISTS bpmsoft;"

# Создать новую базу
docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "CREATE DATABASE bpmsoft;"

# Создать роль sa (если не существует)
docker exec bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "DO \$\$ BEGIN IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'sa') THEN CREATE ROLE sa WITH SUPERUSER CREATEDB CREATEROLE LOGIN; END IF; END \$\$;"

# Восстановить из бэкапа
docker cp "C:\inetpub\www\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL\db\BPMSoft_Full_House_1.6.0.190.backup" bpmsoft-postgres:/tmp/bpmsoft.backup
docker exec bpmsoft-postgres pg_restore -U bpmsoft_user -d bpmsoft --no-owner --no-privileges -c /tmp/bpmsoft.backup
```

### 3.3. Проверка восстановления

Проверьте, что база данных восстановлена корректно:
```powershell
# Проверить список таблиц
docker exec bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft -c "\dt"

# Проверить количество таблиц
docker exec bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft -c "SELECT count(*) FROM information_schema.tables WHERE table_schema = 'public';"
```

### 3.4. Устранение проблем

**Ошибка: "database does not exist"**
- Убедитесь, что база данных `bpmsoft` создана: `docker exec bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "CREATE DATABASE bpmsoft;"`

**Ошибка: "permission denied"**
- Убедитесь, что пользователь `bpmsoft_user` имеет права на создание объектов в базе данных

**Ошибка: "file not found"**
- Проверьте правильность пути к файлу бэкапа
- Убедитесь, что файл существует и доступен для чтения

**Ошибка: "role 'sa' does not exist"**
- Бэкап BPMSoft содержит ссылки на роль `sa` (system administrator из SQL Server)
- Решение 1: Создайте роль `sa` перед восстановлением:
  ```powershell
  docker exec bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "CREATE ROLE sa WITH SUPERUSER CREATEDB CREATEROLE LOGIN;"
  ```
- Решение 2: Используйте опции `--no-owner` и `--no-privileges` при восстановлении:
  ```powershell
  docker exec bpmsoft-postgres pg_restore -U bpmsoft_user -d bpmsoft --no-owner --no-privileges -c /tmp/bpmsoft.backup
  ```
- Рекомендуется использовать оба решения одновременно

## Шаг 4: Проверка работы

1. Откройте браузер и перейдите по адресу приложения
2. Войдите с учетными данными по умолчанию:
   - Login: `Supervisor`
   - Password: см. `scripts/docker-compose.yml` (параметр `POSTGRES_PASSWORD`)

## Управление контейнерами

### Остановка контейнеров
```powershell
cd BPMSoft\scripts
.\stop-infrastructure.ps1
# или
docker-compose stop
```

### Запуск контейнеров
```powershell
cd BPMSoft\scripts
docker-compose start
```

### Остановка и удаление контейнеров (данные сохранятся в volumes)
```powershell
cd BPMSoft\scripts
docker-compose down
```

### Полное удаление контейнеров и данных
```powershell
cd BPMSoft\scripts
docker-compose down -v
```

### Просмотр логов
```powershell
cd BPMSoft\scripts
docker-compose logs -f
```

## Резервное копирование базы данных

### Доступные утилиты PostgreSQL

В контейнере PostgreSQL 16 доступны все стандартные утилиты:
- `pg_dump` - создание бэкапа базы данных
- `pg_dumpall` - создание бэкапа всех баз данных и ролей
- `pg_restore` - восстановление из бэкапа
- `psql` - интерактивный терминал PostgreSQL
- `pg_basebackup` - создание физического бэкапа

### Создание бэкапа PostgreSQL

**Простой бэкап (SQL формат):**
```powershell
docker exec bpmsoft-postgres pg_dump -U bpmsoft_user -d bpmsoft > backup_$(Get-Date -Format "yyyyMMdd_HHmmss").sql
```

**Бэкап с сжатием:**
```powershell
docker exec bpmsoft-postgres pg_dump -U bpmsoft_user -d bpmsoft -F c -f /tmp/backup.dump
docker cp bpmsoft-postgres:/tmp/backup.dump backup_$(Get-Date -Format "yyyyMMdd_HHmmss").dump
```

**Бэкап только схемы (без данных):**
```powershell
docker exec bpmsoft-postgres pg_dump -U bpmsoft_user -d bpmsoft --schema-only > backup_schema_$(Get-Date -Format "yyyyMMdd_HHmmss").sql
```

**Бэкап только данных (без схемы):**
```powershell
docker exec bpmsoft-postgres pg_dump -U bpmsoft_user -d bpmsoft --data-only > backup_data_$(Get-Date -Format "yyyyMMdd_HHmmss").sql
```

**Бэкап всех баз данных и ролей:**
```powershell
docker exec bpmsoft-postgres pg_dumpall -U bpmsoft_user > backup_all_$(Get-Date -Format "yyyyMMdd_HHmmss").sql
```

### Восстановление из бэкапа

**Восстановление из SQL файла:**
```powershell
Get-Content backup_20240101_120000.sql | docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft
```

**Восстановление из сжатого бэкапа:**
```powershell
docker cp backup_20240101_120000.dump bpmsoft-postgres:/tmp/backup.dump
docker exec bpmsoft-postgres pg_restore -U bpmsoft_user -d bpmsoft -c /tmp/backup.dump
```

**Восстановление с пересозданием базы:**
```powershell
docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "DROP DATABASE IF EXISTS bpmsoft;"
docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d postgres -c "CREATE DATABASE bpmsoft;"
Get-Content backup_20240101_120000.sql | docker exec -i bpmsoft-postgres psql -U bpmsoft_user -d bpmsoft
```

### Резервное копирование Redis

**Создание бэкапа Redis (RDB файл):**
```powershell
docker exec bpmsoft-redis redis-cli -a ВАШ_ПАРОЛЬ BGSAVE
docker cp bpmsoft-redis:/data/dump.rdb redis_backup_$(Get-Date -Format "yyyyMMdd_HHmmss").rdb
```

**Экспорт всех ключей:**
```powershell
docker exec bpmsoft-redis redis-cli -a ВАШ_ПАРОЛЬ --rdb /data/backup.rdb
docker cp bpmsoft-redis:/data/backup.rdb redis_backup_$(Get-Date -Format "yyyyMMdd_HHmmss").rdb
```

**Восстановление Redis из бэкапа:**
```powershell
# Остановить Redis
docker-compose stop redis

# Скопировать бэкап в контейнер
docker cp redis_backup_20240101_120000.rdb bpmsoft-redis:/data/dump.rdb

# Запустить Redis
docker-compose start redis
```

## Устранение неполадок

### Проблема: Контейнеры не запускаются
- Проверьте, что Docker Desktop запущен
- Проверьте, что порты 5432 и 6379 не заняты другими приложениями
- Перейдите в папку `BPMSoft\scripts` и проверьте логи: `docker-compose logs`

### Проблема: Приложение не подключается к БД
- Убедитесь, что контейнеры запущены: `cd BPMSoft\scripts; docker-compose ps`
- Проверьте строку подключения в конфигурации
- Проверьте, что используется правильный хост (`localhost` или `127.0.0.1`)

### Проблема: Ошибки прав доступа
- Убедитесь, что пользователь БД имеет необходимые права
- Проверьте права на директорию приложения (для IIS)

## Дополнительные настройки

### Изменение паролей

Если нужно изменить пароли, отредактируйте `BPMSoft\scripts\docker-compose.yml` и пересоздайте контейнеры:

```powershell
cd BPMSoft\scripts
docker-compose down -v
docker-compose up -d
```

**Важно:** При изменении паролей обновите конфигурацию приложения BPMSoft!

### Изменение портов

Если порты 5432 или 6379 заняты, измените маппинг портов в `BPMSoft\scripts\docker-compose.yml`:

```yaml
ports:
  - "5433:5432"  # Внешний порт:Внутренний порт
```

## Что еще может потребоваться

1. **SSL сертификаты** (для HTTPS)
   - Для разработки можно использовать самоподписанные сертификаты
   - Для продакшена нужны валидные сертификаты

2. **Настройка брандмауэра**
   - Разрешите входящие подключения на порты приложения (5002, 5000, 5001 и т.д.)
   - PostgreSQL и Redis доступны только локально (localhost)

3. **Мониторинг и логирование**
   - Настройте централизованное логирование (если требуется)
   - Настройте мониторинг производительности

4. **Резервное копирование**
   - Настройте автоматическое резервное копирование БД
   - Настройте резервное копирование конфигураций

## Контакты и поддержка

При возникновении проблем:
1. Проверьте логи приложения и контейнеров
2. Обратитесь к официальной документации BPMSoft
3. Проверьте системные требования в `sistemnye-trebovaniya-bpmsoft.pdf`

