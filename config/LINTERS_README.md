# Инструкция по использованию линтеров

В проект добавлены два линтера:

## 1. CodeNarc (для Groovy кода)

**Плагин:** `org.codenarc`

**Запуск:**

```bash
./gradlew codenarcMain    # Проверка основного кода
./gradlew codenarcTest    # Проверка тестового кода
./gradlew codenarc        # Проверка всего проекта
```

**Отчеты:** Генерируются в `build/reports/codenarc/`

**Конфигурация:** `config/codenarc/codenarc.groovy`

## 2. Checkstyle (для Java кода)

**Плагин:** `checkstyle`

**Запуск:**

```bash
./gradlew checkstyleMain    # Проверка основного кода
./gradlew checkstyleTest    # Проверка тестового кода
./gradlew checkstyle        # Проверка всего проекта
```

**Отчеты:** Генерируются в `build/reports/checkstyle/`

**Конфигурация:** `config/checkstyle/checkstyle.xml`

## Запуск всех проверок

```bash
./gradlew check    # Запускает все проверки, включая линтеры
```

## Настройка

Вы можете настроить правила проверки, отредактировав конфигурационные файлы:

- `config/codenarc/codenarc.groovy` - для Groovy
- `config/checkstyle/checkstyle.xml` - для Java

## Исключения

По умолчанию тестовые файлы исключены из некоторых проверок.
Если нужно изменить это поведение, отредактируйте соответствующие конфигурационные файлы.

