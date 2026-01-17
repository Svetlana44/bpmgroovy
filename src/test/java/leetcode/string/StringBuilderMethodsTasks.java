package leetcode.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class StringBuilderMethodsTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Append Exercise                                              │
    │ Объедини все строки из массива в одну.                      │
    └──────────────────────────────────────────────────────────────┘
    */
    String joinStrings(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Insert Exercise                                              │
    │ Вставь строку insertStr в позицию index строки s.           │
    └──────────────────────────────────────────────────────────────┘
    */
    String insertString(String s, int index, String insertStr) {
        return new StringBuilder(s)
                .insert(index, insertStr)
                .toString();
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Delete Exercise                                              │
    │ Удали символы с позиции start до end (не включая end).      │
    └──────────────────────────────────────────────────────────────┘
    */
    String deleteSubstring(String s, int start, int end) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ DeleteCharAt Exercise                                        │
    │ Удали символ на позиции index.                               │
    └──────────────────────────────────────────────────────────────┘
    */
    String deleteCharAt(String s, int index) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Replace Exercise                                             │
    │ Замени подстроку с позиции start до end на replacement.     │
    └──────────────────────────────────────────────────────────────┘
    */
    String replaceSubstring(String s, int start, int end, String replacement) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Reverse Exercise                                             │
    │ Переверни строку.                                            │
    └──────────────────────────────────────────────────────────────┘
    */
    String reverseString(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Length Exercise                                              │
    │ Верни длину строки.                                           │
    └──────────────────────────────────────────────────────────────┘
    */
    int getLength(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Capacity Exercise                                            │
    │ Верни ёмкость буфера для строки s.                            │
    └──────────────────────────────────────────────────────────────┘
    */
    int getCapacity(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Complex Exercise: Build String with Multiple Operations      │
    │ Построй строку: добавь prefix, вставь middle в позицию 5,    │
    │ замени последние 3 символа на suffix.                         │
    └──────────────────────────────────────────────────────────────┘
    */
    String buildComplexString(String prefix, String middle, String suffix) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("joinStringsProvider")
    void joinStrings_shouldConcatenate(String[] input, String expected) {
        assertEquals(expected, joinStrings(input));
    }

    @ParameterizedTest
    @MethodSource("insertStringProvider")
    void insertString_shouldInsert(String s, int index, String insertStr, String expected) {
        assertEquals(expected, insertString(s, index, insertStr));
    }

    @ParameterizedTest
    @MethodSource("deleteSubstringProvider")
    void deleteSubstring_shouldDelete(String s, int start, int end, String expected) {
        assertEquals(expected, deleteSubstring(s, start, end));
    }

    @ParameterizedTest
    @MethodSource("deleteCharAtProvider")
    void deleteCharAt_shouldDeleteChar(String s, int index, String expected) {
        assertEquals(expected, deleteCharAt(s, index));
    }

    @ParameterizedTest
    @MethodSource("replaceSubstringProvider")
    void replaceSubstring_shouldReplace(String s, int start, int end, String replacement, String expected) {
        assertEquals(expected, replaceSubstring(s, start, end, replacement));
    }

    @ParameterizedTest
    @MethodSource("reverseStringProvider")
    void reverseString_shouldReverse(String input, String expected) {
        assertEquals(expected, reverseString(input));
    }

    @ParameterizedTest
    @MethodSource("getLengthProvider")
    void getLength_shouldReturnLength(String input, int expected) {
        assertEquals(expected, getLength(input));
    }

    @ParameterizedTest
    @MethodSource("getCapacityProvider")
    void getCapacity_shouldReturnCapacity(String input) {
        int capacity = getCapacity(input);
        // Capacity всегда >= length + 16 (по умолчанию)
        assert capacity >= input.length() + 16 || capacity >= input.length();
    }

    @ParameterizedTest
    @MethodSource("buildComplexStringProvider")
    void buildComplexString_shouldBuild(String prefix, String middle, String suffix, String expected) {
        assertEquals(expected, buildComplexString(prefix, middle, suffix));
    }

    private static Stream<Arguments> joinStringsProvider() {
        return Stream.of(
                Arguments.of(new String[]{"a", "b", "c"}, "abc"),
                Arguments.of(new String[]{"Hello", " ", "World"}, "Hello World"),
                Arguments.of(new String[]{""}, ""),
                Arguments.of(new String[]{"1", "2", "3", "4", "5"}, "12345")
        );
    }

    private static Stream<Arguments> insertStringProvider() {
        return Stream.of(
                Arguments.of("Hello", 5, " World", "Hello World"),
                Arguments.of("abc", 1, "X", "aXbc"),
                Arguments.of("test", 0, "pre", "pretest"),
                Arguments.of("end", 3, "ing", "ending")
        );
    }

    private static Stream<Arguments> deleteSubstringProvider() {
        return Stream.of(
                Arguments.of("Hello World", 5, 11, "Hello"),
                Arguments.of("abcdef", 2, 4, "abef"),
                Arguments.of("test", 0, 2, "st"),
                Arguments.of("remove", 3, 6, "rem")
        );
    }

    private static Stream<Arguments> deleteCharAtProvider() {
        return Stream.of(
                Arguments.of("Hello", 1, "Hllo"),
                Arguments.of("abc", 0, "bc"),
                Arguments.of("test", 3, "tes"),
                Arguments.of("remove", 2, "reove")
        );
    }

    private static Stream<Arguments> replaceSubstringProvider() {
        return Stream.of(
                Arguments.of("Hello World", 6, 11, "Java", "Hello Java"),
                Arguments.of("abc", 1, 2, "X", "aXc"),
                Arguments.of("test", 0, 4, "new", "new"),
                Arguments.of("replace", 2, 5, "XXX", "reXXXace")
        );
    }

    private static Stream<Arguments> reverseStringProvider() {
        return Stream.of(
                Arguments.of("hello", "olleh"),
                Arguments.of("abc", "cba"),
                Arguments.of("a", "a"),
                Arguments.of("", "")
        );
    }

    private static Stream<Arguments> getLengthProvider() {
        return Stream.of(
                Arguments.of("hello", 5),
                Arguments.of("", 0),
                Arguments.of("test", 4),
                Arguments.of("a", 1)
        );
    }

    private static Stream<Arguments> getCapacityProvider() {
        return Stream.of(
                Arguments.of("hello"),
                Arguments.of(""),
                Arguments.of("test"),
                Arguments.of("a")
        );
    }

    private static Stream<Arguments> buildComplexStringProvider() {
        return Stream.of(
                Arguments.of("prefix", "MID", "END", "prefiMIDEND"),
                Arguments.of("abc", "X", "Z", "abcXZ"),
                Arguments.of("test", "middle", "suf", "testmiddleuf")
        );
    }
}
