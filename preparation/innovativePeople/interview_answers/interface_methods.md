# Реализация методов в интерфейсе

## Способы

- `default` методы (Java 8+)
- `static` методы
- `private` методы (Java 9+) для общих частей

## В каком случае использовать

- `default` — когда нужно добавить поведение в интерфейс, не ломая реализации
- `static` — когда нужен фабричный метод или утилита, связанная с интерфейсом
- `private` — когда надо вынести общую логику для нескольких `default` методов

## Пример

```java
interface Logger {
  default void logInfo(String msg) {
    log("INFO", msg);
  }

  private void log(String level, String msg) {
    System.out.println(level + ": " + msg);
  }

  static Logger create() {
    return new ConsoleLogger();
  }
}
```
