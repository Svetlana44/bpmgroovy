# Туториал 1: основы коллекций

## Каркас

- Иерархия: `Collection` → `List`, `Set`, `Queue`; отдельно `Map`.
- Типичные реализации: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`.
- Базовые операции: add/get/remove/contains и их асимптотика.

## Ключевые идеи

- Выбор структуры данных под операцию, а не под привычку.
- `equals` и `hashCode` критичны для `HashMap`/`HashSet`.
- `Tree*` коллекции упорядочены, но дороже по времени.

## Мини‑упражнения

1. Сравни сложность `contains` у `ArrayList` и `HashSet`.
2. Объясни, почему `HashMap` может деградировать до O(n).
3. Для каких задач лучше `LinkedList`, чем `ArrayList`.
