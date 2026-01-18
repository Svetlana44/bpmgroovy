package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на Stream API для работы с List.
 * Типичные вопросы на собеседовании по стримам и списку.
 */
@Tag("leetcode")
class ListStreamTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Filter Even Numbers                                          │
    │ Отфильтруй только чётные числа из списка.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> filterEven(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Square Numbers                                               │
    │ Возведи каждое число в квадрат.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> squareNumbers(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find First Greater Than                                      │
    │ Найди первый элемент больше заданного или верни -1.          │
    └──────────────────────────────────────────────────────────────┘
    */
    int findFirstGreaterThan(List<Integer> numbers, int threshold) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of List                                                  │
    │ Верни сумму всех элементов списка.                           │
    └──────────────────────────────────────────────────────────────┘
    */
    int sumOfList(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sort Descending                                              │
    │ Отсортируй список в порядке убывания.                        │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> sortDescending(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Remove Duplicates                                            │
    │ Удали дубликаты из списка, сохранив порядок.                 │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> removeDuplicates(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Count Elements                                               │
    │ Посчитай количество элементов, удовлетворяющих условию.      │
    │ Условие: число > 10.                                         │
    └──────────────────────────────────────────────────────────────┘
    */
    long countGreaterThanTen(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Skip and Limit                                               │
    │ Пропусти первые n элементов и верни следующие m.             │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> skipAndLimit(List<Integer> numbers, int skip, int limit) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Any Match                                                    │
    │ Проверь, содержит ли список отрицательное число.             │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean hasNegative(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ All Match                                                    │
    │ Проверь, все ли числа положительные.                         │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean allPositive(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Max                                                     │
    │ Найди максимальный элемент или верни 0 если список пуст.     │
    └──────────────────────────────────────────────────────────────┘
    */
    int findMax(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Join Strings                                                 │
    │ Объедини строки через заданный разделитель.                  │
    └──────────────────────────────────────────────────────────────┘
    */
    String joinStrings(List<String> strings, String delimiter) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("filterEvenProvider")
    void filterEven_shouldReturnOnlyEven(List<Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, filterEven(input));
    }

    @ParameterizedTest
    @MethodSource("squareProvider")
    void squareNumbers_shouldSquare(List<Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, squareNumbers(input));
    }

    @ParameterizedTest
    @MethodSource("findFirstProvider")
    void findFirstGreaterThan_shouldFind(List<Integer> input, int threshold, int expected) {
        assertEquals(expected, findFirstGreaterThan(input, threshold));
    }

    @ParameterizedTest
    @MethodSource("sumProvider")
    void sumOfList_shouldSum(List<Integer> input, int expected) {
        assertEquals(expected, sumOfList(input));
    }

    @ParameterizedTest
    @MethodSource("sortDescProvider")
    void sortDescending_shouldSort(List<Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, sortDescending(input));
    }

    @ParameterizedTest
    @MethodSource("removeDupProvider")
    void removeDuplicates_shouldRemove(List<Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, removeDuplicates(input));
    }

    @ParameterizedTest
    @MethodSource("countProvider")
    void countGreaterThanTen_shouldCount(List<Integer> input, long expected) {
        assertEquals(expected, countGreaterThanTen(input));
    }

    @ParameterizedTest
    @MethodSource("skipLimitProvider")
    void skipAndLimit_shouldSkipAndLimit(List<Integer> input, int skip, int limit, List<Integer> expected) {
        assertIterableEquals(expected, skipAndLimit(input, skip, limit));
    }

    @ParameterizedTest
    @MethodSource("hasNegativeProvider")
    void hasNegative_shouldCheck(List<Integer> input, boolean expected) {
        assertEquals(expected, hasNegative(input));
    }

    @ParameterizedTest
    @MethodSource("allPositiveProvider")
    void allPositive_shouldCheck(List<Integer> input, boolean expected) {
        assertEquals(expected, allPositive(input));
    }

    @ParameterizedTest
    @MethodSource("findMaxProvider")
    void findMax_shouldFindMax(List<Integer> input, int expected) {
        assertEquals(expected, findMax(input));
    }

    @ParameterizedTest
    @MethodSource("joinProvider")
    void joinStrings_shouldJoin(List<String> input, String delimiter, String expected) {
        assertEquals(expected, joinStrings(input, delimiter));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> filterEvenProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(2, 4, 6)),
            Arguments.of(List.of(1, 3, 5), List.of()),
            Arguments.of(List.of(2, 4, 6), List.of(2, 4, 6))
        );
    }

    private static Stream<Arguments> squareProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), List.of(1, 4, 9)),
            Arguments.of(List.of(-2, 0, 2), List.of(4, 0, 4)),
            Arguments.of(List.of(5), List.of(25))
        );
    }

    private static Stream<Arguments> findFirstProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 10, 15), 7, 10),
            Arguments.of(List.of(1, 2, 3), 10, -1),
            Arguments.of(List.of(100, 200), 50, 100)
        );
    }

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), 15),
            Arguments.of(List.of(-1, 1), 0),
            Arguments.of(List.of(100), 100)
        );
    }

    private static Stream<Arguments> sortDescProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 3, 2, 5, 4), List.of(5, 4, 3, 2, 1)),
            Arguments.of(List.of(5, 4, 3, 2, 1), List.of(5, 4, 3, 2, 1)),
            Arguments.of(List.of(1), List.of(1))
        );
    }

    private static Stream<Arguments> removeDupProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 2, 3, 3, 3), List.of(1, 2, 3)),
            Arguments.of(List.of(1, 1, 1), List.of(1)),
            Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3))
        );
    }

    private static Stream<Arguments> countProvider() {
        return Stream.of(
            Arguments.of(List.of(5, 10, 15, 20, 25), 3L),
            Arguments.of(List.of(1, 2, 3), 0L),
            Arguments.of(List.of(11, 12, 13), 3L)
        );
    }

    private static Stream<Arguments> skipLimitProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7), 2, 3, List.of(3, 4, 5)),
            Arguments.of(List.of(1, 2, 3), 0, 2, List.of(1, 2)),
            Arguments.of(List.of(1, 2, 3), 5, 2, List.of())
        );
    }

    private static Stream<Arguments> hasNegativeProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, -3, 4), true),
            Arguments.of(List.of(1, 2, 3), false),
            Arguments.of(List.of(-1), true)
        );
    }

    private static Stream<Arguments> allPositiveProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4), true),
            Arguments.of(List.of(1, 2, -3, 4), false),
            Arguments.of(List.of(0, 1, 2), false)
        );
    }

    private static Stream<Arguments> findMaxProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 3, 9, 2), 9),
            Arguments.of(List.of(-5, -3, -1), -1),
            Arguments.of(List.of(), 0)
        );
    }

    private static Stream<Arguments> joinProvider() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c"), ", ", "a, b, c"),
            Arguments.of(List.of("hello", "world"), " ", "hello world"),
            Arguments.of(List.of("single"), "-", "single")
        );
    }
}
