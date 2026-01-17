# LeetCode‑подготовка (Java)

Формат задач — как в C#‑проекте `C:\projects\LeetCode.sln`:
описание → заготовка → тесты проверки.

## Где лежит код

- Тесты и заготовки задач: `src/test/java/leetcode/**`
- Запуск: `./gradlew leetcodeTest`

## Правила работы

1. Найти метод с `throw new UnsupportedOperationException("TODO")`.
2. Реализовать решение.
3. Запустить тесты.

## Почему тесты не мешают основному CI

Все задачи помечены тегом `@Tag("leetcode")` и исключены из стандартного
`test`‑таска. Запускаются только через `leetcodeTest`.

## Стиль тестирования

Используется `@ParameterizedTest` с `@MethodSource` для всех тестов:
- Поддерживает сложные типы (массивы, объекты)
- Единообразие во всех классах задач
- Легко добавлять новые тест-кейсы
- Альтернатива `@CsvSource` не подходит для массивов и сложных объектов

### Альтернатива для простых случаев

Для простых типов (String, int, boolean) можно использовать `@CsvSource`:

```java
@ParameterizedTest
@CsvSource({
    "hello, olleh",
    "abc, cba",
    "a, a"
})
void reverseString_shouldReverse(String input, String expected) {
    assertEquals(expected, reverseString(input));
}
```

Но для массивов и сложных объектов нужен `@MethodSource`:

```java
@ParameterizedTest
@MethodSource("joinStringsProvider")
void joinStrings_shouldConcatenate(String[] input, String expected) {
    assertEquals(expected, joinStrings(input));
}
```

### Чтение параметров из файла

Для больших наборов данных можно использовать `@CsvFileSource`:

```java
@ParameterizedTest
@CsvFileSource(resources = "/testdata/reverse_string.csv", numLinesToSkip = 1)
void reverseString_shouldReverse(String input, String expected) {
    assertEquals(expected, reverseString(input));
}
```

Файл `src/test/resources/testdata/reverse_string.csv`:
```csv
input,expected
hello,olleh
abc,cba
a,a
```

**Важно:** `resources` указывается относительно `src/test/resources/`, файл должен быть в classpath.
