# Доступ к private‑полям

## Может ли объект получить доступ?

Да, через методы класса (getter/setter) или через reflection.

## Пример

```java
class User {
  private String name;

  public String getName() {
    return name;
  }
}
```
