# Page Object Model и паттерны

## Что такое POM

Паттерн, где каждая страница представлена классом, инкапсулирующим элементы
и действия. Это снижает дублирование и улучшает поддержку.

```java
class LoginPage {
  void login(String user, String pass) {
    // fill + click
  }
}
```

## Другие паттерны в автотестах

- **Factory** — создание драйверов и клиентов
- **Builder (Строитель)** — сборка тестовых данных
- **Strategy** — разные стратегии авторизации/запуска
- **Singleton** — менеджер конфигурации (использовать осторожно)

## Короткие примеры

**Factory**
```java
class DriverFactory {
  static WebDriver create(String browser) { return new ChromeDriver(); }
}
```

**Builder (Строитель)**
```java
User user = User.builder().name("qa").pass("123").build();
```

**Strategy**
```java
interface Auth { void apply(); }
class TokenAuth implements Auth { public void apply() {} }
```

**Singleton**
```java
class Config {
  private static final Config INSTANCE = new Config();
  static Config get() { return INSTANCE; }
}
```

## Примеры в проекте (как реализовано)

- Builder/Factory best practices: `src/test/java/aPatterns/BuilderAndFactoryPatternBestPractices.md`
- Builder best practices: `src/test/java/aPatterns/BuilderPatternBestPractices.md`
