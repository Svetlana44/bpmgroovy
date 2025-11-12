# <span style="color: #2563eb;">PropertiesReader</span>
## <span style="color: #7c3aed;">singleton</span>
### <span style="color: #059669;">Автор</span>
<span style="color: rgb(96, 220, 38); font-weight: bold;">Terekhova Svetlana</span>

<span style="color: rgb(37, 156, 235); font-weight: bold;">Класс создан для централизованного управления конфигурацией в тестовом фреймворке.</span>

---

<span style="color: #ea580c; font-weight: bold;">**Версия:**</span> <span style="color: #16a34a; font-weight: bold;">1.0</span>  
<span style="color: #ea580c; font-weight: bold;">**Дата создания:**</span> <span style="color: #16a34a; font-weight: bold;">12/11/2025</span>
## <span style="color: #2563eb;">Описание</span>

<span style="color: #7c3aed; font-weight: bold;"> PropertiesReader</span> — это singleton-класс для чтения properties файлов в Java-проекте. Класс реализует паттерн Singleton с ленивой инициализацией и обеспечивает централизованное управление конфигурацией.

## Основные возможности

- ✅ **Singleton паттерн** — гарантирует единственный экземпляр класса
- ✅ **Ленивая загрузка** — файлы загружаются только при первом обращении
- ✅ **Кэширование** — каждый файл загружается один раз и кэшируется
- ✅ **Thread-safe** — безопасен для использования в многопоточных приложениях
- ✅ **Поддержка нескольких файлов** — можно работать с разными properties файлами
- ✅ **Обратная совместимость** — методы для работы с дефолтным файлом (configs/stands.properties)

## Архитектура

### Паттерн Singleton

Класс использует паттерн Singleton с двойной проверкой (Double-Checked Locking) для обеспечения thread-safety:

```java
private static PropertiesReader instance;
private static final Object lock = new Object();

public static PropertiesReader getInstance() {
    if (instance == null) {
        synchronized (lock) {
            if (instance == null) {
                instance = new PropertiesReader();
            }
        }
    }
    return instance;
}
```

### Кэширование

Все загруженные properties файлы кэшируются в `ConcurrentHashMap` для оптимизации производительности:

```java
private final Map<String, Properties> propertiesCache = new ConcurrentHashMap<>();
```

### Дефолтный файл

По умолчанию используется файл `configs/stands.properties`. Этот файл используется в методах без явного указания пути к файлу.

## Использование

### Базовое использование

#### 1. Получение экземпляра

```java
PropertiesReader reader = PropertiesReader.getInstance();
```

#### 2. Чтение свойств из дефолтного файла

```java
// Получение значения по ключу
String login = reader.getProperty("login");
String password = reader.getProperty("password");
String url = reader.getProperty("url");

// Получение значения с дефолтным значением
String timeout = reader.getProperty("timeout", "30");
```

#### 3. Чтение свойств из конкретного файла

```java
// Получение значения из указанного файла
String value = reader.getPropertyFromFileByKey("configs/database.properties", "db.url");

// С дефолтным значением
String port = reader.getProperty("configs/database.properties", "db.port", "3333");
```

#### 4. Получение всего объекта Properties

```java
// Из дефолтного файла
Properties props = reader.getProperties();

// Из конкретного файла
Properties dbProps = reader.getProperties("configs/database.properties");
```

### Примеры использования в тестах

#### Пример 1: Использование в BaseTests

```java
public class BaseTests {
    protected static final PropertiesReader propertiesReader = PropertiesReader.getInstance();
    
    @BeforeAll
    public static void oneTimeSetUp() {
        String login = propertiesReader.getProperty("login");
        String password = propertiesReader.getProperty("password");
        String url = propertiesReader.getProperty("url");
        // ...
    }
}
```

#### Пример 2: Работа с несколькими файлами

```java
PropertiesReader reader = PropertiesReader.getInstance();

// Чтение из разных файлов
String dbUrl = reader.getProperty("configs/database.properties", "db.url");
String apiKey = reader.getProperty("configs/api.properties", "api.key");
String testData = reader.getProperty("configs/testdata.properties", "test.user");
```

#### Пример 3: Перезагрузка properties (для тестов)

```java
// Перезагрузка файла (полезно при изменении конфигурации в тестах)
reader.reloadProperties("configs/stands.properties");

// Очистка всего кэша
reader.clearCache();
```

## API Reference

### Публичные методы

#### `getInstance()`
Получение единственного экземпляра класса.

```java
public static PropertiesReader getInstance()
```

**Возвращает:** экземпляр `PropertiesReader`

---

#### `getProperty(String key)`
Получение значения свойства по ключу из дефолтного файла.

```java
public String getProperty(String key)
```

**Параметры:**
- `key` — ключ свойства

**Возвращает:** значение свойства или `null`, если ключ не найден

---

#### `getProperty(String key, String defaultValue)`
Получение значения свойства по ключу из дефолтного файла с значением по умолчанию.

```java
public String getProperty(String key, String defaultValue)
```

**Параметры:**
- `key` — ключ свойства
- `defaultValue` — значение по умолчанию, если ключ не найден

**Возвращает:** значение свойства или `defaultValue`

---

#### `getProperty(String propertiesFile, String key)`
Получение значения свойства по ключу из указанного файла.

```java
public String getProperty(String propertiesFile, String key)
```

**Параметры:**
- `propertiesFile` — путь к файлу properties относительно classpath
- `key` — ключ свойства

**Возвращает:** значение свойства или `null`, если ключ не найден

---

#### `getProperty(String propertiesFile, String key, String defaultValue)`
Получение значения свойства по ключу из указанного файла с значением по умолчанию.

```java
public String getProperty(String propertiesFile, String key, String defaultValue)
```

**Параметры:**
- `propertiesFile` — путь к файлу properties относительно classpath
- `key` — ключ свойства
- `defaultValue` — значение по умолчанию, если ключ не найден

**Возвращает:** значение свойства или `defaultValue`

---

#### `getProperties()`
Получение properties из дефолтного файла.

```java
public Properties getProperties()
```

**Возвращает:** объект `Properties` из дефолтного файла (копия для безопасности)

---

#### `getProperties(String propertiesFile)`
Получение properties из указанного файла.

```java
public Properties getProperties(String propertiesFile)
```

**Параметры:**
- `propertiesFile` — путь к файлу properties относительно classpath

**Возвращает:** объект `Properties` (кэшированный, но возвращается копия для безопасности)

---

#### `reloadProperties(String propertiesFile)`
Перезагрузка properties из файла. Удаляет файл из кэша и загружает заново.

```java
public void reloadProperties(String propertiesFile)
```

**Параметры:**
- `propertiesFile` — путь к файлу properties относительно classpath

**Примечание:** Полезно для тестов или динамической конфигурации

---

#### `clearCache()`
Очистка кэша properties.

```java
public void clearCache()
```

**Примечание:** Полезно для тестов, когда нужно перезагрузить все файлы

## Особенности реализации

### Thread-Safety

Класс полностью thread-safe благодаря:
- Использованию `ConcurrentHashMap` для кэширования
- Синхронизации при создании singleton-экземпляра
- Методу `computeIfAbsent`, который атомарно загружает файлы

### Ленивая загрузка

Файлы properties не загружаются при создании singleton-экземпляра. Загрузка происходит только при первом обращении к файлу, что экономит ресурсы.

### Кэширование

Каждый файл загружается только один раз и кэшируется. Последующие обращения к тому же файлу используют кэшированную версию.

### Обработка ошибок

При отсутствии файла или ошибке загрузки выбрасывается `RuntimeException` с информативным сообщением. Все ошибки логируются через SLF4J.

## Миграция со старого кода

### Было (старый подход):

```java
Properties properties = new Properties();
try (InputStream in = UserService.class.getClassLoader()
        .getResourceAsStream("configs/stands.properties")) {
    properties.load(in);
    String login = properties.getProperty("login");
    String password = properties.getProperty("password");
} catch (IOException e) {
    e.printStackTrace();
}
```

### Стало (новый подход):

```java
PropertiesReader reader = PropertiesReader.getInstance();
String login = reader.getProperty("login");
String password = reader.getProperty("password");
```

## Преимущества

1. **Централизация** — все properties загружаются через один класс
2. **Производительность** — кэширование и ленивая загрузка
3. **Безопасность** — thread-safe реализация
4. **Гибкость** — поддержка нескольких файлов
5. **Простота** — простой и понятный API
6. **Обратная совместимость** — старый код продолжает работать

## Лучшие практики

1. **Используйте один экземпляр** — всегда получайте через `getInstance()`, не создавайте новые экземпляры
2. **Используйте дефолтные значения** — для критичных параметров указывайте значения по умолчанию
3. **Очищайте кэш в тестах** — используйте `clearCache()` или `reloadProperties()` при необходимости
4. **Проверяйте null** — методы могут вернуть `null`, если ключ не найден (если не указано дефолтное значение)

## Пример структуры properties файла

```properties
# configs/stands.properties
login=Login
password=Password
url=https://test-stand.example.com
timeout=30
retry.count=3
```

## Зависимости

- **SLF4J** — для логирования (`org.slf4j.Logger`, `org.slf4j.LoggerFactory`)
- **Java 8+** — используется `ConcurrentHashMap` и лямбда-выражения



