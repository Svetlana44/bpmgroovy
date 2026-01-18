package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на Stream API для работы с Map.
 * Операции над парами ключ-значение через стримы.
 */
@Tag("leetcode")
class MapStreamTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Filter Map by Value                                          │
    │ Верни Map, где значения больше заданного порога.             │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> filterByValue(Map<String, Integer> map, int threshold) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Filter Map by Key                                            │
    │ Верни Map, где ключи начинаются с заданного префикса.        │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> filterByKeyPrefix(Map<String, Integer> map, String prefix) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Keys                                                 │
    │ Верни список всех ключей, отсортированных по алфавиту.       │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> getSortedKeys(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Values                                               │
    │ Верни список всех значений, отсортированных по убыванию.     │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> getValuesSortedDesc(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of Values                                                │
    │ Верни сумму всех значений в Map.                             │
    └──────────────────────────────────────────────────────────────┘
    */
    int sumValues(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Key with Max Value                                      │
    │ Найди ключ с максимальным значением. Если Map пуст, верни "".│
    └──────────────────────────────────────────────────────────────┘
    */
    String findKeyWithMaxValue(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Invert Map                                                   │
    │ Поменяй ключи и значения местами (значения уникальны).       │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Integer, String> invertMap(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Merge Two Maps                                               │
    │ Объедини две Map. При конфликте ключей суммируй значения.    │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Map Values                                                   │
    │ Преобразуй все значения, умножив на 2.                       │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> doubleValues(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ List to Map                                                  │
    │ Преобразуй список строк в Map: строка -> её длина.           │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> listToMap(List<String> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Count by First Letter                                        │
    │ Посчитай количество ключей, начинающихся с каждой буквы.     │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Character, Long> countByFirstLetter(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sort Map by Value                                            │
    │ Верни список Entry, отсортированных по значению (возраст.).  │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("filterByValueProvider")
    void filterByValue_shouldFilter(Map<String, Integer> input, int threshold, Map<String, Integer> expected) {
        assertEquals(expected, filterByValue(input, threshold));
    }

    @ParameterizedTest
    @MethodSource("filterByKeyPrefixProvider")
    void filterByKeyPrefix_shouldFilter(Map<String, Integer> input, String prefix, Map<String, Integer> expected) {
        assertEquals(expected, filterByKeyPrefix(input, prefix));
    }

    @ParameterizedTest
    @MethodSource("getSortedKeysProvider")
    void getSortedKeys_shouldSort(Map<String, Integer> input, List<String> expected) {
        assertIterableEquals(expected, getSortedKeys(input));
    }

    @ParameterizedTest
    @MethodSource("getValuesDescProvider")
    void getValuesSortedDesc_shouldSort(Map<String, Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, getValuesSortedDesc(input));
    }

    @ParameterizedTest
    @MethodSource("sumValuesProvider")
    void sumValues_shouldSum(Map<String, Integer> input, int expected) {
        assertEquals(expected, sumValues(input));
    }

    @ParameterizedTest
    @MethodSource("findKeyWithMaxProvider")
    void findKeyWithMaxValue_shouldFind(Map<String, Integer> input, String expected) {
        assertEquals(expected, findKeyWithMaxValue(input));
    }

    @ParameterizedTest
    @MethodSource("invertMapProvider")
    void invertMap_shouldInvert(Map<String, Integer> input, Map<Integer, String> expected) {
        assertEquals(expected, invertMap(input));
    }

    @ParameterizedTest
    @MethodSource("mergeMapsProvider")
    void mergeMaps_shouldMerge(Map<String, Integer> map1, Map<String, Integer> map2, Map<String, Integer> expected) {
        assertEquals(expected, mergeMaps(map1, map2));
    }

    @ParameterizedTest
    @MethodSource("doubleValuesProvider")
    void doubleValues_shouldDouble(Map<String, Integer> input, Map<String, Integer> expected) {
        assertEquals(expected, doubleValues(input));
    }

    @ParameterizedTest
    @MethodSource("listToMapProvider")
    void listToMap_shouldConvert(List<String> input, Map<String, Integer> expected) {
        assertEquals(expected, listToMap(input));
    }

    @ParameterizedTest
    @MethodSource("countByFirstLetterProvider")
    void countByFirstLetter_shouldCount(Map<String, Integer> input, Map<Character, Long> expected) {
        assertEquals(expected, countByFirstLetter(input));
    }

    @ParameterizedTest
    @MethodSource("sortByValueProvider")
    void sortByValue_shouldSort(Map<String, Integer> input, List<Map.Entry<String, Integer>> expected) {
        assertIterableEquals(expected, sortByValue(input));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> filterByValueProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 10, "b", 20, "c", 5), 7, Map.of("a", 10, "b", 20)),
            Arguments.of(Map.of("x", 1, "y", 2), 10, Map.of()),
            Arguments.of(Map.of("k", 100), 50, Map.of("k", 100))
        );
    }

    private static Stream<Arguments> filterByKeyPrefixProvider() {
        return Stream.of(
            Arguments.of(Map.of("apple", 1, "apricot", 2, "banana", 3), "ap", Map.of("apple", 1, "apricot", 2)),
            Arguments.of(Map.of("cat", 1, "dog", 2), "z", Map.of()),
            Arguments.of(Map.of("test", 1), "te", Map.of("test", 1))
        );
    }

    private static Stream<Arguments> getSortedKeysProvider() {
        return Stream.of(
            Arguments.of(Map.of("charlie", 1, "alpha", 2, "bravo", 3), List.of("alpha", "bravo", "charlie")),
            Arguments.of(Map.of("z", 1, "a", 2), List.of("a", "z")),
            Arguments.of(Map.of("single", 1), List.of("single"))
        );
    }

    private static Stream<Arguments> getValuesDescProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 10, "b", 30, "c", 20), List.of(30, 20, 10)),
            Arguments.of(Map.of("x", 5), List.of(5)),
            Arguments.of(Map.of("a", 1, "b", 1), List.of(1, 1))
        );
    }

    private static Stream<Arguments> sumValuesProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 10, "b", 20, "c", 30), 60),
            Arguments.of(Map.of("x", 100), 100),
            Arguments.of(Map.of(), 0)
        );
    }

    private static Stream<Arguments> findKeyWithMaxProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 10, "b", 50, "c", 30), "b"),
            Arguments.of(Map.of("only", 1), "only"),
            Arguments.of(Map.of(), "")
        );
    }

    private static Stream<Arguments> invertMapProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 1, "b", 2, "c", 3), Map.of(1, "a", 2, "b", 3, "c")),
            Arguments.of(Map.of("x", 100), Map.of(100, "x")),
            Arguments.of(Map.of(), Map.of())
        );
    }

    private static Stream<Arguments> mergeMapsProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 10, "b", 20), Map.of("b", 30, "c", 40), Map.of("a", 10, "b", 50, "c", 40)),
            Arguments.of(Map.of("x", 1), Map.of("y", 2), Map.of("x", 1, "y", 2)),
            Arguments.of(Map.of(), Map.of("a", 1), Map.of("a", 1))
        );
    }

    private static Stream<Arguments> doubleValuesProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 5, "b", 10), Map.of("a", 10, "b", 20)),
            Arguments.of(Map.of("x", 0), Map.of("x", 0)),
            Arguments.of(Map.of(), Map.of())
        );
    }

    private static Stream<Arguments> listToMapProvider() {
        return Stream.of(
            Arguments.of(List.of("apple", "cat", "banana"), Map.of("apple", 5, "cat", 3, "banana", 6)),
            Arguments.of(List.of("a", "bb", "ccc"), Map.of("a", 1, "bb", 2, "ccc", 3)),
            Arguments.of(List.of("test"), Map.of("test", 4))
        );
    }

    private static Stream<Arguments> countByFirstLetterProvider() {
        return Stream.of(
            Arguments.of(Map.of("apple", 1, "apricot", 2, "banana", 3), Map.of('a', 2L, 'b', 1L)),
            Arguments.of(Map.of("cat", 1, "car", 2, "cup", 3), Map.of('c', 3L)),
            Arguments.of(Map.of("x", 1), Map.of('x', 1L))
        );
    }

    private static Stream<Arguments> sortByValueProvider() {
        return Stream.of(
            Arguments.of(
                Map.of("a", 30, "b", 10, "c", 20),
                List.of(Map.entry("b", 10), Map.entry("c", 20), Map.entry("a", 30))
            ),
            Arguments.of(
                Map.of("x", 1),
                List.of(Map.entry("x", 1))
            )
        );
    }
}
