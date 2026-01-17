# SQL и работа с БД в автотестах

## Вопросы и ответы

**1. Какие базовые операции SQL ты знаешь?**  
SELECT, INSERT, UPDATE, DELETE, JOIN, GROUP BY, HAVING.

**2. Какие виды JOIN и когда использовать?**  
INNER, LEFT, RIGHT, FULL OUTER — в зависимости от необходимости включать
несовпадающие строки.

**3. Как использовать БД в тестах?**  
Подготовка данных, проверка состояния, очистка после теста.

**4. Как избегать тестового мусора?**  
Транзакции, cleanup, уникальные тестовые данные.

**5. Пример проверки в БД:**
```java
User user = jdbcTemplate.queryForObject(
  "SELECT * FROM users WHERE email = ?",
  new Object[] { "test@example.com" },
  userRowMapper
);
assertThat(user).isNotNull();
```

**6. Что важно для Senior‑уровня?**  
Стабильность тестов при параллельных запусках и консистентность данных.
