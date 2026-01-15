# try‑with‑resources

## Назначение

Автоматическое закрытие ресурсов, реализующих AutoCloseable, даже при исключениях.

## Пример

```java
try (FileWriter writer = new FileWriter("data.txt")) {
  writer.write("hello");
}
```
