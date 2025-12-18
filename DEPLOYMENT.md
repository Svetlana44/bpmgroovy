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
   - Включите через "Программы и компоненты" → "Включение или отключение компонентов Windows"
   - Установите модули: ASP.NET Core Module, URL Rewrite

4. **Архив BPMSoft**
   - У вас уже есть: `C:\Users\playg\Downloads\BPMSoft_Full_House_1.6.0.190_Net8_PostgreSQL.zip`

## Шаг 1: Развертывание инфраструктуры (PostgreSQL и Redis)

### 1.1. Запуск контейнеров

Откройте PowerShell в корне проекта и выполните:

```powershell
docker-compose up -d
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
docker exec -it bpmsoft-redis redis-cli -a BPMAdmin123! ping
```

### 1.3. Параметры подключения

**PostgreSQL:**
- Host: `localhost` (или `127.0.0.1`)
- Port: `5432`
- Database: `bpmsoft`
- User: `bpmsoft_user`
- Password: `BPMAdmin123!`
- Connection String: `Host=localhost;Port=5432;Database=bpmsoft;Username=bpmsoft_user;Password=BPMAdmin123!`

**Redis:**
- Host: `localhost` (или `127.0.0.1`)
- Port: `6379`
- Password: `BPMAdmin123!`
- Connection String: `localhost:6379,password=BPMAdmin123!`

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
    "DefaultConnection": "Host=localhost;Port=5432;Database=bpmsoft;Username=bpmsoft_user;Password=BPMAdmin123!;Pooling=true;"
  }
}
```

**Параметры подключения к Redis:**
```json
{
  "Redis": {
    "ConnectionString": "localhost:6379,password=BPMAdmin123!",
    "InstanceName": "BPMSoft:"
  }
}
```

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

После первого запуска приложения BPMSoft обычно автоматически создает необходимые таблицы и структуры в базе данных. Если этого не произошло:

1. Проверьте логи приложения
2. Убедитесь, что пользователь `bpmsoft_user` имеет права на создание таблиц
3. При необходимости выполните миграции вручную (если они есть в архиве)

## Шаг 4: Проверка работы

1. Откройте браузер и перейдите по адресу приложения
2. Войдите с учетными данными по умолчанию:
   - Login: `Supervisor`
   - Password: `BPMAdmin123!` (или как указано в документации)

## Управление контейнерами

### Остановка контейнеров
```powershell
docker-compose stop
```

### Запуск контейнеров
```powershell
docker-compose start
```

### Остановка и удаление контейнеров (данные сохранятся в volumes)
```powershell
docker-compose down
```

### Полное удаление контейнеров и данных
```powershell
docker-compose down -v
```

### Просмотр логов
```powershell
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
docker exec bpmsoft-redis redis-cli -a BPMAdmin123! BGSAVE
docker cp bpmsoft-redis:/data/dump.rdb redis_backup_$(Get-Date -Format "yyyyMMdd_HHmmss").rdb
```

**Экспорт всех ключей:**
```powershell
docker exec bpmsoft-redis redis-cli -a BPMAdmin123! --rdb /data/backup.rdb
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
- Проверьте логи: `docker-compose logs`

### Проблема: Приложение не подключается к БД
- Убедитесь, что контейнеры запущены: `docker-compose ps`
- Проверьте строку подключения в конфигурации
- Проверьте, что используется правильный хост (`localhost` или `127.0.0.1`)

### Проблема: Ошибки прав доступа
- Убедитесь, что пользователь БД имеет необходимые права
- Проверьте права на директорию приложения (для IIS)

## Дополнительные настройки

### Изменение паролей

Если нужно изменить пароли, отредактируйте `docker-compose.yml` и пересоздайте контейнеры:

```powershell
docker-compose down -v
docker-compose up -d
```

**Важно:** При изменении паролей обновите конфигурацию приложения BPMSoft!

### Изменение портов

Если порты 5432 или 6379 заняты, измените маппинг портов в `docker-compose.yml`:

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

