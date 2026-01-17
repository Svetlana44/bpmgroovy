# Правила overriding

## Основные правила

- Сигнатура метода должна совпадать
- Нельзя сужать модификатор доступа (public → protected запрещено)
- Можно расширять доступ (protected → public)
- Возвращаемый тип может быть ковариантным (подтип исходного)
- Бросаемые checked‑исключения нельзя расширять (можно сузить или убрать)

## Нельзя

- Менять типы параметров (это overload)

## Что такое сигнатура метода

Сигнатура — это имя метода + список параметров (типы и порядок).
Возвращаемый тип и throws не входят в сигнатуру.

## Пример

```java
class Base {
  protected Number value() { return 1; }
}

class Child extends Base {
  @Override
  public Integer value() { return 2; }
}
```

## Ковариантность (расшифровка)

Если метод в базовом классе возвращает `Number`, то в переопределении
допустимо вернуть его подтип, например `Integer`.

## Пример с исключениями

```java
class Base {
  protected void save() throws java.io.IOException {}
}

class Child extends Base {
  @Override
  public void save() throws java.io.FileNotFoundException {}
}
```
