package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на Stream API для работы с Set.
 * Операции над множествами через стримы.
 */
@Tag("leetcode")
class SetStreamTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Union of Two Sets                                            │
    │ Верни объединение двух множеств.                             │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Intersection of Two Sets                                     │
    │ Верни пересечение двух множеств.                             │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Difference of Two Sets                                       │
    │ Верни разность множеств (элементы из set1, которых нет в set2).│
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Symmetric Difference                                         │
    │ Верни симметрическую разность (элементы, которые есть только │
    │ в одном из множеств).                                        │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> symmetricDifference(Set<Integer> set1, Set<Integer> set2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Filter Set by Predicate                                      │
    │ Отфильтруй множество, оставив только чётные числа.           │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> filterEven(Set<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Convert Set to Sorted List                                   │
    │ Преобразуй множество в отсортированный список.               │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> toSortedList(Set<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Common Elements in Multiple Sets                        │
    │ Найди элементы, присутствующие во всех множествах.           │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> findCommonInAll(List<Set<Integer>> sets) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Map Set Elements                                             │
    │ Преобразуй каждый элемент множества, умножив на 2.           │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> doubleElements(Set<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Check Subset                                                 │
    │ Проверь, является ли set1 подмножеством set2.                │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isSubset(Set<Integer> set1, Set<Integer> set2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Elements in Set Not in List                             │
    │ Найди элементы множества, которых нет в списке.              │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Integer> notInList(Set<Integer> set, List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("unionProvider")
    void union_shouldUnite(Set<Integer> set1, Set<Integer> set2, Set<Integer> expected) {
        assertEquals(expected, union(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("intersectionProvider")
    void intersection_shouldIntersect(Set<Integer> set1, Set<Integer> set2, Set<Integer> expected) {
        assertEquals(expected, intersection(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("differenceProvider")
    void difference_shouldDiff(Set<Integer> set1, Set<Integer> set2, Set<Integer> expected) {
        assertEquals(expected, difference(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("symmetricProvider")
    void symmetricDifference_shouldFindSymmetric(Set<Integer> set1, Set<Integer> set2, Set<Integer> expected) {
        assertEquals(expected, symmetricDifference(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("filterEvenSetProvider")
    void filterEven_shouldFilter(Set<Integer> input, Set<Integer> expected) {
        assertEquals(expected, filterEven(input));
    }

    @ParameterizedTest
    @MethodSource("toSortedListProvider")
    void toSortedList_shouldSort(Set<Integer> input, List<Integer> expected) {
        assertIterableEquals(expected, toSortedList(input));
    }

    @ParameterizedTest
    @MethodSource("findCommonProvider")
    void findCommonInAll_shouldFindCommon(List<Set<Integer>> sets, Set<Integer> expected) {
        assertEquals(expected, findCommonInAll(sets));
    }

    @ParameterizedTest
    @MethodSource("doubleProvider")
    void doubleElements_shouldDouble(Set<Integer> input, Set<Integer> expected) {
        assertEquals(expected, doubleElements(input));
    }

    @ParameterizedTest
    @MethodSource("subsetProvider")
    void isSubset_shouldCheck(Set<Integer> set1, Set<Integer> set2, boolean expected) {
        assertEquals(expected, isSubset(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("notInListProvider")
    void notInList_shouldFind(Set<Integer> set, List<Integer> list, Set<Integer> expected) {
        assertEquals(expected, notInList(set, list));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> unionProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3), Set.of(3, 4, 5), Set.of(1, 2, 3, 4, 5)),
            Arguments.of(Set.of(1, 2), Set.of(3, 4), Set.of(1, 2, 3, 4)),
            Arguments.of(Set.of(), Set.of(1, 2), Set.of(1, 2))
        );
    }

    private static Stream<Arguments> intersectionProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3, 4), Set.of(3, 4, 5, 6), Set.of(3, 4)),
            Arguments.of(Set.of(1, 2), Set.of(3, 4), Set.of()),
            Arguments.of(Set.of(1, 2, 3), Set.of(1, 2, 3), Set.of(1, 2, 3))
        );
    }

    private static Stream<Arguments> differenceProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3, 4), Set.of(3, 4, 5), Set.of(1, 2)),
            Arguments.of(Set.of(1, 2, 3), Set.of(1, 2, 3), Set.of()),
            Arguments.of(Set.of(1, 2), Set.of(5, 6), Set.of(1, 2))
        );
    }

    private static Stream<Arguments> symmetricProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3), Set.of(3, 4, 5), Set.of(1, 2, 4, 5)),
            Arguments.of(Set.of(1, 2), Set.of(1, 2), Set.of()),
            Arguments.of(Set.of(1), Set.of(2), Set.of(1, 2))
        );
    }

    private static Stream<Arguments> filterEvenSetProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3, 4, 5, 6), Set.of(2, 4, 6)),
            Arguments.of(Set.of(1, 3, 5), Set.of()),
            Arguments.of(Set.of(2, 4), Set.of(2, 4))
        );
    }

    private static Stream<Arguments> toSortedListProvider() {
        return Stream.of(
            Arguments.of(Set.of(3, 1, 4, 1, 5), List.of(1, 3, 4, 5)),
            Arguments.of(Set.of(5, 4, 3, 2, 1), List.of(1, 2, 3, 4, 5)),
            Arguments.of(Set.of(1), List.of(1))
        );
    }

    private static Stream<Arguments> findCommonProvider() {
        return Stream.of(
            Arguments.of(List.of(Set.of(1, 2, 3), Set.of(2, 3, 4), Set.of(2, 3, 5)), Set.of(2, 3)),
            Arguments.of(List.of(Set.of(1, 2), Set.of(3, 4)), Set.of()),
            Arguments.of(List.of(Set.of(1, 2, 3)), Set.of(1, 2, 3))
        );
    }

    private static Stream<Arguments> doubleProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3), Set.of(2, 4, 6)),
            Arguments.of(Set.of(5, 10), Set.of(10, 20)),
            Arguments.of(Set.of(0), Set.of(0))
        );
    }

    private static Stream<Arguments> subsetProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2), Set.of(1, 2, 3, 4), true),
            Arguments.of(Set.of(1, 5), Set.of(1, 2, 3), false),
            Arguments.of(Set.of(), Set.of(1, 2), true)
        );
    }

    private static Stream<Arguments> notInListProvider() {
        return Stream.of(
            Arguments.of(Set.of(1, 2, 3, 4, 5), List.of(2, 4), Set.of(1, 3, 5)),
            Arguments.of(Set.of(1, 2), List.of(1, 2), Set.of()),
            Arguments.of(Set.of(1, 2, 3), List.of(5, 6), Set.of(1, 2, 3))
        );
    }
}
