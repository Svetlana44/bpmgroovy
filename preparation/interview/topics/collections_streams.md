# Коллекции и Stream API

## Вопросы и ответы

**1. Разница между List, Set, Map?**  
List — упорядоченная коллекция, Set — уникальные элементы, Map — ключ‑значение.

**2. Когда использовать ArrayList vs LinkedList?**  
ArrayList быстрый доступ по индексу, LinkedList — вставки/удаления в середине.

**3. Что такое HashMap и TreeMap?**  
HashMap — O(1) в среднем, TreeMap — сортировка по ключу O(log n).

**4. Stream API: чем map отличается от flatMap?**  
map преобразует элемент, flatMap разворачивает вложенные коллекции.

**5. Что такое lazy evaluation в Stream?**  
Промежуточные операции не выполняются без terminal операции.

**6. Как избежать ошибок с параллельными стримами?**  
Использовать stateless операции и thread‑safe коллекторы.

**7. Что важно на Senior‑уровне?**  
Выбор структуры данных под задачу и влияние на сложность/производительность.

## Туториалы

- `collections_streams_tutorials/README.md` — индекс по туториалам с последовательностью.

## LeetCode упражнения

### Easy (6)

- Two Sum — https://leetcode.com/problems/two-sum/
- Contains Duplicate — https://leetcode.com/problems/contains-duplicate/
- Valid Anagram — https://leetcode.com/problems/valid-anagram/
- First Unique Character in a String — https://leetcode.com/problems/first-unique-character-in-a-string/
- Intersection of Two Arrays — https://leetcode.com/problems/intersection-of-two-arrays/
- Ransom Note — https://leetcode.com/problems/ransom-note/

### Medium (2)

- Group Anagrams — https://leetcode.com/problems/group-anagrams/
- Top K Frequent Elements — https://leetcode.com/problems/top-k-frequent-elements/
