# equals vs ==

## Отличия

- `==` сравнивает ссылки (один и тот же объект)
- `equals` сравнивает логическое равенство (если переопределён)

## Риски

- При использовании `==` для строк можно получить false при равных значениях
- Неправильно переопределённый `equals` ломает коллекции

## Пример

```java
String a = new String("test");
String b = new String("test");
System.out.println(a == b);      // false
System.out.println(a.equals(b)); // true
```
