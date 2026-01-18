package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на параллельные стримы (Parallel Streams).
 * Частые вопросы: когда использовать, подводные камни, ForkJoinPool.
 */
@Tag("leetcode")
class ParallelStreamTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Sum                                                 │
    │ Посчитай сумму элементов параллельно.                        │
    └──────────────────────────────────────────────────────────────┘
    */
    long parallelSum(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Filter and Count                                    │
    │ Посчитай количество чётных чисел параллельно.                │
    └──────────────────────────────────────────────────────────────┘
    */
    long parallelCountEven(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Find Any                                            │
    │ Найди любой элемент > threshold параллельно.                 │
    │ Верни -1 если не найдено.                                    │
    └──────────────────────────────────────────────────────────────┘
    */
    int parallelFindAny(List<Integer> numbers, int threshold) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel to Set                                              │
    │ Преобразуй в Set параллельно.                                │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> parallelToSet(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Group By                                            │
    │ Сгруппируй строки по длине параллельно.                      │
    │ Используй groupingByConcurrent для потокобезопасности.       │
    └──────────────────────────────────────────────────────────────┘
    */
    ConcurrentMap<Integer, List<String>> parallelGroupByLength(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Reduce                                              │
    │ Найди произведение всех чисел параллельно.                   │
    │ Используй reduce с combiner.                                 │
    └──────────────────────────────────────────────────────────────┘
    */
    long parallelProduct(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel Map and Collect                                     │
    │ Преобразуй каждый элемент (возведи в квадрат) параллельно.   │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> parallelSquare(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sequential vs Parallel                                       │
    │ Верни true если переданный стрим является параллельным.      │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isParallel(Stream<Integer> stream) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Convert to Sequential                                        │
    │ Преобразуй параллельный стрим в последовательный и найди max.│
    └──────────────────────────────────────────────────────────────┘
    */
    int toSequentialAndFindMax(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel ForEach Ordered                                     │
    │ ВАЖНО: forEachOrdered сохраняет порядок в параллельном стриме│
    │ Верни конкатенацию строк с сохранением порядка.              │
    └──────────────────────────────────────────────────────────────┘
    */
    String parallelConcatOrdered(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Unordered for Performance                                    │
    │ Используй unordered() для улучшения производительности.      │
    │ Верни Set уникальных элементов.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> parallelUnorderedDistinct(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel All Match                                           │
    │ Проверь, все ли числа положительные (параллельно).           │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean parallelAllPositive(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Parallel to ConcurrentMap                                    │
    │ Преобразуй список строк в ConcurrentMap: строка -> длина.    │
    └──────────────────────────────────────────────────────────────┘
    */
    ConcurrentMap<String, Integer> parallelToConcurrentMap(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("sumProvider")
    void parallelSum_shouldSum(List<Integer> input, long expected) {
        assertEquals(expected, parallelSum(input));
    }

    @ParameterizedTest
    @MethodSource("countEvenProvider")
    void parallelCountEven_shouldCount(List<Integer> input, long expected) {
        assertEquals(expected, parallelCountEven(input));
    }

    @ParameterizedTest
    @MethodSource("findAnyProvider")
    void parallelFindAny_shouldFind(List<Integer> input, int threshold, boolean shouldFind) {
        int result = parallelFindAny(input, threshold);
        if (shouldFind) {
            assertTrue(result > threshold);
        } else {
            assertEquals(-1, result);
        }
    }

    @ParameterizedTest
    @MethodSource("toSetProvider")
    void parallelToSet_shouldConvert(List<Integer> input, Set<Integer> expected) {
        assertEquals(expected, parallelToSet(input));
    }

    @ParameterizedTest
    @MethodSource("groupByLengthProvider")
    void parallelGroupByLength_shouldGroup(List<String> input, Map<Integer, List<String>> expected) {
        ConcurrentMap<Integer, List<String>> result = parallelGroupByLength(input);
        assertEquals(expected.keySet(), result.keySet());
        for (Integer key : expected.keySet()) {
            assertEquals(Set.copyOf(expected.get(key)), Set.copyOf(result.get(key)));
        }
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void parallelProduct_shouldMultiply(List<Integer> input, long expected) {
        assertEquals(expected, parallelProduct(input));
    }

    @ParameterizedTest
    @MethodSource("squareProvider")
    void parallelSquare_shouldSquare(List<Integer> input, Set<Integer> expected) {
        List<Integer> result = parallelSquare(input);
        assertEquals(expected, Set.copyOf(result));
    }

    @ParameterizedTest
    @MethodSource("isParallelProvider")
    void isParallel_shouldCheck(Stream<Integer> input, boolean expected) {
        assertEquals(expected, isParallel(input));
    }

    @ParameterizedTest
    @MethodSource("toSequentialProvider")
    void toSequentialAndFindMax_shouldFindMax(List<Integer> input, int expected) {
        assertEquals(expected, toSequentialAndFindMax(input));
    }

    @ParameterizedTest
    @MethodSource("concatOrderedProvider")
    void parallelConcatOrdered_shouldConcat(List<String> input, String expected) {
        assertEquals(expected, parallelConcatOrdered(input));
    }

    @ParameterizedTest
    @MethodSource("distinctProvider")
    void parallelUnorderedDistinct_shouldGetDistinct(List<Integer> input, Set<Integer> expected) {
        assertEquals(expected, parallelUnorderedDistinct(input));
    }

    @ParameterizedTest
    @MethodSource("allPositiveProvider")
    void parallelAllPositive_shouldCheck(List<Integer> input, boolean expected) {
        assertEquals(expected, parallelAllPositive(input));
    }

    @ParameterizedTest
    @MethodSource("toConcurrentMapProvider")
    void parallelToConcurrentMap_shouldConvert(List<String> input, Map<String, Integer> expected) {
        assertEquals(expected, parallelToConcurrentMap(input));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), 15L),
            Arguments.of(List.of(10, 20, 30), 60L),
            Arguments.of(List.of(), 0L)
        );
    }

    private static Stream<Arguments> countEvenProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5, 6), 3L),
            Arguments.of(List.of(1, 3, 5), 0L),
            Arguments.of(List.of(2, 4, 6), 3L)
        );
    }

    private static Stream<Arguments> findAnyProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 10, 15, 20), 7, true),
            Arguments.of(List.of(1, 2, 3), 10, false),
            Arguments.of(List.of(), 5, false)
        );
    }

    private static Stream<Arguments> toSetProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 2, 3, 3, 3), Set.of(1, 2, 3)),
            Arguments.of(List.of(1), Set.of(1)),
            Arguments.of(List.of(), Set.of())
        );
    }

    private static Stream<Arguments> groupByLengthProvider() {
        return Stream.of(
            Arguments.of(
                List.of("cat", "dog", "elephant", "ant"),
                Map.of(3, List.of("cat", "dog", "ant"), 8, List.of("elephant"))
            )
        );
    }

    private static Stream<Arguments> productProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4), 24L),
            Arguments.of(List.of(2, 5, 10), 100L),
            Arguments.of(List.of(7), 7L)
        );
    }

    private static Stream<Arguments> squareProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), Set.of(1, 4, 9)),
            Arguments.of(List.of(5, 10), Set.of(25, 100))
        );
    }

    private static Stream<Arguments> isParallelProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3).parallelStream(), true),
            Arguments.of(List.of(1, 2, 3).stream(), false)
        );
    }

    private static Stream<Arguments> toSequentialProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 3, 9, 2), 9),
            Arguments.of(List.of(-5, -1, -10), -1)
        );
    }

    private static Stream<Arguments> concatOrderedProvider() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c"), "abc"),
            Arguments.of(List.of("hello", " ", "world"), "hello world")
        );
    }

    private static Stream<Arguments> distinctProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 2, 3, 3, 3, 4), Set.of(1, 2, 3, 4)),
            Arguments.of(List.of(5, 5, 5), Set.of(5))
        );
    }

    private static Stream<Arguments> allPositiveProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), true),
            Arguments.of(List.of(1, -2, 3), false),
            Arguments.of(List.of(), true)
        );
    }

    private static Stream<Arguments> toConcurrentMapProvider() {
        return Stream.of(
            Arguments.of(List.of("cat", "dog", "elephant"), Map.of("cat", 3, "dog", 3, "elephant", 8)),
            Arguments.of(List.of("a", "bb"), Map.of("a", 1, "bb", 2))
        );
    }
}
