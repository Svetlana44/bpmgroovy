package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на flatMap и обработку вложенных структур.
 * Очень частый вопрос на собеседованиях - "Чем отличается map от flatMap?"
 */
@Tag("leetcode")
class FlatMapStreamTasks {

    // Вспомогательные record для задач
    record Order(String customer, List<String> items) {}
    record Department(String name, List<Employee> employees) {}
    record Employee(String name, List<String> skills) {}

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Flatten List of Lists                                        │
    │ Преобразуй List<List<Integer>> в List<Integer>.              │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> flattenLists(List<List<Integer>> lists) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Flatten and Filter                                           │
    │ Разверни вложенные списки и оставь только чётные числа.      │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> flattenAndFilterEven(List<List<Integer>> lists) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Words                                                │
    │ Получи все слова из списка предложений.                      │
    │ Каждое предложение - это строка слов через пробел.           │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> getAllWords(List<String> sentences) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Characters                                           │
    │ Получи все уникальные символы из списка строк.               │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<Character> getAllUniqueChars(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Items from Orders                                    │
    │ Получи все товары из всех заказов.                           │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> getAllItems(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get Unique Items                                             │
    │ Получи уникальные товары из всех заказов.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    Set<String> getUniqueItems(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get All Skills from Departments                              │
    │ Получи все навыки сотрудников всех отделов (двойной flatMap).│
    └──────────────────────────────────────────────────────────────┘
    */
    Set<String> getAllSkills(List<Department> departments) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Pair Elements                                                │
    │ Создай все пары (i, j) где i из первого списка, j из второго.│
    │ Результат: List of "i-j" строк.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> pairElements(List<Integer> list1, List<Integer> list2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ FlatMap with Index                                           │
    │ Разверни списки и добавь индекс к каждому элементу.          │
    │ Формат: "index:value".                                       │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> flatMapWithIndex(List<List<String>> lists) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Split and Collect                                            │
    │ Разбей каждую строку по разделителю и собери в один список.  │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> splitAndCollect(List<String> strings, String delimiter) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Map to Values and Flatten                                    │
    │ Получи все значения из Map<String, List<Integer>>.           │
    └──────────────────────────────────────────────────────────────┘
    */
    List<Integer> flattenMapValues(Map<String, List<Integer>> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ FlatMap Optional                                             │
    │ Отфильтруй null строки, раздели на слова и собери в список.  │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> flatMapNullSafe(List<String> strings) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Count Items per Customer                                     │
    │ Посчитай количество товаров у каждого клиента.               │
    └──────────────────────────────────────────────────────────────┘
    */
    Map<String, Integer> countItemsPerCustomer(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("flattenProvider")
    void flattenLists_shouldFlatten(List<List<Integer>> input, List<Integer> expected) {
        assertIterableEquals(expected, flattenLists(input));
    }

    @ParameterizedTest
    @MethodSource("flattenFilterProvider")
    void flattenAndFilterEven_shouldFlattenAndFilter(List<List<Integer>> input, List<Integer> expected) {
        assertIterableEquals(expected, flattenAndFilterEven(input));
    }

    @ParameterizedTest
    @MethodSource("wordsProvider")
    void getAllWords_shouldGetWords(List<String> input, List<String> expected) {
        assertIterableEquals(expected, getAllWords(input));
    }

    @ParameterizedTest
    @MethodSource("charsProvider")
    void getAllUniqueChars_shouldGetChars(List<String> input, Set<Character> expected) {
        assertEquals(expected, getAllUniqueChars(input));
    }

    @ParameterizedTest
    @MethodSource("ordersProvider")
    void getAllItems_shouldGetItems(List<Order> input, List<String> expected) {
        assertIterableEquals(expected, getAllItems(input));
    }

    @ParameterizedTest
    @MethodSource("uniqueItemsProvider")
    void getUniqueItems_shouldGetUnique(List<Order> input, Set<String> expected) {
        assertEquals(expected, getUniqueItems(input));
    }

    @ParameterizedTest
    @MethodSource("skillsProvider")
    void getAllSkills_shouldGetSkills(List<Department> input, Set<String> expected) {
        assertEquals(expected, getAllSkills(input));
    }

    @ParameterizedTest
    @MethodSource("pairProvider")
    void pairElements_shouldPair(List<Integer> list1, List<Integer> list2, List<String> expected) {
        assertIterableEquals(expected, pairElements(list1, list2));
    }

    @ParameterizedTest
    @MethodSource("splitProvider")
    void splitAndCollect_shouldSplit(List<String> input, String delim, List<String> expected) {
        assertIterableEquals(expected, splitAndCollect(input, delim));
    }

    @ParameterizedTest
    @MethodSource("mapValuesProvider")
    void flattenMapValues_shouldFlatten(Map<String, List<Integer>> input, List<Integer> expected) {
        List<Integer> result = flattenMapValues(input);
        assertEquals(expected.size(), result.size());
        assertEquals(Set.copyOf(expected), Set.copyOf(result));
    }

    @ParameterizedTest
    @MethodSource("countItemsProvider")
    void countItemsPerCustomer_shouldCount(List<Order> input, Map<String, Integer> expected) {
        assertEquals(expected, countItemsPerCustomer(input));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> flattenProvider() {
        return Stream.of(
            Arguments.of(
                List.of(List.of(1, 2), List.of(3, 4), List.of(5)),
                List.of(1, 2, 3, 4, 5)
            ),
            Arguments.of(
                List.of(List.of(1), List.of(), List.of(2, 3)),
                List.of(1, 2, 3)
            )
        );
    }

    private static Stream<Arguments> flattenFilterProvider() {
        return Stream.of(
            Arguments.of(
                List.of(List.of(1, 2, 3), List.of(4, 5, 6)),
                List.of(2, 4, 6)
            ),
            Arguments.of(
                List.of(List.of(1, 3, 5), List.of(7, 9)),
                List.of()
            )
        );
    }

    private static Stream<Arguments> wordsProvider() {
        return Stream.of(
            Arguments.of(
                List.of("hello world", "java stream"),
                List.of("hello", "world", "java", "stream")
            ),
            Arguments.of(
                List.of("one", "two three"),
                List.of("one", "two", "three")
            )
        );
    }

    private static Stream<Arguments> charsProvider() {
        return Stream.of(
            Arguments.of(
                List.of("abc", "bcd"),
                Set.of('a', 'b', 'c', 'd')
            ),
            Arguments.of(
                List.of("aaa", "bbb"),
                Set.of('a', 'b')
            )
        );
    }

    private static Stream<Arguments> ordersProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Order("Alice", List.of("laptop", "mouse")),
                    new Order("Bob", List.of("keyboard"))
                ),
                List.of("laptop", "mouse", "keyboard")
            )
        );
    }

    private static Stream<Arguments> uniqueItemsProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Order("Alice", List.of("laptop", "mouse")),
                    new Order("Bob", List.of("laptop", "keyboard"))
                ),
                Set.of("laptop", "mouse", "keyboard")
            )
        );
    }

    private static Stream<Arguments> skillsProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Department("IT", List.of(
                        new Employee("Alice", List.of("Java", "Python")),
                        new Employee("Bob", List.of("Java", "Go"))
                    )),
                    new Department("HR", List.of(
                        new Employee("Charlie", List.of("Excel", "Python"))
                    ))
                ),
                Set.of("Java", "Python", "Go", "Excel")
            )
        );
    }

    private static Stream<Arguments> pairProvider() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of("1-3", "1-4", "2-3", "2-4")
            )
        );
    }

    private static Stream<Arguments> splitProvider() {
        return Stream.of(
            Arguments.of(
                List.of("a,b,c", "d,e"),
                ",",
                List.of("a", "b", "c", "d", "e")
            ),
            Arguments.of(
                List.of("1-2", "3-4-5"),
                "-",
                List.of("1", "2", "3", "4", "5")
            )
        );
    }

    private static Stream<Arguments> mapValuesProvider() {
        return Stream.of(
            Arguments.of(
                Map.of("a", List.of(1, 2), "b", List.of(3, 4)),
                List.of(1, 2, 3, 4)
            )
        );
    }

    private static Stream<Arguments> countItemsProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Order("Alice", List.of("laptop", "mouse")),
                    new Order("Bob", List.of("keyboard")),
                    new Order("Alice", List.of("monitor"))
                ),
                Map.of("Alice", 3, "Bob", 1)
            )
        );
    }
}
