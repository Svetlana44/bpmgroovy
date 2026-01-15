# Функциональный интерфейс

## Определение

Интерфейс с одним абстрактным методом (SAM). 
Может содержать default/static методы.

## Пример

```java
@FunctionalInterface
interface Checker {
  boolean check(String value);

  default boolean isBlank(String value) {
    return value == null || value.isBlank();
  }

  static Checker notBlankChecker() {
    return v -> v != null && !v.isBlank();
  }
}

Checker notEmpty = s -> s != null && !s.isBlank();
Checker notBlank = Checker.notBlankChecker();
```
