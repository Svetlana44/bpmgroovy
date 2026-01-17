# План подготовки к вакансии QA Automation (Java)

## Что уже есть в проекте
- RestAssured для API тестирования
- Selenium/Selenide для UI тестирования
- JUnit 5
- Работа с БД (PostgreSQL, MSSQL)
- JSON Schema валидация
- Allure отчёты
- Wiremock примеры — `../examples/contract/WiremockExampleTest.java`
- Playwright пример — `../examples/ui/PlaywrightExampleTest.java`
- MongoDB пример — `../examples/database/MongoDBExampleTest.java`

## Что усилить
- Контрактное тестирование на базе OpenAPI/Swagger
- Стратегия CI/CD и отчётность
- Стабильность UI тестов (waits, локаторы, тест‑данные)
- Архитектура тестового фреймворка

## Приоритеты

### 1) Контракты через OpenAPI
- Валидация схемы ответов
- Проверка обязательных полей и типов
- Проверка статус‑кодов согласно спецификации

### 2) Wiremock
- Стабы, негативные сценарии, задержки
- Проверка вызовов (verify)

### 3) REST API + БД
- Негативные проверки
- Подготовка/очистка данных в БД

### 4) UI + Playwright
- Page Object
- Ожидания и стабильность

## Примерный график (2–3 недели)

### Неделя 1
- OpenAPI‑валидация + Wiremock
- Повторение REST API и SQL

### Неделя 2
- Playwright + UI архитектура
- MongoDB/Kafka/Kibana (обзорно)

### Неделя 3
- Практика задач
- Повторение теории
- Подготовка ответов

## Чек‑лист
- Понимаю OpenAPI‑валидацию
- Могу писать Wiremock стабы
- Уверенно тестирую REST API
- Умею работать с SQL/БД в тестах
- Понимаю архитектуру фреймворка
