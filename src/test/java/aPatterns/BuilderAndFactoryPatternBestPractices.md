# Builder Pattern Best Practices

## Автор

Terekhova Svetlana

---

**Версия:** 1.0  
**Дата создания:** 13/11/2025

## Описание

Документация описывает лучшие практики использования паттерна Builder в тестовом фреймворке, включая улучшения для
классов User и централизацию создания тестовых пользователей.

---

## Содержание

1. [Текущее состояние](#текущее-состояние)
2. [Устранены проблемы]
3. [Рекомендуемые улучшения](#рекомендуемые-улучшения)
4. [Примеры использования](#примеры-использования)

---

## Текущее состояние

### Структура User классов

В проекте существует несколько классов User для разных целей:

1. **`api.rND21689GetZipPackages.models.User`** - для тестов GetZipPackages
2. **`api.rND21624BpmsQueryBuilder.models.User`** - для тестов QueryBuilder
3. **`api.models.AuthUser`** - для аутентификации
4. **`api.models.SysUser`** - системный пользователь

### Текущая реализация Builder

```java

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonProperty("UserName")
    public String name;

    @JsonProperty("UserPassword")
    public String pass;

    public boolean isCanManageSolution;
    public boolean isCanViewConfiguration;
}
```

### Текущее использование

```java
// В BaseTests.java
supervisor1 =User.

builder()
        .

isCanManageSolution(true)
        .

name("Supervisor")
        .

isCanViewConfiguration(true)
        .

pass("password")
        .

build();

svetuser2 =User.

builder()
        .

isCanManageSolution(false)
        .

name("SVETuser")
        .

isCanViewConfiguration(true)
        .

pass("password")
        .

build();
```

---

## Устранены проблемы

### 1. **Дублирование кода**

- Создание пользователей дублируется в `BaseTests`, `UIBaseTests`, `GetZipPackagesAPITests`
- Одинаковые значения паролей и имен захардкожены в разных местах

### 2. **Нарушение принципов SOLID**

- Нарушение **DRY** (Don't Repeat Yourself)
- Нарушение **Single Responsibility** - тестовые классы знают детали создания пользователей

---

## Рекомендуемые улучшения

### 1. Создание TestUserFactory

**Цель:** Централизовать создание тестовых пользователей

**Реализация:**

```java
public class TestUserFactory {
    private static final PropertiesReader propertiesReader = PropertiesReader.getInstance();

    public static User createSupervisor() {
        return User.builder()
                .name(getLoginFromProperties())
                .pass(getPasswordFromProperties())
                .isCanManageSolution(true)
                .isCanViewConfiguration(true)
                .build();
    }

    public static User createSvetUser() {
        return User.builder()
                .name("SVETuser")
                .pass(getPasswordFromProperties())
                .isCanManageSolution(false)
                .isCanViewConfiguration(true)
                .build();
    }
}
```

**Преимущества:**

- ✅ Единая точка создания пользователей
- ✅ Использование значений из properties
- ✅ Легко добавлять новые типы пользователей
- ✅ Улучшенная читаемость тестов

### 2. Улучшение User класса

**Изменения:**

```java

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonProperty("UserName")
    private String name;  // Изменено с public на private

    @JsonProperty("UserPassword")
    private String pass;  // Изменено с public на private

    private boolean isCanManageSolution;
    private boolean isCanViewConfiguration;

    // Добавлена валидация
    public boolean isValid() {
        return name != null && !name.isEmpty()
                && pass != null && !pass.isEmpty();
    }

    // Безопасный toString (без пароля)
    @Override
    public String toString() {
        return String.format("User{name='%s', isCanManageSolution=%s, isCanViewConfiguration=%s}",
                name, isCanManageSolution, isCanViewConfiguration);
    }
}
```

**Улучшения:**

- ✅ Приватные поля (инкапсуляция)
- ✅ Валидация объекта
- ✅ Безопасный toString (без пароля)

### 3. Использование в тестах

**До:**

```java
supervisor1 =User.

builder()
        .

isCanManageSolution(true)
        .

name("Supervisor")
        .

isCanViewConfiguration(true)
        .

pass("password")
        .

build();
```

**После:**

```java
supervisor1 =TestUserFactory.

createSupervisor();

svetuser2 =TestUserFactory.

createSvetUser();
```

**Преимущества:**

- ✅ Код стал короче и читабельнее
- ✅ Нет дублирования
- ✅ Легко изменить значения в одном месте

---

## Примеры использования

### Базовое использование

```java
// Создание стандартных пользователей
User supervisor = TestUserFactory.createSupervisor();
User svetUser = TestUserFactory.createSvetUser();
```

### Кастомные пользователи

```java
// Пользователь с кастомными правами
User customUser = TestUserFactory.createCustomUser(
        "CustomUser",
        true,  // canManageSolution
        false  // canViewConfiguration
);

// Пользователь с кастомным паролем
User userWithCustomPassword = TestUserFactory.createCustomUser(
        "TestUser",
        "CustomPassword123!",
        true,
        true
);
```

### Прямое использование Builder (для особых случаев)

```java
// Для нестандартных случаев можно использовать Builder напрямую
User specialUser = User.builder()
        .name("SpecialUser")
        .pass("SpecialPass")
        .isCanManageSolution(false)
        .isCanViewConfiguration(false)
        .build();
```

### Валидация

```java
User user = TestUserFactory.createSupervisor();
if(!user.

isValid()){
        throw new

IllegalStateException("User is not valid");
}
```

---

## Преимущества улучшений

### 1. **Централизация**

- Все тестовые пользователи создаются в одном месте
- Легко найти и изменить стандартные пользователи
- Единый стиль создания пользователей

### 2. **Использование Properties**

- Пароли и логины загружаются из properties файла
- Нет хардкода в коде
- Легко менять значения для разных окружений

### 3. **Читаемость**

- Код стал более декларативным
- `TestUserFactory.createSupervisor()` читается лучше, чем длинный builder
- Явное намерение создания пользователя

### 4. **Поддерживаемость**

- Изменение стандартных пользователей в одном месте
- Легко добавлять новые типы пользователей
- Меньше вероятность ошибок при копировании кода

### 5. **Безопасность**

- Приватные поля защищают данные
- Безопасный toString не выводит пароли в логи
- Валидация предотвращает создание некорректных объектов

---

## Дополнительные рекомендации

### 1. Bean Validation (опционально)

Для более строгой валидации можно использовать Bean Validation:

```java

@Builder
public class User {
    @NotBlank
    @JsonProperty("UserName")
    private String name;

    @NotBlank
    @JsonProperty("UserPassword")
    private String pass;

    // ...
}
```

### 2. Immutable объекты (опционально)

Для создания неизменяемых объектов:

```java

@Builder
@Value  // Lombok создаст immutable класс
public class User {
    // все поля будут final
}
```

### 3. Fluent Builder с валидацией

```java

@Builder(buildMethodName = "buildValidated")
public class User {
    // ...

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        public User buildValidated() {
            User user = build();
            if (!user.isValid()) {
                throw new IllegalStateException("Invalid user");
            }
            return user;
        }
    }
}
```

---

## Связанные документы

- [PropertiesReader.md](./PropertiesReader.md) - документация по PropertiesReader

---

**Последнее обновление:** 13/11/2025

