# Аудит проекта и best practices

## Наблюдения по текущей структуре

- В `src/test/java` лежат `.md` файлы и обучающие материалы — это усложняет сборку
  и мешает навигации.
- Нейминг пакетов разнороден: `testi`, `enams`, `utilies`, `resoursesfiles` —
  ошибки и смешение языков.
- В проекте параллельно используются JUnit и TestNG без чёткой стратегии.
- Нет единой структуры тестовых слоёв (API/UI/contract/core/data).

## Приоритетные улучшения (Senior‑level)

### P0 — Архитектура и читаемость

1. Вынести материалы/cheatsheets из `src/test/java` в `preparation/`.
2. Нормализовать имена пакетов:
   - `testi` → `tests`
   - `enams` → `enums`
   - `utilies` → `utilities`
   - `resoursesfiles` → `resourcesfiles`
3. Единая структура:
   - `api`, `ui`, `contract`, `core`, `data`, `utils`

### P1 — Управление тестами

1. Ввести теги JUnit (`@Tag`) для разделения API/UI/contract.
2. Добавить Gradle‑задачи: `apiTest`, `uiTest`, `contractTest`.
3. Переиспользуемые базовые классы для setup/teardown.

### P2 — Стабильность и качество

1. Стабильные waits (избегать sleep), централизованные timeouts.
2. Контроль флейков и retry‑политика только как временная мера.
3. Стандартизированный логинг request/response в Allure.

## Что можно показать на собеседовании

- Архитектурная схема слоёв фреймворка
- Пример контракта и JSON‑Schema валидации
- CI/CD и публикация Allure
