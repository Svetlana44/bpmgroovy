package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на Optional и его связь со Stream API.
 * Optional - один из ключевых топиков на собеседованиях по Java 8+.
 */
@Tag("leetcode")
class OptionalStreamTasks {

    // Вспомогательные record для задач
    record User(String name, Optional<String> email) {}
    record Product(String name, Optional<Integer> price) {}

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find First Match                                             │
    │ Найди первый элемент > threshold или верни default.          │
    └──────────────────────────────────────────────────────────────┘
    */
    int findFirstOrDefault(List<Integer> numbers, int threshold, int defaultValue) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Any Negative                                            │
    │ Найди любое отрицательное число или верни Optional.empty().  │
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<Integer> findAnyNegative(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Optional Map                                                 │
    │ Если Optional содержит строку, верни её длину, иначе 0.      │
    └──────────────────────────────────────────────────────────────┘
    */
    int getStringLengthOrZero(Optional<String> optString) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Optional Filter                                              │
    │ Если число > 10, верни его, иначе Optional.empty().          │
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<Integer> filterGreaterThanTen(Optional<Integer> optNumber) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Optional FlatMap                                             │
    │ Если пользователь имеет email, верни его в верхнем регистре. │
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<String> getUpperCaseEmail(User user) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Chain Optional Operations                                    │
    │ Найди цену продукта, умножь на 2, если > 100 верни, иначе 0. │
    └──────────────────────────────────────────────────────────────┘
    */
    int getDoublePriceIfOver100(Product product) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Optional Or                                                  │
    │ Верни первое непустое значение или default.                  │
    └──────────────────────────────────────────────────────────────┘
    */
    String getFirstNonEmpty(Optional<String> opt1, Optional<String> opt2, String defaultValue) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Stream of Optionals                                          │
    │ Извлеки все непустые значения из списка Optional.            │
    │ Java 9+: можно использовать Optional.stream().               │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> extractPresent(List<Optional<String>> optionals) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find in Map                                                  │
    │ Найди значение по ключу в Map и преобразуй в Optional.       │
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<Integer> findInMap(Map<String, Integer> map, String key) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Reduce with Optional                                         │
    │ Найди максимум из списка через reduce (без начального знач.).│
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<Integer> findMaxWithReduce(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Optional IfPresentOrElse                                     │
    │ Если строка присутствует, верни в uppercase, иначе "N/A".    │
    └──────────────────────────────────────────────────────────────┘
    */
    String toUpperOrNA(Optional<String> optString) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Combine Optionals                                            │
    │ Если оба Optional присутствуют, верни их конкатенацию.       │
    └──────────────────────────────────────────────────────────────┘
    */
    Optional<String> combineIfBothPresent(Optional<String> opt1, Optional<String> opt2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Get Emails from Users                                        │
    │ Получи список email'ов всех пользователей (только непустые). │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> getValidEmails(List<User> users) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of Prices                                                │
    │ Посчитай сумму цен всех продуктов (только где цена указана). │
    └──────────────────────────────────────────────────────────────┘
    */
    int sumOfValidPrices(List<Product> products) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("findFirstProvider")
    void findFirstOrDefault_shouldFind(List<Integer> input, int threshold, int defaultVal, int expected) {
        assertEquals(expected, findFirstOrDefault(input, threshold, defaultVal));
    }

    @ParameterizedTest
    @MethodSource("findNegativeProvider")
    void findAnyNegative_shouldFind(List<Integer> input, boolean expectPresent) {
        Optional<Integer> result = findAnyNegative(input);
        assertEquals(expectPresent, result.isPresent());
        if (expectPresent) {
            assertEquals(true, result.get() < 0);
        }
    }

    @ParameterizedTest
    @MethodSource("stringLengthProvider")
    void getStringLengthOrZero_shouldReturn(Optional<String> input, int expected) {
        assertEquals(expected, getStringLengthOrZero(input));
    }

    @ParameterizedTest
    @MethodSource("filterTenProvider")
    void filterGreaterThanTen_shouldFilter(Optional<Integer> input, Optional<Integer> expected) {
        assertEquals(expected, filterGreaterThanTen(input));
    }

    @ParameterizedTest
    @MethodSource("emailProvider")
    void getUpperCaseEmail_shouldGet(User user, Optional<String> expected) {
        assertEquals(expected, getUpperCaseEmail(user));
    }

    @ParameterizedTest
    @MethodSource("productPriceProvider")
    void getDoublePriceIfOver100_shouldReturn(Product product, int expected) {
        assertEquals(expected, getDoublePriceIfOver100(product));
    }

    @ParameterizedTest
    @MethodSource("firstNonEmptyProvider")
    void getFirstNonEmpty_shouldReturn(Optional<String> opt1, Optional<String> opt2, String defaultVal, String expected) {
        assertEquals(expected, getFirstNonEmpty(opt1, opt2, defaultVal));
    }

    @ParameterizedTest
    @MethodSource("extractPresentProvider")
    void extractPresent_shouldExtract(List<Optional<String>> input, List<String> expected) {
        assertIterableEquals(expected, extractPresent(input));
    }

    @ParameterizedTest
    @MethodSource("findInMapProvider")
    void findInMap_shouldFind(Map<String, Integer> map, String key, Optional<Integer> expected) {
        assertEquals(expected, findInMap(map, key));
    }

    @ParameterizedTest
    @MethodSource("reduceMaxProvider")
    void findMaxWithReduce_shouldFindMax(List<Integer> input, Optional<Integer> expected) {
        assertEquals(expected, findMaxWithReduce(input));
    }

    @ParameterizedTest
    @MethodSource("toUpperNAProvider")
    void toUpperOrNA_shouldReturn(Optional<String> input, String expected) {
        assertEquals(expected, toUpperOrNA(input));
    }

    @ParameterizedTest
    @MethodSource("combineProvider")
    void combineIfBothPresent_shouldCombine(Optional<String> opt1, Optional<String> opt2, Optional<String> expected) {
        assertEquals(expected, combineIfBothPresent(opt1, opt2));
    }

    @ParameterizedTest
    @MethodSource("validEmailsProvider")
    void getValidEmails_shouldGet(List<User> users, List<String> expected) {
        assertIterableEquals(expected, getValidEmails(users));
    }

    @ParameterizedTest
    @MethodSource("sumPricesProvider")
    void sumOfValidPrices_shouldSum(List<Product> products, int expected) {
        assertEquals(expected, sumOfValidPrices(products));
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> findFirstProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 10, 15), 7, -1, 10),
            Arguments.of(List.of(1, 2, 3), 10, -1, -1),
            Arguments.of(List.of(), 5, 0, 0)
        );
    }

    private static Stream<Arguments> findNegativeProvider() {
        return Stream.of(
            Arguments.of(List.of(1, -2, 3, -4), true),
            Arguments.of(List.of(1, 2, 3), false),
            Arguments.of(List.of(), false)
        );
    }

    private static Stream<Arguments> stringLengthProvider() {
        return Stream.of(
            Arguments.of(Optional.of("hello"), 5),
            Arguments.of(Optional.empty(), 0),
            Arguments.of(Optional.of(""), 0)
        );
    }

    private static Stream<Arguments> filterTenProvider() {
        return Stream.of(
            Arguments.of(Optional.of(15), Optional.of(15)),
            Arguments.of(Optional.of(5), Optional.empty()),
            Arguments.of(Optional.empty(), Optional.empty())
        );
    }

    private static Stream<Arguments> emailProvider() {
        return Stream.of(
            Arguments.of(new User("Alice", Optional.of("alice@test.com")), Optional.of("ALICE@TEST.COM")),
            Arguments.of(new User("Bob", Optional.empty()), Optional.empty())
        );
    }

    private static Stream<Arguments> productPriceProvider() {
        return Stream.of(
            Arguments.of(new Product("Laptop", Optional.of(100)), 200),
            Arguments.of(new Product("Mouse", Optional.of(30)), 0),
            Arguments.of(new Product("Unknown", Optional.empty()), 0)
        );
    }

    private static Stream<Arguments> firstNonEmptyProvider() {
        return Stream.of(
            Arguments.of(Optional.of("first"), Optional.of("second"), "default", "first"),
            Arguments.of(Optional.empty(), Optional.of("second"), "default", "second"),
            Arguments.of(Optional.empty(), Optional.empty(), "default", "default")
        );
    }

    private static Stream<Arguments> extractPresentProvider() {
        return Stream.of(
            Arguments.of(
                List.of(Optional.of("a"), Optional.empty(), Optional.of("b")),
                List.of("a", "b")
            ),
            Arguments.of(
                List.of(Optional.empty(), Optional.empty()),
                List.of()
            )
        );
    }

    private static Stream<Arguments> findInMapProvider() {
        return Stream.of(
            Arguments.of(Map.of("a", 1, "b", 2), "a", Optional.of(1)),
            Arguments.of(Map.of("a", 1, "b", 2), "c", Optional.empty())
        );
    }

    private static Stream<Arguments> reduceMaxProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 3, 9, 2), Optional.of(9)),
            Arguments.of(List.of(-5, -1, -10), Optional.of(-1)),
            Arguments.of(List.of(), Optional.empty())
        );
    }

    private static Stream<Arguments> toUpperNAProvider() {
        return Stream.of(
            Arguments.of(Optional.of("hello"), "HELLO"),
            Arguments.of(Optional.empty(), "N/A")
        );
    }

    private static Stream<Arguments> combineProvider() {
        return Stream.of(
            Arguments.of(Optional.of("Hello"), Optional.of("World"), Optional.of("HelloWorld")),
            Arguments.of(Optional.of("Hello"), Optional.empty(), Optional.empty()),
            Arguments.of(Optional.empty(), Optional.of("World"), Optional.empty())
        );
    }

    private static Stream<Arguments> validEmailsProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new User("Alice", Optional.of("alice@test.com")),
                    new User("Bob", Optional.empty()),
                    new User("Charlie", Optional.of("charlie@test.com"))
                ),
                List.of("alice@test.com", "charlie@test.com")
            )
        );
    }

    private static Stream<Arguments> sumPricesProvider() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Product("A", Optional.of(100)),
                    new Product("B", Optional.empty()),
                    new Product("C", Optional.of(50))
                ),
                150
            )
        );
    }
}
