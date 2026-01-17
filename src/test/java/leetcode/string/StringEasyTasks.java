package leetcode.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class StringEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Valid Anagram (Easy)                                          │
    │ Верни true, если t — анаграмма s.                             │
    │ Анаграмма — это строка, полученная перестановкой символов     │
    │ другой строки без добавления или удаления символов.           │
    │ Примеры входных данных и ожидаемых результатов — в тестах.     │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isAnagram(String s, String t) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Reverse String (Easy)                                         │
    │ Верни строку в обратном порядке.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    String reverseString(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ First Unique Character (Easy)                                 │
    │ Верни индекс первого уникального символа или -1.              │
    └──────────────────────────────────────────────────────────────┘
    */
    int firstUniqChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Valid Palindrome (Easy)                                       │
    │ Игнорируй регистр и не алфавитно-цифровые символы.             │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Longest Common Prefix (Easy)                                  │
    │ Верни общий префикс массива строк.                            │
    └──────────────────────────────────────────────────────────────┘
    */
    String longestCommonPrefix(String[] strs) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("anagramProvider")
    void isAnagram_shouldDetect(String s, String t, boolean expected) {
        assertEquals(expected, isAnagram(s, t));
    }

    @ParameterizedTest
    @MethodSource("reverseProvider")
    void reverseString_shouldReverse(String input, String expected) {
        assertEquals(expected, reverseString(input));
    }

    @ParameterizedTest
    @MethodSource("firstUniqueProvider")
    void firstUniqChar_shouldReturnIndex(String input, int expected) {
        assertEquals(expected, firstUniqChar(input));
    }

    @ParameterizedTest
    @MethodSource("palindromeProvider")
    void isPalindrome_shouldDetect(String input, boolean expected) {
        assertEquals(expected, isPalindrome(input));
    }

    @ParameterizedTest
    @MethodSource("prefixProvider")
    void longestCommonPrefix_shouldReturnPrefix(String[] input, String expected) {
        assertEquals(expected, longestCommonPrefix(input));
    }

    @Test
    void reverseString_shouldHandleEmpty() {
        assertTrue(reverseString("").isEmpty());
    }

    private static Stream<Arguments> anagramProvider() {
        return Stream.of(
            Arguments.of("anagram", "nagaram", true),
            Arguments.of("rat", "car", false),
            Arguments.of("aacc", "ccac", false)
        );
    }

    private static Stream<Arguments> reverseProvider() {
        return Stream.of(
            Arguments.of("hello", "olleh"),
            Arguments.of("a", "a"),
            Arguments.of("ab", "ba")
        );
    }

    private static Stream<Arguments> firstUniqueProvider() {
        return Stream.of(
            Arguments.of("leetcode", 0),
            Arguments.of("loveleetcode", 2),
            Arguments.of("aabb", -1)
        );
    }

    private static Stream<Arguments> palindromeProvider() {
        return Stream.of(
            Arguments.of("A man, a plan, a canal: Panama", true),
            Arguments.of("race a car", false),
            Arguments.of(" ", true)
        );
    }

    private static Stream<Arguments> prefixProvider() {
        return Stream.of(
            Arguments.of(new String[] {"flower", "flow", "flight"}, "fl"),
            Arguments.of(new String[] {"dog", "racecar", "car"}, ""),
            Arguments.of(new String[] {"interview", "internet", "internal"}, "inte")
        );
    }
}
