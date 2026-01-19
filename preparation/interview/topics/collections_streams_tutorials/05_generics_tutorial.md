# Туториал 5: Generics (Обобщения) в Java

## Оглавление
1. [Зачем нужны дженерики](#зачем-нужны-дженерики)
2. [Базовый синтаксис](#базовый-синтаксис)
3. [Type Erasure (Стирание типов)](#type-erasure-стирание-типов)
4. [Ограничения (Bounded Types)](#ограничения-bounded-types)
5. [Wildcards (?, extends, super)](#wildcards--extends-super)
6. [PECS: Producer Extends, Consumer Super](#pecs-producer-extends-consumer-super)
7. [Обобщённые методы](#обобщённые-методы)
8. [Ограничения дженериков](#ограничения-дженериков)
9. [Дженерики в автотестировании](#дженерики-в-автотестировании)
10. [Частые вопросы на собеседовании](#частые-вопросы-на-собеседовании)
11. [Практические задачи](#практические-задачи)

---

## Зачем нужны дженерики

### Проблема без дженериков (до Java 5)

```java
// Без дженериков — нет типобезопасности
List list = new ArrayList();
list.add("строка");
list.add(123);  // Компилятор не ругается!

String s = (String) list.get(1);  // ClassCastException в runtime!
```

### Решение с дженериками

```java
// С дженериками — ошибка на этапе компиляции
List<String> list = new ArrayList<>();
list.add("строка");
list.add(123);  // Ошибка компиляции!

String s = list.get(0);  // Каст не нужен
```

### Преимущества дженериков

1. **Типобезопасность на этапе компиляции** — ошибки обнаруживаются раньше
2. **Устранение явных кастов** — код чище и безопаснее
3. **Переиспользование кода** — один класс/метод для разных типов
4. **Самодокументируемый код** — `List<User>` понятнее, чем просто `List`

---

## Базовый синтаксис

### Обобщённые классы

```java
// Простой generic-класс
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}

// Использование
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String s = stringBox.get();

Box<Integer> intBox = new Box<>();
intBox.set(42);
Integer i = intBox.get();
```

### Несколько параметров типа

```java
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
}

// Использование
Pair<String, Integer> pair = new Pair<>("age", 25);
```

### Соглашения по именованию

| Символ | Значение | Пример |
|--------|----------|--------|
| `T` | Type (тип) | `Box<T>` |
| `E` | Element (элемент коллекции) | `List<E>` |
| `K` | Key (ключ) | `Map<K, V>` |
| `V` | Value (значение) | `Map<K, V>` |
| `N` | Number (число) | `Calculator<N>` |
| `R` | Result (результат) | `Function<T, R>` |
| `S, U` | Дополнительные типы | `BiFunction<T, U, R>` |

---

## Type Erasure (Стирание типов)

### Что происходит при компиляции

Дженерики существуют **только на этапе компиляции**. В runtime вся информация о типах стирается.

```java
// Исходный код
public class Box<T> {
    private T content;
    public T get() { return content; }
}

// После компиляции (type erasure)
public class Box {
    private Object content;
    public Object get() { return content; }
}
```

### Доказательство стирания типов

```java
List<String> strings = new ArrayList<>();
List<Integer> integers = new ArrayList<>();

// В runtime оба списка имеют один класс!
System.out.println(strings.getClass() == integers.getClass());  // true
System.out.println(strings.getClass().getName());  // java.util.ArrayList
```

### Последствия type erasure

```java
// Нельзя проверить параметр типа в runtime
if (list instanceof List<String>) { }  // Ошибка компиляции!

// Можно только так
if (list instanceof List<?>) { }  // OK
if (list instanceof List) { }     // OK (raw type warning)
```

---

## Ограничения (Bounded Types)

### Upper Bounded (extends)

```java
// T должен быть Number или его подклассом
public class Calculator<T extends Number> {
    private T value;
    
    public double doubleValue() {
        return value.doubleValue();  // Можем вызывать методы Number
    }
}

Calculator<Integer> intCalc = new Calculator<>();  // OK
Calculator<Double> doubleCalc = new Calculator<>();  // OK
Calculator<String> strCalc = new Calculator<>();   // Ошибка компиляции!
```

### Множественные границы

```java
// T должен наследовать класс и реализовывать интерфейсы
public class Handler<T extends Serializable & Comparable<T>> {
    // ...
}

// Класс должен быть первым, интерфейсы после
public class MyClass<T extends SomeClass & Interface1 & Interface2> {
    // ...
}
```

### Пример: сортировка с ограничением

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}

String maxStr = max("apple", "banana");  // "banana"
Integer maxInt = max(10, 20);  // 20
```

---

## Wildcards (?, extends, super)

### Unbounded Wildcard (?)

```java
// Принимает список любого типа
public void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

printList(Arrays.asList("a", "b"));
printList(Arrays.asList(1, 2, 3));
```

### Upper Bounded Wildcard (? extends)

```java
// Принимает List<Number> или List<подкласс Number>
public double sum(List<? extends Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}

sum(Arrays.asList(1, 2, 3));           // List<Integer> — OK
sum(Arrays.asList(1.5, 2.5));          // List<Double> — OK
sum(Arrays.asList("a", "b"));          // List<String> — Ошибка!
```

**Ограничение**: нельзя добавлять элементы (кроме null):

```java
List<? extends Number> list = new ArrayList<Integer>();
list.add(1);      // Ошибка компиляции!
list.add(null);   // OK (но бесполезно)
Number n = list.get(0);  // OK — читать можно
```

### Lower Bounded Wildcard (? super)

```java
// Принимает List<Integer> или List<родитель Integer>
public void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

addNumbers(new ArrayList<Integer>());  // OK
addNumbers(new ArrayList<Number>());   // OK
addNumbers(new ArrayList<Object>());   // OK
addNumbers(new ArrayList<Double>());   // Ошибка!
```

**Ограничение**: читать можно только как Object:

```java
List<? super Integer> list = new ArrayList<Number>();
list.add(1);           // OK — добавлять можно
Object obj = list.get(0);  // OK — только как Object
Integer i = list.get(0);   // Ошибка компиляции!
```

---

## PECS: Producer Extends, Consumer Super

**Главное правило wildcards:**

| Роль коллекции | Wildcard | Операции |
|----------------|----------|----------|
| **Producer** (даёт данные) | `? extends T` | Только чтение |
| **Consumer** (принимает данные) | `? super T` | Только запись |
| **Оба** | `T` (без wildcard) | Чтение и запись |

### Пример: Collections.copy()

```java
// Сигнатура из JDK
public static <T> void copy(List<? super T> dest, List<? extends T> src) {
    // src — producer (читаем из неё)
    // dest — consumer (пишем в неё)
    for (int i = 0; i < src.size(); i++) {
        dest.set(i, src.get(i));
    }
}

List<Number> dest = new ArrayList<>(Arrays.asList(0, 0, 0));
List<Integer> src = Arrays.asList(1, 2, 3);
Collections.copy(dest, src);  // OK
```

### Мнемоника

```
PECS = Producer Extends, Consumer Super

Если ПОЛУЧАЕШЬ данные из коллекции → extends (producer)
Если КЛАДЁШЬ данные в коллекцию → super (consumer)
```

---

## Обобщённые методы

### Синтаксис

```java
// Параметр типа объявляется ПЕРЕД возвращаемым типом
public <T> T firstElement(List<T> list) {
    return list.isEmpty() ? null : list.get(0);
}

// Вызов — тип выводится автоматически
String s = firstElement(Arrays.asList("a", "b"));
Integer i = firstElement(Arrays.asList(1, 2, 3));

// Явное указание типа (редко нужно)
String s = this.<String>firstElement(Arrays.asList("a", "b"));
```

### Статические обобщённые методы

```java
public class Utils {
    // Статический метод — свой параметр типа
    public static <T> List<T> toList(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
    
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}

List<String> list = Utils.toList("a", "b", "c");
String max = Utils.max("apple", "banana");
```

### Несколько параметров типа

```java
public static <K, V> Map<K, V> mapOf(K key, V value) {
    Map<K, V> map = new HashMap<>();
    map.put(key, value);
    return map;
}

public static <T, R> R transform(T input, Function<T, R> transformer) {
    return transformer.apply(input);
}
```

---

## Ограничения дженериков

### 1. Нельзя создать экземпляр параметра типа

```java
public class Box<T> {
    // T item = new T();  // Ошибка компиляции!
    
    // Решение — передать Class<T> или Supplier<T>
    public T createInstance(Class<T> clazz) throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }
    
    public T createInstance(Supplier<T> supplier) {
        return supplier.get();
    }
}
```

### 2. Нельзя создать массив параметра типа

```java
public class Box<T> {
    // T[] array = new T[10];  // Ошибка компиляции!
    
    // Решение — использовать Object[] или List<T>
    private Object[] array = new Object[10];
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }
}
```

### 3. Нельзя использовать примитивы

```java
List<int> list;     // Ошибка!
List<Integer> list; // OK — используй wrapper-классы
```

### 4. Нельзя создать статические поля параметра типа

```java
public class Box<T> {
    // static T value;  // Ошибка!
    // Потому что T разный для разных Box<String>, Box<Integer>
}
```

### 5. Нельзя использовать instanceof с параметром типа

```java
public <T> void check(Object obj) {
    // if (obj instanceof T) { }  // Ошибка!
    
    // Решение — передать Class<T>
    public <T> boolean check(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj);
    }
}
```

### 6. Нельзя перехватывать или бросать параметр типа

```java
// class MyException<T> extends Exception { }  // Ошибка!

// public <T extends Exception> void method() throws T { }  // Ошибка!
```

---

## Дженерики в автотестировании

### 1. Базовый класс для Page Object

```java
/**
 * Базовый Page Object с fluent-интерфейсом
 * @param <T> конкретный тип страницы для цепочки вызовов
 */
public abstract class BasePage<T extends BasePage<T>> {
    protected WebDriver driver;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }
    
    public T waitForPageLoad() {
        // ожидание загрузки
        return self();
    }
    
    public T takeScreenshot() {
        // скриншот
        return self();
    }
}

// Конкретная страница
public class LoginPage extends BasePage<LoginPage> {
    @FindBy(id = "username")
    private WebElement usernameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public LoginPage enterUsername(String username) {
        usernameInput.sendKeys(username);
        return this;  // fluent
    }
    
    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }
    
    public HomePage clickLogin() {
        // ...
        return new HomePage(driver);
    }
}

// Использование — цепочка вызовов
LoginPage loginPage = new LoginPage(driver)
    .waitForPageLoad()       // из BasePage
    .takeScreenshot()        // из BasePage
    .enterUsername("user")   // из LoginPage
    .enterPassword("pass");  // из LoginPage
```

### 2. Обобщённый API-клиент

```java
/**
 * Универсальный REST-клиент с типизированными ответами
 */
public class ApiClient {
    private final RequestSpecification spec;
    private final ObjectMapper mapper = new ObjectMapper();
    
    public ApiClient(String baseUrl) {
        this.spec = RestAssured.given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON);
    }
    
    /**
     * GET-запрос с десериализацией в конкретный тип
     */
    public <T> T get(String path, Class<T> responseType) {
        return spec.get(path)
            .then()
            .statusCode(200)
            .extract()
            .as(responseType);
    }
    
    /**
     * GET-запрос для списка объектов
     */
    public <T> List<T> getList(String path, Class<T> elementType) {
        String json = spec.get(path)
            .then()
            .statusCode(200)
            .extract()
            .asString();
        
        JavaType listType = mapper.getTypeFactory()
            .constructCollectionType(List.class, elementType);
        return mapper.readValue(json, listType);
    }
    
    /**
     * POST с типизированным телом и ответом
     */
    public <T, R> R post(String path, T body, Class<R> responseType) {
        return spec.body(body)
            .post(path)
            .then()
            .statusCode(201)
            .extract()
            .as(responseType);
    }
    
    /**
     * POST без возвращаемого значения
     */
    public <T> void post(String path, T body) {
        spec.body(body)
            .post(path)
            .then()
            .statusCode(201);
    }
}

// Использование
ApiClient api = new ApiClient("https://api.example.com");

// Один объект
User user = api.get("/users/1", User.class);

// Список объектов
List<User> users = api.getList("/users", User.class);

// POST с типизацией
CreateUserRequest request = new CreateUserRequest("John", "john@test.com");
User created = api.post("/users", request, User.class);
```

### 3. Обобщённый Builder для тестовых данных

```java
/**
 * Абстрактный builder с поддержкой наследования
 */
public abstract class BaseBuilder<T, B extends BaseBuilder<T, B>> {
    
    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }
    
    public abstract T build();
}

/**
 * Builder для User с fluent-интерфейсом
 */
public class UserBuilder extends BaseBuilder<User, UserBuilder> {
    private Long id;
    private String name = "Default User";
    private String email;
    private UserRole role = UserRole.USER;
    private boolean active = true;
    
    public static UserBuilder aUser() {
        return new UserBuilder();
    }
    
    public UserBuilder withId(Long id) {
        this.id = id;
        return self();
    }
    
    public UserBuilder withName(String name) {
        this.name = name;
        return self();
    }
    
    public UserBuilder withEmail(String email) {
        this.email = email;
        return self();
    }
    
    public UserBuilder withRole(UserRole role) {
        this.role = role;
        return self();
    }
    
    public UserBuilder active() {
        this.active = true;
        return self();
    }
    
    public UserBuilder inactive() {
        this.active = false;
        return self();
    }
    
    // Готовые пресеты
    public UserBuilder asAdmin() {
        return withRole(UserRole.ADMIN).withName("Admin User");
    }
    
    public UserBuilder asGuest() {
        return withRole(UserRole.GUEST).withName("Guest");
    }
    
    @Override
    public User build() {
        if (email == null) {
            email = name.toLowerCase().replace(" ", ".") + "@test.com";
        }
        return new User(id, name, email, role, active);
    }
}

// Использование в тестах
User admin = UserBuilder.aUser()
    .asAdmin()
    .withEmail("admin@company.com")
    .build();

User guest = UserBuilder.aUser()
    .asGuest()
    .inactive()
    .build();
```

### 4. Обобщённый Repository для тестов

```java
/**
 * Интерфейс репозитория для тестовых данных
 */
public interface TestDataRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void deleteAll();
}

/**
 * In-memory реализация для юнит-тестов
 */
public class InMemoryRepository<T, ID> implements TestDataRepository<T, ID> {
    private final Map<ID, T> storage = new ConcurrentHashMap<>();
    private final Function<T, ID> idExtractor;
    
    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }
    
    @Override
    public T save(T entity) {
        ID id = idExtractor.apply(entity);
        storage.put(id, entity);
        return entity;
    }
    
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }
    
    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }
    
    @Override
    public void deleteAll() {
        storage.clear();
    }
}

// Использование
TestDataRepository<User, Long> userRepo = 
    new InMemoryRepository<>(User::getId);

userRepo.save(new User(1L, "John"));
Optional<User> found = userRepo.findById(1L);
```

### 5. Обобщённые Assertions

```java
/**
 * Типизированные soft assertions для API-тестов
 */
public class ApiAssertions<T> {
    private final T actual;
    private final List<String> errors = new ArrayList<>();
    
    private ApiAssertions(T actual) {
        this.actual = actual;
    }
    
    public static <T> ApiAssertions<T> assertThat(T actual) {
        return new ApiAssertions<>(actual);
    }
    
    public ApiAssertions<T> isNotNull() {
        if (actual == null) {
            errors.add("Expected non-null value");
        }
        return this;
    }
    
    public <V> ApiAssertions<T> hasFieldEqualTo(
            Function<T, V> extractor, 
            V expected, 
            String fieldName) {
        if (actual != null) {
            V actualValue = extractor.apply(actual);
            if (!Objects.equals(actualValue, expected)) {
                errors.add(String.format(
                    "Field '%s': expected <%s> but was <%s>",
                    fieldName, expected, actualValue
                ));
            }
        }
        return this;
    }
    
    public <V extends Comparable<V>> ApiAssertions<T> hasFieldGreaterThan(
            Function<T, V> extractor,
            V threshold,
            String fieldName) {
        if (actual != null) {
            V actualValue = extractor.apply(actual);
            if (actualValue.compareTo(threshold) <= 0) {
                errors.add(String.format(
                    "Field '%s': expected > %s but was %s",
                    fieldName, threshold, actualValue
                ));
            }
        }
        return this;
    }
    
    public void verify() {
        if (!errors.isEmpty()) {
            throw new AssertionError(
                "Assertion failures:\n" + String.join("\n", errors)
            );
        }
    }
}

// Использование
User user = api.get("/users/1", User.class);

ApiAssertions.assertThat(user)
    .isNotNull()
    .hasFieldEqualTo(User::getName, "John", "name")
    .hasFieldEqualTo(User::getRole, UserRole.ADMIN, "role")
    .hasFieldGreaterThan(User::getAge, 18, "age")
    .verify();
```

### 6. Обобщённый Retry-механизм

```java
/**
 * Утилита для повторных попыток с типизированным результатом
 */
public class Retry {
    
    public static <T> T execute(
            Supplier<T> action,
            Predicate<T> successCondition,
            int maxAttempts,
            Duration delay) {
        
        T result = null;
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                result = action.get();
                if (successCondition.test(result)) {
                    return result;
                }
            } catch (Exception e) {
                lastException = e;
            }
            
            if (attempt < maxAttempts) {
                sleep(delay);
            }
        }
        
        if (lastException != null) {
            throw new RuntimeException(
                "Failed after " + maxAttempts + " attempts", lastException);
        }
        
        return result;
    }
    
    public static <T> T untilNotNull(
            Supplier<T> action,
            int maxAttempts,
            Duration delay) {
        return execute(action, Objects::nonNull, maxAttempts, delay);
    }
    
    private static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Использование в тестах
// Ждём, пока статус заказа станет COMPLETED
Order order = Retry.execute(
    () -> api.get("/orders/" + orderId, Order.class),
    o -> o.getStatus() == OrderStatus.COMPLETED,
    10,
    Duration.ofSeconds(2)
);

// Ждём, пока элемент появится
WebElement element = Retry.untilNotNull(
    () -> driver.findElement(By.id("dynamic-element")),
    5,
    Duration.ofMillis(500)
);
```

### 7. Обобщённый Data Provider (TestNG)

```java
/**
 * Загрузчик тестовых данных из JSON
 */
public class TestDataLoader {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Загрузка массива объектов для DataProvider
     */
    public static <T> Object[][] loadTestData(
            String resourcePath, 
            Class<T> dataType) {
        try {
            InputStream is = TestDataLoader.class
                .getResourceAsStream(resourcePath);
            
            List<T> data = mapper.readValue(is, 
                mapper.getTypeFactory()
                    .constructCollectionType(List.class, dataType));
            
            return data.stream()
                .map(item -> new Object[]{item})
                .toArray(Object[][]::new);
                
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }
    
    /**
     * Загрузка данных с фильтрацией
     */
    public static <T> Object[][] loadTestData(
            String resourcePath,
            Class<T> dataType,
            Predicate<T> filter) {
        try {
            InputStream is = TestDataLoader.class
                .getResourceAsStream(resourcePath);
            
            List<T> data = mapper.readValue(is,
                mapper.getTypeFactory()
                    .constructCollectionType(List.class, dataType));
            
            return data.stream()
                .filter(filter)
                .map(item -> new Object[]{item})
                .toArray(Object[][]::new);
                
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }
}

// Использование
public class UserApiTest {
    
    @DataProvider(name = "users")
    public Object[][] userData() {
        return TestDataLoader.loadTestData(
            "/testdata/users.json", 
            UserTestCase.class
        );
    }
    
    @DataProvider(name = "activeUsers")
    public Object[][] activeUserData() {
        return TestDataLoader.loadTestData(
            "/testdata/users.json",
            UserTestCase.class,
            UserTestCase::isActive  // фильтр
        );
    }
    
    @Test(dataProvider = "users")
    public void testUserCreation(UserTestCase testCase) {
        // тест с данными из JSON
    }
}
```

### 8. Обобщённый Response Wrapper

```java
/**
 * Обёртка для API-ответов с валидацией
 */
public class ApiResponse<T> {
    private final Response rawResponse;
    private final Class<T> bodyType;
    private T body;
    
    private ApiResponse(Response response, Class<T> bodyType) {
        this.rawResponse = response;
        this.bodyType = bodyType;
    }
    
    public static <T> ApiResponse<T> from(Response response, Class<T> bodyType) {
        return new ApiResponse<>(response, bodyType);
    }
    
    public ApiResponse<T> expectStatus(int status) {
        int actual = rawResponse.getStatusCode();
        if (actual != status) {
            throw new AssertionError(
                String.format("Expected status %d but was %d. Body: %s",
                    status, actual, rawResponse.asString())
            );
        }
        return this;
    }
    
    public ApiResponse<T> expectHeader(String name, String value) {
        String actual = rawResponse.getHeader(name);
        if (!Objects.equals(actual, value)) {
            throw new AssertionError(
                String.format("Header '%s': expected '%s' but was '%s'",
                    name, value, actual)
            );
        }
        return this;
    }
    
    public T getBody() {
        if (body == null) {
            body = rawResponse.as(bodyType);
        }
        return body;
    }
    
    public ApiResponse<T> validateBody(Consumer<T> validator) {
        validator.accept(getBody());
        return this;
    }
    
    public <R> ApiResponse<R> map(Function<T, R> mapper, Class<R> newType) {
        R mapped = mapper.apply(getBody());
        // Создаём новый response с mapped телом
        return new ApiResponse<R>(rawResponse, newType) {
            @Override
            public R getBody() {
                return mapped;
            }
        };
    }
}

// Использование
User user = ApiResponse.from(
        given().get("/users/1"), 
        User.class
    )
    .expectStatus(200)
    .expectHeader("Content-Type", "application/json")
    .validateBody(u -> {
        assertThat(u.getName()).isNotBlank();
        assertThat(u.getEmail()).contains("@");
    })
    .getBody();
```

---

## Частые вопросы на собеседовании

### Базовые вопросы

1. **Что такое дженерики и зачем они нужны?**
   - Типобезопасность, отсутствие кастов, переиспользование кода

2. **Что такое Type Erasure?**
   - Стирание информации о типах при компиляции, в runtime остаётся Object

3. **В чём разница между `List<Object>` и `List<?>`?**
   - `List<Object>` — можно добавлять Object
   - `List<?>` — нельзя ничего добавлять (кроме null), только читать как Object

4. **Можно ли использовать примитивы в дженериках?**
   - Нет, только wrapper-классы (Integer вместо int)

5. **Что такое raw type?**
   - Использование generic-класса без параметра типа: `List` вместо `List<String>`

### Средний уровень

6. **Объясните PECS**
   - Producer Extends, Consumer Super
   - Читаем из `? extends`, пишем в `? super`

7. **В чём разница между `<T>` и `<?>`?**
   - `<T>` — объявление параметра типа, можно использовать T внутри метода
   - `<?>` — wildcard, неизвестный тип, нельзя использовать как тип

8. **Можно ли создать массив дженерик-типа?**
   - `new T[]` — нельзя
   - `new ArrayList<String>[]` — нельзя (generic array creation)
   - Решение: использовать List или Object[]

9. **Как получить Class<T> в runtime?**
   - Передать как параметр: `void method(Class<T> clazz)`
   - Сохранить в поле при создании объекта

10. **Что такое bounded wildcards?**
    - `? extends T` — верхняя граница (подтипы T)
    - `? super T` — нижняя граница (супертипы T)

### Продвинутый уровень

11. **Почему нельзя сделать `new T()`?**
    - Type erasure: в runtime T превращается в Object
    - Компилятор не знает, какой конструктор вызывать

12. **Как работает bridge method?**
    - Генерируется компилятором для сохранения полиморфизма после erasure
    ```java
    class Node<T> {
        T data;
        void setData(T data) { this.data = data; }
    }
    class IntNode extends Node<Integer> {
        @Override
        void setData(Integer data) { super.setData(data); }
        // Компилятор добавляет bridge method:
        // void setData(Object data) { setData((Integer) data); }
    }
    ```

13. **Что такое reifiable type?**
    - Тип, информация о котором полностью доступна в runtime
    - Примитивы, non-generic классы, raw types, unbounded wildcards (`List<?>`)
    - `List<String>` — NOT reifiable (из-за erasure)

14. **Почему `List<String>` не является подтипом `List<Object>`?**
    - Потому что можно было бы добавить Integer в List<String>:
    ```java
    List<String> strings = new ArrayList<>();
    List<Object> objects = strings;  // Если бы это было разрешено...
    objects.add(123);  // ...могли бы добавить Integer
    String s = strings.get(0);  // ClassCastException!
    ```

15. **Как реализовать generic singleton?**
    ```java
    public class GenericSingleton<T> {
        private static final GenericSingleton<?> INSTANCE = 
            new GenericSingleton<>();
        
        private GenericSingleton() {}
        
        @SuppressWarnings("unchecked")
        public static <T> GenericSingleton<T> getInstance() {
            return (GenericSingleton<T>) INSTANCE;
        }
    }
    ```

---

## Практические задачи

### Задача 1: Обобщённый стек

```java
/**
 * Реализуй generic Stack с методами push, pop, peek, isEmpty
 */
public class GenericStack<E> {
    // TODO: реализация
}

// Тест
GenericStack<String> stack = new GenericStack<>();
stack.push("a");
stack.push("b");
assertEquals("b", stack.pop());
assertEquals("a", stack.peek());
assertFalse(stack.isEmpty());
```

<details>
<summary>Решение</summary>

```java
public class GenericStack<E> {
    private final List<E> elements = new ArrayList<>();
    
    public void push(E element) {
        elements.add(element);
    }
    
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }
    
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public int size() {
        return elements.size();
    }
}
```
</details>

---

### Задача 2: Метод swap для списка

```java
/**
 * Напиши метод, который меняет местами два элемента в списке
 */
public static <???> void swap(List<???> list, int i, int j) {
    // TODO
}

// Должно работать:
List<String> strings = Arrays.asList("a", "b", "c");
swap(strings, 0, 2);  // ["c", "b", "a"]
```

<details>
<summary>Решение</summary>

```java
public static <T> void swap(List<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
}

// Альтернатива с wildcard (helper method pattern)
public static void swap(List<?> list, int i, int j) {
    swapHelper(list, i, j);
}

private static <T> void swapHelper(List<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
}
```
</details>

---

### Задача 3: Копирование с фильтрацией

```java
/**
 * Напиши метод, который копирует элементы из src в dest,
 * применяя фильтр. Используй PECS.
 */
public static <T> void copyIf(
        List<???> src,      // откуда читаем
        List<???> dest,     // куда пишем
        Predicate<???> filter) {
    // TODO
}

// Должно работать:
List<Number> numbers = new ArrayList<>();
List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
copyIf(integers, numbers, n -> n % 2 == 0);  // [2, 4]
```

<details>
<summary>Решение</summary>

```java
public static <T> void copyIf(
        List<? extends T> src,      // producer — extends
        List<? super T> dest,       // consumer — super
        Predicate<? super T> filter) {
    for (T item : src) {
        if (filter.test(item)) {
            dest.add(item);
        }
    }
}
```
</details>

---

### Задача 4: Обобщённый кэш для автотестов

```java
/**
 * Реализуй generic кэш с TTL (time-to-live) для тестов
 * Используется для кэширования токенов, тестовых данных и т.д.
 */
public class TestCache<K, V> {
    // TODO: реализация
    
    V get(K key);
    void put(K key, V value, Duration ttl);
    void invalidate(K key);
    void invalidateAll();
}

// Пример использования:
TestCache<String, String> tokenCache = new TestCache<>();
tokenCache.put("user1", "token123", Duration.ofMinutes(5));
String token = tokenCache.get("user1");  // "token123" или null если истёк
```

<details>
<summary>Решение</summary>

```java
public class TestCache<K, V> {
    private final Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    
    private static class CacheEntry<V> {
        final V value;
        final Instant expiresAt;
        
        CacheEntry(V value, Duration ttl) {
            this.value = value;
            this.expiresAt = Instant.now().plus(ttl);
        }
        
        boolean isExpired() {
            return Instant.now().isAfter(expiresAt);
        }
    }
    
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.value;
    }
    
    public void put(K key, V value, Duration ttl) {
        cache.put(key, new CacheEntry<>(value, ttl));
    }
    
    public V computeIfAbsent(K key, Supplier<V> supplier, Duration ttl) {
        V existing = get(key);
        if (existing != null) {
            return existing;
        }
        V value = supplier.get();
        put(key, value, ttl);
        return value;
    }
    
    public void invalidate(K key) {
        cache.remove(key);
    }
    
    public void invalidateAll() {
        cache.clear();
    }
}
```
</details>

---

### Задача 5: Page Object Factory

```java
/**
 * Реализуй фабрику для создания Page Object'ов с автоинициализацией
 */
public class PageFactory {
    // TODO: реализация
    
    public static <T extends BasePage<T>> T create(Class<T> pageClass, WebDriver driver);
}

// Использование:
LoginPage loginPage = PageFactory.create(LoginPage.class, driver);
```

<details>
<summary>Решение</summary>

```java
public class PageFactory {
    
    public static <T extends BasePage<T>> T create(
            Class<T> pageClass, 
            WebDriver driver) {
        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            T page = constructor.newInstance(driver);
            org.openqa.selenium.support.PageFactory.initElements(driver, page);
            return page;
        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to create page: " + pageClass.getName(), e);
        }
    }
    
    // Версия с ожиданием загрузки
    public static <T extends BasePage<T>> T createAndWait(
            Class<T> pageClass,
            WebDriver driver,
            Duration timeout) {
        T page = create(pageClass, driver);
        new WebDriverWait(driver, timeout)
            .until(d -> page.isLoaded());
        return page;
    }
}
```
</details>

---

### Задача 6: Универсальный компаратор для сортировки

```java
/**
 * Реализуй builder для создания компараторов.
 * Должен поддерживать цепочку полей и направление сортировки.
 */
public class ComparatorBuilder<T> {
    // TODO: реализация
    
    public static <T> ComparatorBuilder<T> comparing(Function<T, ? extends Comparable<?>> extractor);
    public ComparatorBuilder<T> thenComparing(Function<T, ? extends Comparable<?>> extractor);
    public ComparatorBuilder<T> reversed();
    public Comparator<T> build();
}

// Использование:
Comparator<User> comparator = ComparatorBuilder.<User>comparing(User::getLastName)
    .thenComparing(User::getFirstName)
    .thenComparing(User::getAge)
    .reversed()
    .build();

users.sort(comparator);
```

<details>
<summary>Решение</summary>

```java
public class ComparatorBuilder<T> {
    private Comparator<T> comparator;
    
    private ComparatorBuilder(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> ComparatorBuilder<T> comparing(
            Function<T, ? extends Comparable<?>> extractor) {
        Comparator<T> comp = (a, b) -> {
            Comparable<Object> v1 = (Comparable<Object>) extractor.apply(a);
            Comparable<Object> v2 = (Comparable<Object>) extractor.apply(b);
            if (v1 == null && v2 == null) return 0;
            if (v1 == null) return -1;
            if (v2 == null) return 1;
            return v1.compareTo(v2);
        };
        return new ComparatorBuilder<>(comp);
    }
    
    @SuppressWarnings("unchecked")
    public ComparatorBuilder<T> thenComparing(
            Function<T, ? extends Comparable<?>> extractor) {
        Comparator<T> next = (a, b) -> {
            Comparable<Object> v1 = (Comparable<Object>) extractor.apply(a);
            Comparable<Object> v2 = (Comparable<Object>) extractor.apply(b);
            if (v1 == null && v2 == null) return 0;
            if (v1 == null) return -1;
            if (v2 == null) return 1;
            return v1.compareTo(v2);
        };
        this.comparator = this.comparator.thenComparing(next);
        return this;
    }
    
    public ComparatorBuilder<T> reversed() {
        this.comparator = this.comparator.reversed();
        return this;
    }
    
    public Comparator<T> build() {
        return comparator;
    }
}

// Примечание: в реальности проще использовать
// Comparator.comparing(User::getLastName)
//           .thenComparing(User::getFirstName)
//           .reversed()
```
</details>

---

### Задача 7: Обобщённый Assertion Matcher

```java
/**
 * Реализуй fluent assertion для коллекций в стиле AssertJ
 */
public class CollectionAssert<E> {
    // TODO: реализация
    
    public static <E> CollectionAssert<E> assertThat(Collection<E> actual);
    public CollectionAssert<E> hasSize(int expected);
    public CollectionAssert<E> contains(E element);
    public CollectionAssert<E> containsAll(Collection<E> elements);
    public CollectionAssert<E> doesNotContain(E element);
    public CollectionAssert<E> allMatch(Predicate<E> predicate);
    public CollectionAssert<E> anyMatch(Predicate<E> predicate);
}

// Использование:
List<User> users = api.getUsers();

CollectionAssert.assertThat(users)
    .hasSize(3)
    .allMatch(u -> u.isActive())
    .anyMatch(u -> u.getRole() == Role.ADMIN);
```

<details>
<summary>Решение</summary>

```java
public class CollectionAssert<E> {
    private final Collection<E> actual;
    
    private CollectionAssert(Collection<E> actual) {
        this.actual = actual;
    }
    
    public static <E> CollectionAssert<E> assertThat(Collection<E> actual) {
        return new CollectionAssert<>(actual);
    }
    
    public CollectionAssert<E> isNotNull() {
        if (actual == null) {
            throw new AssertionError("Expected non-null collection");
        }
        return this;
    }
    
    public CollectionAssert<E> isEmpty() {
        if (!actual.isEmpty()) {
            throw new AssertionError(
                "Expected empty collection but had " + actual.size() + " elements");
        }
        return this;
    }
    
    public CollectionAssert<E> isNotEmpty() {
        if (actual.isEmpty()) {
            throw new AssertionError("Expected non-empty collection");
        }
        return this;
    }
    
    public CollectionAssert<E> hasSize(int expected) {
        if (actual.size() != expected) {
            throw new AssertionError(
                String.format("Expected size %d but was %d", expected, actual.size()));
        }
        return this;
    }
    
    public CollectionAssert<E> contains(E element) {
        if (!actual.contains(element)) {
            throw new AssertionError(
                "Expected collection to contain " + element);
        }
        return this;
    }
    
    public CollectionAssert<E> containsAll(Collection<E> elements) {
        for (E element : elements) {
            if (!actual.contains(element)) {
                throw new AssertionError(
                    "Expected collection to contain " + element);
            }
        }
        return this;
    }
    
    public CollectionAssert<E> doesNotContain(E element) {
        if (actual.contains(element)) {
            throw new AssertionError(
                "Expected collection NOT to contain " + element);
        }
        return this;
    }
    
    public CollectionAssert<E> allMatch(Predicate<E> predicate) {
        for (E element : actual) {
            if (!predicate.test(element)) {
                throw new AssertionError(
                    "Element " + element + " does not match predicate");
            }
        }
        return this;
    }
    
    public CollectionAssert<E> anyMatch(Predicate<E> predicate) {
        for (E element : actual) {
            if (predicate.test(element)) {
                return this;
            }
        }
        throw new AssertionError("No element matches predicate");
    }
    
    public CollectionAssert<E> noneMatch(Predicate<E> predicate) {
        for (E element : actual) {
            if (predicate.test(element)) {
                throw new AssertionError(
                    "Element " + element + " matches predicate but shouldn't");
            }
        }
        return this;
    }
}
```
</details>

---

## Шпаргалка

```
┌─────────────────────────────────────────────────────────────┐
│                    GENERICS CHEATSHEET                      │
├─────────────────────────────────────────────────────────────┤
│ Объявление класса:    class Box<T> { }                      │
│ Несколько типов:      class Pair<K, V> { }                  │
│ Ограничение:          class Calc<T extends Number> { }      │
│ Несколько границ:     <T extends A & B & C>                 │
├─────────────────────────────────────────────────────────────┤
│ Метод:                <T> T method(T arg) { }               │
│ Статический метод:    static <T> T method(T arg) { }        │
├─────────────────────────────────────────────────────────────┤
│ Wildcards:                                                  │
│   List<?>              — любой тип (только чтение)          │
│   List<? extends T>    — T или подтип (producer, чтение)    │
│   List<? super T>      — T или супертип (consumer, запись)  │
├─────────────────────────────────────────────────────────────┤
│ PECS: Producer Extends, Consumer Super                      │
│   Читаем из коллекции  → ? extends                          │
│   Пишем в коллекцию    → ? super                            │
├─────────────────────────────────────────────────────────────┤
│ НЕЛЬЗЯ:                                                     │
│   × new T()                                                 │
│   × new T[]                                                 │
│   × instanceof T                                            │
│   × static T field                                          │
│   × примитивы (int, long...)                                │
└─────────────────────────────────────────────────────────────┘
```
