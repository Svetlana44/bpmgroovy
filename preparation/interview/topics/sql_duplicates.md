# Поиск дубликатов в SQL

## Найти дубликаты ФИО

```sql
SELECT full_name, COUNT(*) AS cnt
FROM employees
GROUP BY full_name
HAVING COUNT(*) > 1;
```

## Показать сами строки‑дубликаты

```sql
SELECT e.*
FROM employees e
JOIN (
  SELECT full_name
  FROM employees
  GROUP BY full_name
  HAVING COUNT(*) > 1
) d ON d.full_name = e.full_name
ORDER BY e.full_name;
```

## Вариант через оконную функцию

```sql
SELECT *
FROM (
  SELECT e.*,
         COUNT(*) OVER (PARTITION BY full_name) AS cnt
  FROM employees e
) t
WHERE t.cnt > 1
ORDER BY t.full_name;
```
