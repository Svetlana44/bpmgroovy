package leetcode.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на коллекции для подготовки к собеседованию.
 * Связаны с мини-упражнениями из туториала 01_collections_basics.md
 */
@Tag("leetcode")
class CollectionsAdvancedTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 1: Пересечение двух списков                       │
    │ LeetCode 349: Intersection of Two Arrays                     │
    │ https://leetcode.com/problems/intersection-of-two-arrays/    │
    │                                                              │
    │ Верни массив уникальных элементов, присутствующих в обоих    │
    │ массивах. Порядок не важен.                                  │
    │                                                              │
    │ Подсказка: используй HashSet для O(n) решения                │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] intersection(int[] nums1, int[] nums2) {
        // TODO: реализуй
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 1b: Пересечение с учётом кратности                │
    │ LeetCode 350: Intersection of Two Arrays II                  │
    │ https://leetcode.com/problems/intersection-of-two-arrays-ii/ │
    │                                                              │
    │ Верни пересечение с учётом количества вхождений.             │
    │ [1,2,2,1] ∩ [2,2] = [2,2]                                    │
    │                                                              │
    │ Подсказка: используй HashMap для подсчёта частот             │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] intersect(int[] nums1, int[] nums2) {
        // TODO: реализуй
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 2: LRU Cache                                      │
    │ LeetCode 146: LRU Cache                                      │
    │ https://leetcode.com/problems/lru-cache/                     │
    │                                                              │
    │ Реализуй LRU (Least Recently Used) кэш с операциями:         │
    │ - get(key): вернуть значение или -1, если нет                │
    │ - put(key, value): добавить/обновить значение                │
    │                                                              │
    │ При превышении capacity удаляется наименее недавно           │
    │ использованный элемент.                                      │
    │                                                              │
    │ Подсказка: LinkedHashMap с accessOrder=true                  │
    └──────────────────────────────────────────────────────────────┘
    */
    static class LRUCache {
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            // TODO: инициализируй структуру данных
            throw new UnsupportedOperationException("TODO");
        }

        public int get(int key) {
            // TODO: реализуй
            throw new UnsupportedOperationException("TODO");
        }

        public void put(int key, int value) {
            // TODO: реализуй
            throw new UnsupportedOperationException("TODO");
        }
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 3: Группировка анаграмм                           │
    │ LeetCode 49: Group Anagrams                                  │
    │ https://leetcode.com/problems/group-anagrams/                │
    │                                                              │
    │ Сгруппируй строки-анаграммы вместе.                          │
    │ ["eat","tea","tan","ate","nat","bat"] →                      │
    │ [["bat"],["nat","tan"],["ate","eat","tea"]]                  │
    │                                                              │
    │ Подсказка: ключ = отсортированная строка или частоты букв    │
    └──────────────────────────────────────────────────────────────┘
    */
    List<List<String>> groupAnagrams(String[] strs) {
        // TODO: реализуй
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 4: Сравнение ArrayList vs LinkedList              │
    │                                                              │
    │ Не LeetCode задача — практическое сравнение производительности│
    │ Вставка в середину списка.                                   │
    │                                                              │
    │ Заполни метод и запусти тест, чтобы увидеть разницу.         │
    └──────────────────────────────────────────────────────────────┘
    */
    long measureInsertInMiddle(List<String> list, int operations) {
        // Сначала добавим начальные элементы
        for (int i = 0; i < 10000; i++) {
            list.add("item" + i);
        }

        long start = System.nanoTime();

        for (int i = 0; i < operations; i++) {
            int middle = list.size() / 2;
            list.add(middle, "new" + i);  // вставка в середину
        }

        return System.nanoTime() - start;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Упражнение 5: Почему ConcurrentHashMap.size() неточен?       │
    │                                                              │
    │ Теоретический вопрос — объясни в комментарии ниже.           │
    │ Тест демонстрирует это поведение.                            │
    └──────────────────────────────────────────────────────────────┘
    */
    /*
     * ОТВЕТ (заполни):
     *
     * ConcurrentHashMap.size() может быть неточным потому что:
     * 1. ...
     * 2. ...
     * 3. ...
     *
     * Для точного подсчёта используй: ...
     */

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Бонус: First Unique Character                                │
    │ LeetCode 387: First Unique Character in a String             │
    │ https://leetcode.com/problems/first-unique-character-in-a-string/ │
    │                                                              │
    │ Верни индекс первого неповторяющегося символа или -1.        │
    │                                                              │
    │ Подсказка: LinkedHashMap сохраняет порядок вставки           │
    └──────────────────────────────────────────────────────────────┘
    */
    int firstUniqChar(String s) {
        // TODO: реализуй
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Бонус: Top K Frequent Elements                               │
    │ LeetCode 347: Top K Frequent Elements                        │
    │ https://leetcode.com/problems/top-k-frequent-elements/       │
    │                                                              │
    │ Верни k самых частых элементов.                              │
    │                                                              │
    │ Подсказка: HashMap + PriorityQueue (min-heap размера k)      │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] topKFrequent(int[] nums, int k) {
        // TODO: реализуй
        throw new UnsupportedOperationException("TODO");
    }

    // ==================== ТЕСТЫ ====================

    @ParameterizedTest
    @MethodSource("intersectionProvider")
    void intersection_shouldReturnUnique(int[] nums1, int[] nums2, int[] expected) {
        int[] result = intersection(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("intersectProvider")
    void intersect_shouldReturnWithMultiplicity(int[] nums1, int[] nums2, int[] expected) {
        int[] result = intersect(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    @Test
    void lruCache_shouldEvictLeastRecentlyUsed() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));       // возвращает 1, теперь 1 — недавно использованный

        cache.put(3, 3);                      // вытесняет ключ 2 (он LRU)
        assertEquals(-1, cache.get(2));       // не найден

        cache.put(4, 4);                      // вытесняет ключ 1
        assertEquals(-1, cache.get(1));       // не найден
        assertEquals(3, cache.get(3));        // возвращает 3
        assertEquals(4, cache.get(4));        // возвращает 4
    }

    @Test
    void lruCache_shouldUpdateExisting() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);                     // обновляем значение
        assertEquals(10, cache.get(1));       // должно быть 10

        cache.put(3, 3);                      // вытесняет 2 (1 недавно обновлён)
        assertEquals(-1, cache.get(2));
    }

    @ParameterizedTest
    @MethodSource("anagramProvider")
    void groupAnagrams_shouldGroupCorrectly(String[] input, int expectedGroups) {
        List<List<String>> result = groupAnagrams(input);
        assertEquals(expectedGroups, result.size());
        // Проверяем, что все элементы на месте
        int totalElements = result.stream().mapToInt(List::size).sum();
        assertEquals(input.length, totalElements);
    }

    @Test
    void compareArrayListVsLinkedList_insertInMiddle() {
        int operations = 1000;

        long arrayListTime = measureInsertInMiddle(new ArrayList<>(), operations);
        long linkedListTime = measureInsertInMiddle(new LinkedList<>(), operations);

        System.out.println("=== Вставка " + operations + " элементов в середину ===");
        System.out.println("ArrayList:  " + arrayListTime / 1_000_000 + " ms");
        System.out.println("LinkedList: " + linkedListTime / 1_000_000 + " ms");
        System.out.println();
        System.out.println("Ожидание: ArrayList быстрее, потому что:");
        System.out.println("- ArrayList: сдвиг элементов в памяти (CPU cache friendly)");
        System.out.println("- LinkedList: O(n) на поиск позиции + создание узла");
        System.out.println();
        System.out.println("LinkedList выигрывает ТОЛЬКО при вставке через Iterator,");
        System.out.println("когда позиция уже найдена (тогда вставка O(1)).");

        // ArrayList обычно быстрее даже для вставки в середину!
        // Это контринтуитивно, но связано с кэшированием CPU
    }

    @Test
    void concurrentHashMap_sizeCanBeInaccurate() throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

        // Запускаем много потоков, добавляющих элементы
        int threads = 10;
        int operationsPerThread = 10000;
        Thread[] threadArray = new Thread[threads];

        for (int t = 0; t < threads; t++) {
            final int threadId = t;
            threadArray[t] = new Thread(() -> {
                for (int i = 0; i < operationsPerThread; i++) {
                    map.put(threadId * operationsPerThread + i, i);
                }
            });
        }

        // Стартуем все потоки
        for (Thread thread : threadArray) {
            thread.start();
        }

        // Пока потоки работают, size() может быть неточным
        System.out.println("=== ConcurrentHashMap.size() во время записи ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("size() = " + map.size() + " (ожидаем ~" + threads * operationsPerThread + ")");
            Thread.sleep(10);
        }

        // Ждём завершения всех потоков
        for (Thread thread : threadArray) {
            thread.join();
        }

        System.out.println();
        System.out.println("После завершения всех потоков:");
        System.out.println("size() = " + map.size());
        System.out.println("mappingCount() = " + map.mappingCount());
        System.out.println();
        System.out.println("Почему size() может быть неточен:");
        System.out.println("1. size() не блокирует всю map");
        System.out.println("2. Подсчёт идёт по сегментам, которые могут меняться");
        System.out.println("3. Возвращает int, а элементов может быть > Integer.MAX_VALUE");
        System.out.println();
        System.out.println("Для точного подсчёта используй mappingCount() (возвращает long)");

        assertEquals(threads * operationsPerThread, map.size());
    }

    @ParameterizedTest
    @MethodSource("firstUniqProvider")
    void firstUniqChar_shouldFindFirst(String s, int expected) {
        assertEquals(expected, firstUniqChar(s));
    }

    @ParameterizedTest
    @MethodSource("topKProvider")
    void topKFrequent_shouldReturnTopK(int[] nums, int k, int[] expected) {
        int[] result = topKFrequent(nums, k);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    // ==================== ПРОВАЙДЕРЫ ДАННЫХ ====================

    private static Stream<Arguments> intersectionProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2}),
                Arguments.of(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{4, 9}),
                Arguments.of(new int[]{1}, new int[]{2}, new int[]{}),
                Arguments.of(new int[]{1, 2, 3}, new int[]{1, 2, 3}, new int[]{1, 2, 3})
        );
    }

    private static Stream<Arguments> intersectProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2, 2}),
                Arguments.of(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{4, 9}),
                Arguments.of(new int[]{1}, new int[]{2}, new int[]{}),
                Arguments.of(new int[]{1, 1, 1}, new int[]{1, 1}, new int[]{1, 1})
        );
    }

    private static Stream<Arguments> anagramProvider() {
        return Stream.of(
                Arguments.of(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}, 3),
                Arguments.of(new String[]{""}, 1),
                Arguments.of(new String[]{"a"}, 1),
                Arguments.of(new String[]{"abc", "bca", "cab", "xyz", "zyx"}, 2)
        );
    }

    private static Stream<Arguments> firstUniqProvider() {
        return Stream.of(
                Arguments.of("leetcode", 0),      // 'l' первый уникальный
                Arguments.of("loveleetcode", 2),  // 'v' первый уникальный
                Arguments.of("aabb", -1)          // нет уникальных
        );
    }

    private static Stream<Arguments> topKProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}),
                Arguments.of(new int[]{1}, 1, new int[]{1}),
                Arguments.of(new int[]{4, 4, 4, 5, 5, 6}, 2, new int[]{4, 5})
        );
    }
}
