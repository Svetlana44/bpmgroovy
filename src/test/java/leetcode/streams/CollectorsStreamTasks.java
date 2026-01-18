package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на Collectors - одна из самых частых тем на собеседованиях.
 * Включает groupingBy, partitioningBy, mapping, reducing и другие.
 */
@Tag("leetcode")
class CollectorsStreamTasks {

    // Вспомогательный record для задач
    record Person(String name, int age, String department) {}
    record Product(String name, String category, double price) {}

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group by Length                                              │
    │ Сгруппируй строки по их длине.                               │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Integer, List<String>> groupByLength(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group by First Letter                                        │
    │ Сгруппируй строки по первой букве.                           │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Character, List<String>> groupByFirstLetter(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Partition by Even                                            │
    │ Раздели числа на чётные (true) и нечётные (false).           │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Boolean, List<Integer>> partitionByEven(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group and Count                                              │
    │ Сгруппируй строки по длине и посчитай количество в группе.   │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Integer, Long> groupByLengthAndCount(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group Persons by Department                                  │
    │ Сгруппируй людей по отделу.                                  │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, List<Person>> groupByDepartment(List<Person> persons) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group and Get Names                                          │
    │ Сгруппируй людей по отделу, но верни только имена.           │
    │ Используй Collectors.mapping().                              │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, List<String>> groupByDepartmentNames(List<Person> persons) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group and Sum Ages                                           │
    │ Сгруппируй людей по отделу и посчитай сумму возрастов.       │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> groupByDepartmentSumAge(List<Person> persons) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group and Find Max Age                                       │
    │ Сгруппируй по отделу и найди человека с макс. возрастом.     │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Optional<Person>> groupByDepartmentMaxAge(List<Person> persons) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group and Average Price                                      │
    │ Сгруппируй продукты по категории и найди среднюю цену.       │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Double> groupByCategoryAvgPrice(List<Product> products) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Joining Strings                                              │
    │ Объедини строки через разделитель с помощью joining().       │
    └──────────────────────────────────────────────────────────────┘
    */
    String joinWithDelimiter(List<String> strings, String delimiter) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Joining with Prefix and Suffix                               │
    │ Объедини строки с разделителем, префиксом и суффиксом.       │
    └──────────────────────────────────────────────────────────────┘
    */
    String joinWithPrefixSuffix(List<String> strings, String delimiter, String prefix, String suffix) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Summary Statistics                                           │
    │ Верни IntSummaryStatistics для списка чисел.                 │
    └──────────────────────────────────────────────────────────────┘
    */
    IntSummaryStatistics getStatistics(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Reduce to Product                                            │
    │ Перемножь все числа списка через reducing().                 │
    └──────────────────────────────────────────────────────────────┘
    */
    int reduceToProduct(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Collect to Unmodifiable List                                 │
    │ Собери в неизменяемый список.                                │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> collectToUnmodifiable(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Collect to TreeSet                                           │
    │ Собери строки в отсортированное множество TreeSet.           │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<String> collectToTreeSet(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group by Multiple Levels                                     │
    │ Сгруппируй людей по отделу, затем по чётности возраста.      │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Map<Boolean, List<Person>>> groupByDepartmentThenByEvenAge(List<Person> persons) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Collect and Then                                             │
    │ Сгруппируй по длине и преобразуй результат в размер списка.  │
    │ Используй collectingAndThen().                               │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<Integer, Integer> groupByLengthAndSize(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("groupByLengthProvider")
    void groupByLength_shouldGroup(List<String> input, Map<Integer, List<String>> expected) {
        assertEquals(expected, groupByLength(input));
    }

    @ParameterizedTest
    @MethodSource("groupByFirstLetterProvider")
    void groupByFirstLetter_shouldGroup(List<String> input, Map<Character, List<String>> expected) {
        assertEquals(expected, groupByFirstLetter(input));
    }

    @ParameterizedTest
    @MethodSource("partitionProvider")
    void partitionByEven_shouldPartition(List<Integer> input, Map<Boolean, List<Integer>> expected) {
        assertEquals(expected, partitionByEven(input));
    }

    @ParameterizedTest
    @MethodSource("groupCountProvider")
    void groupByLengthAndCount_shouldCount(List<String> input, Map<Integer, Long> expected) {
        assertEquals(expected, groupByLengthAndCount(input));
    }

    @ParameterizedTest
    @MethodSource("personDepartmentProvider")
    void groupByDepartment_shouldGroup(List<Person> input, Map<String, List<Person>> expected) {
        assertEquals(expected, groupByDepartment(input));
    }

    @ParameterizedTest
    @MethodSource("personNamesProvider")
    void groupByDepartmentNames_shouldGroupNames(List<Person> input, Map<String, List<String>> expected) {
        assertEquals(expected, groupByDepartmentNames(input));
    }

    @ParameterizedTest
    @MethodSource("sumAgeProvider")
    void groupByDepartmentSumAge_shouldSum(List<Person> input, Map<String, Integer> expected) {
        assertEquals(expected, groupByDepartmentSumAge(input));
    }

    @ParameterizedTest
    @MethodSource("joinProvider")
    void joinWithDelimiter_shouldJoin(List<String> input, String delimiter, String expected) {
        assertEquals(expected, joinWithDelimiter(input, delimiter));
    }

    @ParameterizedTest
    @MethodSource("joinPrefixSuffixProvider")
    void joinWithPrefixSuffix_shouldJoin(List<String> input, String delim, String prefix, String suffix, String expected) {
        assertEquals(expected, joinWithPrefixSuffix(input, delim, prefix, suffix));
    }

    @ParameterizedTest
    @MethodSource("statisticsProvider")
    void getStatistics_shouldGetStats(List<Integer> input, long count, long sum, int min, int max) {
        IntSummaryStatistics stats = getStatistics(input);
        assertEquals(count, stats.getCount());
        assertEquals(sum, stats.getSum());
        assertEquals(min, stats.getMin());
        assertEquals(max, stats.getMax());
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void reduceToProduct_shouldMultiply(List<Integer> input, int expected) {
        assertEquals(expected, reduceToProduct(input));
    }

    @ParameterizedTest
    @MethodSource("treeSetProvider")
    void collectToTreeSet_shouldSort(List<String> input, List<String> expected) {
        Set<String> result = collectToTreeSet(input);
        assertIterableEquals(expected, result);
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> groupByLengthProvider() {
        return Stream.of(
            Arguments.of(
                List.of("cat", "dog", "elephant", "ant"),
                Map.of(3, List.of("cat", "dog", "ant"), 8, List.of("elephant"))
            ),
            Arguments.of(
                List.of("a", "bb", "ccc"),
                Map.of(1, List.of("a"), 2, List.of("bb"), 3, List.of("ccc"))
            )
        );
    }

    private static Stream<Arguments> groupByFirstLetterProvider() {
        return Stream.of(
            Arguments.of(
                List.of("apple", "apricot", "banana", "blueberry"),
                Map.of('a', List.of("apple", "apricot"), 'b', List.of("banana", "blueberry"))
            )
        );
    }

    private static Stream<Arguments> partitionProvider() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2, 3, 4, 5, 6),
                Map.of(true, List.of(2, 4, 6), false, List.of(1, 3, 5))
            )
        );
    }

    private static Stream<Arguments> groupCountProvider() {
        return Stream.of(
            Arguments.of(
                List.of("cat", "dog", "elephant", "ant", "bee"),
                Map.of(3, 3L, 8, 1L)
            )
        );
    }

    private static Stream<Arguments> personDepartmentProvider() {
        Person p1 = new Person("Alice", 30, "IT");
        Person p2 = new Person("Bob", 25, "IT");
        Person p3 = new Person("Charlie", 35, "HR");
        return Stream.of(
            Arguments.of(
                List.of(p1, p2, p3),
                Map.of("IT", List.of(p1, p2), "HR", List.of(p3))
            )
        );
    }

    private static Stream<Arguments> personNamesProvider() {
        Person p1 = new Person("Alice", 30, "IT");
        Person p2 = new Person("Bob", 25, "IT");
        Person p3 = new Person("Charlie", 35, "HR");
        return Stream.of(
            Arguments.of(
                List.of(p1, p2, p3),
                Map.of("IT", List.of("Alice", "Bob"), "HR", List.of("Charlie"))
            )
        );
    }

    private static Stream<Arguments> sumAgeProvider() {
        return Stream.of(
            Arguments.of(
                List.of(new Person("Alice", 30, "IT"), new Person("Bob", 25, "IT"), new Person("Charlie", 35, "HR")),
                Map.of("IT", 55, "HR", 35)
            )
        );
    }

    private static Stream<Arguments> joinProvider() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c"), ", ", "a, b, c"),
            Arguments.of(List.of("hello", "world"), " ", "hello world")
        );
    }

    private static Stream<Arguments> joinPrefixSuffixProvider() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c"), ", ", "[", "]", "[a, b, c]"),
            Arguments.of(List.of("1", "2", "3"), "-", "(", ")", "(1-2-3)")
        );
    }

    private static Stream<Arguments> statisticsProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), 5L, 15L, 1, 5),
            Arguments.of(List.of(10, 20, 30), 3L, 60L, 10, 30)
        );
    }

    private static Stream<Arguments> productProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4), 24),
            Arguments.of(List.of(2, 5, 10), 100),
            Arguments.of(List.of(7), 7)
        );
    }

    private static Stream<Arguments> treeSetProvider() {
        return Stream.of(
            Arguments.of(List.of("banana", "apple", "cherry"), List.of("apple", "banana", "cherry")),
            Arguments.of(List.of("z", "a", "m"), List.of("a", "m", "z"))
        );
    }
}
