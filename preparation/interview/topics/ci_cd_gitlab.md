# GitLab CI/CD

## Вопросы и ответы

**1. Что такое pipeline в GitLab?**  
Набор стадий и job‑ов, запускаемых по правилам репозитория.

**2. Какие ключевые секции .gitlab-ci.yml?**  
`stages`, `jobs`, `script`, `artifacts`, `rules/only/except`, `cache`.

**3. Как публиковать Allure отчёт?**  
Сохранять `allure-results`, генерировать `allure-report`, публиковать артефакт.

**4. Как разделить API/UI/contract тесты?**  
Разные jobs, теги, параллельные стадии.

**5. Что важно для Senior‑уровня?**  
Оптимизация пайплайнов, кеширование, параллелизация, стабильность.

**Пример job для Gradle‑тестов:**
```yaml
stages:
  - test

tests:
  stage: test
  script:
    - ./gradlew clean test
  artifacts:
    when: always
    paths:
      - build/allure-results
```
