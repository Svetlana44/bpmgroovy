# SQL Шпаргалка для QA Automation

## 📝 Базовые операции CRUD

### CREATE (INSERT) - Создание записей

```sql
-- Вставка одной записи
INSERT INTO users (name, email, age) 
VALUES ('John Doe', 'john@example.com', 30);

-- Вставка нескольких записей
INSERT INTO users (name, email, age) 
VALUES 
    ('John Doe', 'john@example.com', 30),
    ('Jane Doe', 'jane@example.com', 25),
    ('Bob Smith', 'bob@example.com', 35);
```

### READ (SELECT) - Чтение данных

```sql
-- Выбор всех записей
SELECT * FROM users;

-- Выбор конкретных колонок
SELECT name, email FROM users;

-- Выбор с условием (WHERE)
SELECT * FROM users WHERE age > 30;

-- Выбор с сортировкой (ORDER BY)
SELECT * FROM users ORDER BY age DESC;

-- Выбор с лимитом (LIMIT)
SELECT * FROM users LIMIT 10;
```

### UPDATE - Обновление данных

```sql
-- Обновление одной записи
UPDATE users 
SET age = 31 
WHERE email = 'john@example.com';

-- Обновление нескольких записей
UPDATE users 
SET status = 'active' 
WHERE age > 25;
```

### DELETE - Удаление данных

```sql
-- Удаление одной записи
DELETE FROM users WHERE email = 'john@example.com';

-- Удаление нескольких записей
DELETE FROM users WHERE status = 'inactive';

-- Удаление всех записей (ОСТОРОЖНО!)
DELETE FROM users;
```

---

## 🔗 JOIN - Объединение таблиц

### INNER JOIN
Возвращает только совпадающие записи из обеих таблиц.

```sql
SELECT users.name, orders.order_date, orders.amount
FROM users
INNER JOIN orders ON users.id = orders.user_id;
```

### LEFT JOIN (LEFT OUTER JOIN)
Возвращает все записи из левой таблицы и совпадающие из правой.

```sql
SELECT users.name, orders.order_date
FROM users
LEFT JOIN orders ON users.id = orders.user_id;
-- Вернет всех пользователей, даже без заказов
```

### RIGHT JOIN (RIGHT OUTER JOIN)
Возвращает все записи из правой таблицы и совпадающие из левой.

```sql
SELECT users.name, orders.order_date
FROM users
RIGHT JOIN orders ON users.id = orders.user_id;
-- Вернет все заказы, даже без пользователей
```

### FULL OUTER JOIN
Возвращает все записи из обеих таблиц.

```sql
SELECT users.name, orders.order_date
FROM users
FULL OUTER JOIN orders ON users.id = orders.user_id;
```

---

## 🔍 Полезные функции и операторы

### WHERE - Условия

```sql
-- Сравнение
WHERE age = 30
WHERE age > 25
WHERE age < 40
WHERE age >= 18
WHERE age <= 65

-- Логические операторы
WHERE age > 25 AND city = 'Moscow'
WHERE age < 18 OR age > 65
WHERE NOT status = 'inactive'

-- IN / NOT IN
WHERE city IN ('Moscow', 'SPB', 'Kazan')
WHERE city NOT IN ('Moscow', 'SPB')

-- LIKE (поиск по шаблону)
WHERE email LIKE '%@example.com'
WHERE name LIKE 'John%'  -- начинается с John
WHERE name LIKE '%Doe'   -- заканчивается на Doe

-- BETWEEN
WHERE age BETWEEN 18 AND 65

-- IS NULL / IS NOT NULL
WHERE email IS NULL
WHERE email IS NOT NULL
```

### GROUP BY - Группировка

```sql
-- Группировка с агрегацией
SELECT city, COUNT(*) as user_count
FROM users
GROUP BY city;

-- Группировка с условием (HAVING)
SELECT city, COUNT(*) as user_count
FROM users
GROUP BY city
HAVING COUNT(*) > 10;
```

### Агрегатные функции

```sql
-- COUNT - количество
SELECT COUNT(*) FROM users;

-- SUM - сумма
SELECT SUM(amount) FROM orders;

-- AVG - среднее
SELECT AVG(age) FROM users;

-- MIN / MAX - минимум/максимум
SELECT MIN(age), MAX(age) FROM users;
```

### ORDER BY - Сортировка

```sql
-- По возрастанию (ASC - по умолчанию)
SELECT * FROM users ORDER BY age ASC;

-- По убыванию
SELECT * FROM users ORDER BY age DESC;

-- Множественная сортировка
SELECT * FROM users ORDER BY city ASC, age DESC;
```

---

## 💡 Практические примеры для тестов

### Пример 1: Проверка наличия пользователя

```sql
SELECT * FROM users 
WHERE email = 'test@example.com';
```

### Пример 2: Подготовка тестовых данных

```sql
INSERT INTO users (name, email, age, status) 
VALUES ('Test User', 'test@example.com', 25, 'active');
```

### Пример 3: Очистка тестовых данных

```sql
DELETE FROM users WHERE email LIKE 'test%@example.com';
```

### Пример 4: Проверка количества записей

```sql
SELECT COUNT(*) FROM orders WHERE user_id = 123;
```

### Пример 5: Получение последней записи

```sql
SELECT * FROM orders 
ORDER BY created_at DESC 
LIMIT 1;
```

### Пример 6: Сложный запрос с JOIN

```sql
SELECT 
    users.name,
    users.email,
    COUNT(orders.id) as order_count,
    SUM(orders.amount) as total_amount
FROM users
LEFT JOIN orders ON users.id = orders.user_id
WHERE users.status = 'active'
GROUP BY users.id, users.name, users.email
HAVING COUNT(orders.id) > 0
ORDER BY total_amount DESC;
```

---

## 🧪 Использование в Java тестах

### Пример с JDBC

```java
import java.sql.*;

public class DatabaseTest {
    private Connection connection;
    
    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/testdb",
            "username",
            "password"
        );
    }
    
    @Test
    void testUserExists() throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "test@example.com");
        
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertEquals("Test User", rs.getString("name"));
    }
    
    @Test
    void testInsertUser() throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "New User");
        stmt.setString(2, "new@example.com");
        
        int rowsAffected = stmt.executeUpdate();
        assertEquals(1, rowsAffected);
    }
    
    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
```

---

## 📚 Полезные ресурсы

- SQL Tutorial: https://www.w3schools.com/sql/
- PostgreSQL Documentation: https://www.postgresql.org/docs/
- SQL Fiddle (онлайн тестирование): http://sqlfiddle.com/

---

## ✅ Чек-лист знаний SQL

- [ ] Умею выполнять CRUD операции
- [ ] Понимаю разницу между INNER, LEFT, RIGHT, FULL JOIN
- [ ] Могу использовать WHERE с различными условиями
- [ ] Умею использовать GROUP BY и агрегатные функции
- [ ] Понимаю ORDER BY и LIMIT
- [ ] Могу написать сложный запрос с несколькими JOIN
- [ ] Умею использовать SQL в Java тестах
