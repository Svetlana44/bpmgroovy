# Туториал 1: Полная иерархия коллекций и Map

## Оглавление
1. [Общая иерархия](#общая-иерархия)
2. [Интерфейс Collection](#интерфейс-collection)
3. [List — упорядоченные коллекции](#list--упорядоченные-коллекции)
4. [Set — уникальные элементы](#set--уникальные-элементы)
5. [Queue и Deque — очереди](#queue-и-deque--очереди)
6. [Map — ключ-значение](#map--ключ-значение)
7. [Сравнительная таблица сложности](#сравнительная-таблица-сложности)
8. [Контракт equals и hashCode](#контракт-equals-и-hashcode)
9. [Fail-fast vs Fail-safe итераторы](#fail-fast-vs-fail-safe-итераторы)
10. [Частые вопросы на собеседовании](#частые-вопросы-на-собеседовании)

---

## Общая иерархия

```
                         Iterable<E>
                             │
                        Collection<E>
               ┌─────────────┼─────────────┐
               │             │             │
            List<E>        Set<E>       Queue<E>
               │             │             │
       ┌───────┴───────┐     │         ┌───┴───┐
       │               │     │         │       │
  ArrayList      LinkedList  │      Deque<E>  PriorityQueue
                             │         │
                    ┌────────┴────┐    │
                    │             │    │
                 HashSet     SortedSet │
                    │             │    │
            LinkedHashSet    NavigableSet
                                  │    │
                              TreeSet  │
                                       │
                              ┌────────┴────────┐
                              │                 │
                          ArrayDeque       LinkedList


                           Map<K,V>
                              │
           ┌──────────────────┼──────────────────┐
           │                  │                  │
        HashMap           SortedMap          Hashtable
           │                  │
    LinkedHashMap        NavigableMap
                              │
                           TreeMap
```

---

## Интерфейс Collection

Корневой интерфейс для всех коллекций (кроме Map).

### Основные методы Collection<E>

| Метод | Описание |
|-------|----------|
| `int size()` | Количество элементов |
| `boolean isEmpty()` | Пуста ли коллекция |
| `boolean contains(Object o)` | Содержит ли элемент |
| `boolean add(E e)` | Добавить элемент |
| `boolean remove(Object o)` | Удалить элемент |
| `void clear()` | Очистить коллекцию |
| `Iterator<E> iterator()` | Получить итератор |
| `Object[] toArray()` | Преобразовать в массив |
| `<T> T[] toArray(T[] a)` | Преобразовать в типизированный массив |
| `boolean containsAll(Collection<?> c)` | Содержит ли все элементы |
| `boolean addAll(Collection<? extends E> c)` | Добавить все элементы |
| `boolean removeAll(Collection<?> c)` | Удалить все совпадающие |
| `boolean retainAll(Collection<?> c)` | Оставить только совпадающие |
| `default Stream<E> stream()` | Получить Stream (Java 8+) |
| `default Stream<E> parallelStream()` | Параллельный Stream |

---

## List — упорядоченные коллекции

**Особенности**: порядок сохраняется, допускаются дубликаты, доступ по индексу.

### Дополнительные методы List<E>

| Метод | Описание |
|-------|----------|
| `E get(int index)` | Получить по индексу |
| `E set(int index, E element)` | Заменить по индексу |
| `void add(int index, E element)` | Вставить по индексу |
| `E remove(int index)` | Удалить по индексу |
| `int indexOf(Object o)` | Первый индекс элемента |
| `int lastIndexOf(Object o)` | Последний индекс элемента |
| `List<E> subList(int from, int to)` | Подсписок (view!) |
| `ListIterator<E> listIterator()` | Двунаправленный итератор |
| `default void sort(Comparator<? super E> c)` | Сортировка (Java 8+) |
| `static <E> List<E> of(E... elements)` | Неизменяемый список (Java 9+) |

### Реализации List

#### ArrayList
```java
List<String> list = new ArrayList<>();  // default capacity = 10
List<String> list = new ArrayList<>(100);  // initial capacity
```
- Внутри: динамический массив
- **get/set**: O(1)
- **add в конец**: O(1) амортизированно, O(n) при расширении
- **add/remove в середине**: O(n) — сдвиг элементов
- **Когда использовать**: частый доступ по индексу, редкие вставки в середину

#### LinkedList
```java
List<String> list = new LinkedList<>();
Deque<String> deque = new LinkedList<>();  // как двусторонняя очередь
```
- Внутри: двусвязный список
- **get/set по индексу**: O(n)
- **add/remove в начало/конец**: O(1)
- **add/remove в середине**: O(1) если есть итератор, иначе O(n) на поиск
- **Когда использовать**: частые вставки/удаления в начало, реализация очереди

#### Vector (legacy)
- Синхронизированный аналог ArrayList
- **Не рекомендуется**: используй `Collections.synchronizedList()` или `CopyOnWriteArrayList`

#### CopyOnWriteArrayList (java.util.concurrent)
```java
List<String> list = new CopyOnWriteArrayList<>();
```
- При каждой модификации создаётся копия массива
- **Чтение**: O(1), без блокировок
- **Запись**: O(n), дорого
- **Когда использовать**: много чтений, редкие записи, многопоточность

---

## Set — уникальные элементы

**Особенности**: без дубликатов, порядок зависит от реализации.

### Методы Set<E>
Наследует все методы `Collection<E>`. Метод `add()` возвращает `false`, если элемент уже есть.

```java
Set<String> set = new HashSet<>();
set.add("A");  // true
set.add("A");  // false — уже есть
```

### Реализации Set

#### HashSet
```java
Set<String> set = new HashSet<>();
Set<String> set = new HashSet<>(32, 0.75f);  // capacity, loadFactor
```
- Внутри: `HashMap` (элемент = ключ, значение = заглушка)
- **add/remove/contains**: O(1) в среднем, O(n) в худшем
- **Порядок**: не гарантирован
- **null**: допускается один null

#### LinkedHashSet
```java
Set<String> set = new LinkedHashSet<>();
```
- Внутри: `LinkedHashMap`
- **Порядок**: порядок вставки сохраняется
- Чуть медленнее HashSet из-за поддержки связного списка

#### TreeSet
```java
Set<String> set = new TreeSet<>();  // natural ordering
Set<String> set = new TreeSet<>(Comparator.reverseOrder());
```
- Внутри: красно-чёрное дерево (`TreeMap`)
- **add/remove/contains**: O(log n)
- **Порядок**: отсортированный
- **null**: не допускается (начиная с Java 7)
- Реализует `NavigableSet` — дополнительные методы навигации

> **Что такое красно-чёрное дерево?**
> 
> Самобалансирующееся бинарное дерево поиска со следующими свойствами:
> 1. Каждый узел либо **красный**, либо **чёрный**
> 2. Корень всегда **чёрный**
> 3. Все листья (NIL) — **чёрные**
> 4. У красного узла оба потомка **чёрные** (нет двух красных подряд)
> 5. Все пути от узла до листьев содержат **одинаковое число чёрных узлов**
>
> Эти правила гарантируют, что дерево остаётся сбалансированным: высота не превышает `2·log₂(n+1)`. При вставке/удалении происходят **повороты** и **перекраска** узлов для восстановления баланса. Это обеспечивает O(log n) для всех операций в худшем случае (в отличие от обычного BST, которое может деградировать до O(n)).

#### NavigableSet — дополнительные методы
| Метод | Описание |
|-------|----------|
| `E first()` / `E last()` | Первый / последний элемент |
| `E lower(E e)` | Наибольший элемент < e |
| `E floor(E e)` | Наибольший элемент ≤ e |
| `E ceiling(E e)` | Наименьший элемент ≥ e |
| `E higher(E e)` | Наименьший элемент > e |
| `E pollFirst()` / `E pollLast()` | Извлечь и удалить первый/последний |
| `NavigableSet<E> descendingSet()` | Обратный порядок (view) |
| `SortedSet<E> subSet(E from, E to)` | Подмножество |

#### EnumSet
```java
EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
EnumSet<Day> allDays = EnumSet.allOf(Day.class);
```
- Оптимизирован для enum — использует битовый вектор
- Очень быстрый и компактный

#### CopyOnWriteArraySet (java.util.concurrent)
- Аналогичен `CopyOnWriteArrayList`, но для Set

---

## Queue и Deque — очереди

### Интерфейс Queue<E>

FIFO — First In, First Out.

| Операция | Бросает исключение | Возвращает null/false |
|----------|-------------------|----------------------|
| Вставка | `add(e)` | `offer(e)` |
| Удаление | `remove()` | `poll()` |
| Просмотр | `element()` | `peek()` |

### Интерфейс Deque<E>

Double-Ended Queue — двусторонняя очередь.

| Операция | Начало (бросает) | Начало (safe) | Конец (бросает) | Конец (safe) |
|----------|-----------------|---------------|-----------------|--------------|
| Вставка | `addFirst(e)` | `offerFirst(e)` | `addLast(e)` | `offerLast(e)` |
| Удаление | `removeFirst()` | `pollFirst()` | `removeLast()` | `pollLast()` |
| Просмотр | `getFirst()` | `peekFirst()` | `getLast()` | `peekLast()` |

Также Deque можно использовать как **стек**:
| Stack | Deque эквивалент |
|-------|------------------|
| `push(e)` | `addFirst(e)` |
| `pop()` | `removeFirst()` |
| `peek()` | `peekFirst()` |

### Реализации Queue/Deque

#### ArrayDeque
```java
Deque<String> deque = new ArrayDeque<>();
Deque<String> stack = new ArrayDeque<>();  // вместо Stack
Queue<String> queue = new ArrayDeque<>();
```
- Внутри: циклический массив
- **Все операции**: O(1) амортизированно
- **null**: не допускается
- **Рекомендуется** вместо `Stack` и `LinkedList` для стеков и очередей

#### PriorityQueue
```java
Queue<Integer> pq = new PriorityQueue<>();  // min-heap
Queue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());  // max-heap
```
- Внутри: бинарная куча (heap)
- **offer/poll**: O(log n)
- **peek**: O(1)
- **Порядок**: элементы извлекаются в порядке приоритета
- **null**: не допускается

#### LinkedList
- Реализует и `List`, и `Deque`
- Для очередей лучше `ArrayDeque`

#### BlockingQueue (java.util.concurrent)
- `ArrayBlockingQueue` — ограниченная
- `LinkedBlockingQueue` — опционально ограниченная
- `PriorityBlockingQueue` — с приоритетами
- Для producer-consumer паттерна

---

## Map — ключ-значение

**Особенности**: пары ключ-значение, ключи уникальны, не наследует Collection.

### Основные методы Map<K, V>

| Метод | Описание |
|-------|----------|
| `V get(Object key)` | Получить значение по ключу |
| `V put(K key, V value)` | Добавить/заменить, вернуть старое |
| `V remove(Object key)` | Удалить по ключу |
| `boolean containsKey(Object key)` | Есть ли ключ |
| `boolean containsValue(Object value)` | Есть ли значение |
| `int size()` | Количество пар |
| `boolean isEmpty()` | Пуста ли карта |
| `void clear()` | Очистить |
| `Set<K> keySet()` | Множество ключей (view) |
| `Collection<V> values()` | Коллекция значений (view) |
| `Set<Map.Entry<K, V>> entrySet()` | Множество пар (view) |
| `void putAll(Map<? extends K, ? extends V> m)` | Добавить все |

### Методы Java 8+

| Метод | Описание |
|-------|----------|
| `V getOrDefault(Object key, V defaultValue)` | Получить или вернуть default |
| `V putIfAbsent(K key, V value)` | Добавить, если нет |
| `boolean remove(Object key, Object value)` | Удалить, если значение совпадает |
| `boolean replace(K key, V oldValue, V newValue)` | Заменить, если совпадает |
| `V replace(K key, V value)` | Заменить, если есть |
| `V compute(K key, BiFunction)` | Вычислить новое значение |
| `V computeIfAbsent(K key, Function)` | Вычислить, если нет |
| `V computeIfPresent(K key, BiFunction)` | Вычислить, если есть |
| `V merge(K key, V value, BiFunction)` | Объединить значения |
| `void forEach(BiConsumer)` | Итерация |

```java
// Примеры использования Java 8+ методов

// computeIfAbsent — получить значение или создать, если нет
// Если ключа нет → вызывает лямбду, кладёт результат в map, возвращает его
// Если ключ есть → лямбда НЕ вызывается, возвращает существующее значение
// и к нему прибавляет add(item)
// Удобно для группировки: Map<String, List<Item>>
map.computeIfAbsent("key", k -> new ArrayList<>()).add(item);

map.merge("count", 1, Integer::sum);  // инкремент счётчика
map.getOrDefault("missing", "default");
```

### Реализации Map

#### HashMap
```java
Map<String, Integer> map = new HashMap<>();
Map<String, Integer> map = new HashMap<>(32, 0.75f);
```
- Внутри: массив бакетов, каждый бакет — связный список или дерево (с Java 8)
- **get/put/remove**: O(1) в среднем
- **Порядок**: не гарантирован
- **null**: один null-ключ и множество null-значений
- **Load factor**: при превышении (default 0.75) происходит rehashing

**Важно для собеседования**: с Java 8 при >8 элементах в бакете связный список превращается в дерево (O(log n) вместо O(n)).

#### LinkedHashMap
```java
Map<String, Integer> map = new LinkedHashMap<>();
// LRU cache с максимум 100 элементами
Map<String, Integer> lru = new LinkedHashMap<>(16, 0.75f, true) {
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return size() > 100;
    }
};
```
- **Порядок**: порядок вставки или порядок доступа (accessOrder=true)
- Идеален для реализации LRU кэша

#### TreeMap
```java
Map<String, Integer> map = new TreeMap<>();
NavigableMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
```
- Внутри: красно-чёрное дерево
- **get/put/remove**: O(log n)
- **Порядок**: отсортированный по ключам
- **null-ключи**: не допускаются

#### NavigableMap — дополнительные методы
| Метод | Описание |
|-------|----------|
| `K firstKey()` / `K lastKey()` | Первый / последний ключ |
| `Map.Entry<K,V> firstEntry()` / `lastEntry()` | Первая / последняя пара |
| `Map.Entry<K,V> lowerEntry(K key)` | Пара с наибольшим ключом < key |
| `Map.Entry<K,V> floorEntry(K key)` | Пара с наибольшим ключом ≤ key |
| `Map.Entry<K,V> ceilingEntry(K key)` | Пара с наименьшим ключом ≥ key |
| `Map.Entry<K,V> higherEntry(K key)` | Пара с наименьшим ключом > key |
| `NavigableMap<K,V> descendingMap()` | Обратный порядок (view) |
| `SortedMap<K,V> subMap(K from, K to)` | Подкарта |

#### Hashtable (legacy)
- Синхронизированный, не допускает null
- **Не рекомендуется**: используй `ConcurrentHashMap`

#### ConcurrentHashMap (java.util.concurrent)
```java
ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
map.compute("count", (k, v) -> v == null ? 1 : v + 1);  // атомарно
```
- Потокобезопасный без полной блокировки
- Использует сегментирование (до Java 8) или CAS + synchronized на бакет (Java 8+)
- **null**: не допускается (ни ключи, ни значения)

#### EnumMap
```java
Map<Day, String> schedule = new EnumMap<>(Day.class);
```
- Оптимизирован для enum-ключей
- Очень быстрый и компактный

#### WeakHashMap
```java
Map<Key, Value> cache = new WeakHashMap<>();
```
- Ключи хранятся как WeakReference
- Записи автоматически удаляются при GC, если на ключ нет других ссылок
- Для кэшей с автоматической очисткой

#### IdentityHashMap
```java
Map<String, Integer> map = new IdentityHashMap<>();
```
- Сравнивает ключи по `==`, а не `equals()`
- Редко используется

---

## Сравнительная таблица сложности

### List

| Операция | ArrayList | LinkedList |
|----------|-----------|------------|
| get(index) | O(1) | O(n) |
| add(E) в конец | O(1)* | O(1) |
| add(index, E) | O(n) | O(n)** |
| remove(index) | O(n) | O(n)** |
| contains(E) | O(n) | O(n) |
| iterator.remove() | O(n) | O(1) |

\* амортизированно  
\*\* O(1) если есть позиция итератора

### Set

| Операция | HashSet | LinkedHashSet | TreeSet |
|----------|---------|---------------|---------|
| add(E) | O(1) | O(1) | O(log n) |
| remove(E) | O(1) | O(1) | O(log n) |
| contains(E) | O(1) | O(1) | O(log n) |
| итерация | O(n) | O(n) | O(n) |

### Map

| Операция | HashMap | LinkedHashMap | TreeMap |
|----------|---------|---------------|---------|
| get(K) | O(1) | O(1) | O(log n) |
| put(K, V) | O(1) | O(1) | O(log n) |
| remove(K) | O(1) | O(1) | O(log n) |
| containsKey | O(1) | O(1) | O(log n) |

### Queue

| Операция | ArrayDeque | PriorityQueue | LinkedList |
|----------|------------|---------------|------------|
| offer(E) | O(1) | O(log n) | O(1) |
| poll() | O(1) | O(log n) | O(1) |
| peek() | O(1) | O(1) | O(1) |

---

## Контракт equals и hashCode

### Правила

1. **Рефлексивность**: `x.equals(x)` = true
2. **Симметричность**: `x.equals(y)` ⟺ `y.equals(x)`
3. **Транзитивность**: `x.equals(y)` && `y.equals(z)` ⟹ `x.equals(z)`
4. **Консистентность**: результат не меняется без изменения объекта
5. **null**: `x.equals(null)` = false



### Как HashMap ищет значение (важно для понимания!)

```java
V get(Object key) {
    int hash = key.hashCode();           // 1. Вычисляем hashCode
    int bucket = hash % buckets.length;  // 2. Определяем бакет
    
    for (Entry e : buckets[bucket]) {    // 3. Ищем ТОЛЬКО в этом бакете!
        if (e.hash == hash &&            // 4. Сначала сравниваем hash (быстро)
            (e.key == key || key.equals(e.key))) {  // 5. Потом equals
            return e.value;
        }
    }
    return null;  // Не нашли
}
```

**Ключевой момент**: `equals()` вызывается **только если hashCode совпал**. Если hashCode разный — HashMap даже не смотрит в нужный бакет.

### Последствия нарушения контракта

```java
class BadKey {
    int id;
    // equals переопределён, hashCode — нет (используется Object.hashCode = адрес в памяти)
    @Override
    public boolean equals(Object o) {
        return o instanceof BadKey && ((BadKey) o).id == this.id;
    }
}

BadKey key1 = new BadKey(1);
BadKey key2 = new BadKey(1);

key1.equals(key2);                      // true — id одинаковый
key1.hashCode() == key2.hashCode();     // false! — разные объекты в памяти

Map<BadKey, String> map = new HashMap<>();
map.put(key1, "value");
map.get(key2);  // null! — Почему?
```

**Что произошло:**
```
HashMap внутри:
┌─────────────────────────────────────────┐
│ Bucket 0: []                            │
│ Bucket 1: []                            │
│ Bucket 2: [key1 -> "value"]             │  ← key1 попал сюда (hashCode=123)
│ Bucket 3: []                            │
│ Bucket 4: []                            │  ← key2 ищется здесь (hashCode=456)
│ ...                                     │
└─────────────────────────────────────────┘
```

1. `key1.hashCode()` = 123 → положили в бакет 2
2. `key2.hashCode()` = 456 → ищем в бакете 4
3. Бакет 4 пустой → вернули `null`
4. `equals()` **даже не вызывался** — до него не дошли!

### Правильная реализация

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && Objects.equals(name, person.name);
}

@Override
public int hashCode() {
    return Objects.hash(name, age);
}
```

---

## Fail-fast vs Fail-safe итераторы

### Fail-fast (большинство коллекций)

```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
for (String s : list) {
    if (s.equals("b")) {
        list.remove(s);  // ConcurrentModificationException!
    }
}
```

- Бросает `ConcurrentModificationException` при модификации во время итерации
- Использует счётчик `modCount`
- ArrayList, HashMap, HashSet, LinkedList...

**Решение**:
```java
// Через Iterator.remove()
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("b")) {
        it.remove();  // OK
    }
}

// Через removeIf (Java 8+)
list.removeIf(s -> s.equals("b"));
```

### Fail-safe (concurrent коллекции)

```java
List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("a", "b", "c"));
for (String s : list) {
    list.remove(s);  // OK — итератор работает со снимком
}
```

- Работают со снимком или используют блокировки
- CopyOnWriteArrayList, ConcurrentHashMap...

---

## Частые вопросы на собеседовании

### Базовые вопросы

1. **Чем отличается ArrayList от LinkedList?**
   - Структура данных, сложность операций, использование памяти

2. **Чем отличается HashSet от TreeSet?**
   - Упорядоченность, сложность, требование Comparable/Comparator

3. **Чем отличается HashMap от Hashtable?**
   - Синхронизация, null-значения, производительность

4. **Как работает HashMap внутри?**
   - Массив бакетов, хеширование, коллизии, resize, tree-ификация (Java 8+)

5. **Что такое load factor в HashMap?**
   - Порог заполненности для rehashing (default 0.75)

### Средний уровень

6. **Почему String хороший ключ для HashMap?**
   - Неизменяемый (immutable), hashCode кешируется

7. **Что будет, если изменить объект-ключ в HashMap?**
   - hashCode изменится, объект "потеряется" в неправильном бакете

8. **Как реализовать LRU кэш?**
   - LinkedHashMap с accessOrder=true и переопределённым removeEldestEntry()

9. **В чём разница между Comparable и Comparator?**
   - Comparable — естественный порядок в самом классе
   - Comparator — внешний компаратор для любого порядка

10. **Как работает PriorityQueue?**
    - Бинарная куча, элементы извлекаются в порядке приоритета

### Продвинутый уровень

11. **Как ConcurrentHashMap обеспечивает потокобезопасность?**
    - Java 7: сегментирование
    - Java 8+: CAS операции + synchronized на отдельных бакетах

12. **Что такое tree-ификация в HashMap (Java 8)?**
    - При >8 элементах в бакете список превращается в красно-чёрное дерево

13. **Почему в ConcurrentHashMap нельзя использовать null?**
    - Неоднозначность: null означает "нет значения" или "значение null"?

14. **Как реализовать потокобезопасный счётчик в Map?**
    ```java
    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
    // или
    map.merge(key, 1, Integer::sum);
    ```

15. **Когда использовать WeakHashMap?**
    - Кэши, где записи должны удаляться при отсутствии ссылок на ключ


---

## Шпаргалка: что когда использовать

| Задача | Рекомендуемая коллекция |
|--------|------------------------|
| Список с частым доступом по индексу | ArrayList |
| Частые вставки/удаления в начало | LinkedList или ArrayDeque |
| Уникальные элементы, без порядка | HashSet |
| Уникальные элементы, отсортированные | TreeSet |
| Уникальные элементы, порядок вставки | LinkedHashSet |
| Стек (LIFO) | ArrayDeque |
| Очередь (FIFO) | ArrayDeque |
| Очередь с приоритетом | PriorityQueue |
| Пары ключ-значение | HashMap |
| Отсортированные пары | TreeMap |
| Пары с порядком вставки | LinkedHashMap |
| LRU кэш | LinkedHashMap (accessOrder=true) |
| Потокобезопасная Map | ConcurrentHashMap |
| Потокобезопасный List (много чтений) | CopyOnWriteArrayList |
| Enum как ключи/элементы | EnumMap / EnumSet |

---

## Мини‑упражнения

Задачи для практики — решай в `src/test/java/leetcode/collections/CollectionsAdvancedTasks.java`

| # | Упражнение | LeetCode | Сложность |
|---|------------|----------|-----------|
| 1 | Пересечение двух массивов (уникальные) | [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) | Easy |
| 1b | Пересечение с учётом кратности | [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/) | Easy |
| 2 | LRU кэш на LinkedHashMap | [146. LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium |
| 3 | Группировка анаграмм | [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium |
| 4 | ArrayList vs LinkedList (бенчмарк) | — | Практика |
| 5 | Почему ConcurrentHashMap.size() неточен | — | Теория |

### Бонусные задачи

| # | Упражнение | LeetCode | Сложность |
|---|------------|----------|-----------|
| 6 | Первый уникальный символ | [387. First Unique Character](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy |
| 7 | Top K частых элементов | [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium |

### Как решать

```bash
# Запустить все тесты коллекций
./gradlew test --tests "leetcode.collections.*"

# Запустить конкретный тест
./gradlew test --tests "leetcode.collections.CollectionsAdvancedTasks.lruCache*"
```
