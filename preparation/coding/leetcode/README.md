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
