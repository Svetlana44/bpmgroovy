package leetcode.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class StackEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Valid Parentheses (Easy)                                      │
    │ Верни true, если скобки корректно вложены.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isValidParentheses(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Backspace String Compare (Easy)                               │
    │ '#' означает удаление предыдущего символа.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean backspaceCompare(String s, String t) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Baseball Game (Easy)                                          │
    │ Операции: числа, C (remove), D (double), + (sum).             │
    └──────────────────────────────────────────────────────────────┘
    */
    int calPoints(String[] operations) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("parenthesesProvider")
    void isValidParentheses_shouldValidate(String input, boolean expected) {
        assertEquals(expected, isValidParentheses(input));
    }

    @ParameterizedTest
    @MethodSource("backspaceProvider")
    void backspaceCompare_shouldCompare(String s, String t, boolean expected) {
        assertEquals(expected, backspaceCompare(s, t));
    }

    @ParameterizedTest
    @MethodSource("baseballProvider")
    void calPoints_shouldCalculate(String[] operations, int expected) {
        assertEquals(expected, calPoints(operations));
    }

    private static Stream<Arguments> parenthesesProvider() {
        return Stream.of(
            Arguments.of("()", true),
            Arguments.of("()[]{}", true),
            Arguments.of("(]", false),
            Arguments.of("([)]", false)
        );
    }

    private static Stream<Arguments> backspaceProvider() {
        return Stream.of(
            Arguments.of("ab#c", "ad#c", true),
            Arguments.of("ab##", "c#d#", true),
            Arguments.of("a#c", "b", false)
        );
    }

    private static Stream<Arguments> baseballProvider() {
        return Stream.of(
            Arguments.of(new String[] {"5", "2", "C", "D", "+"}, 30),
            Arguments.of(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"}, 27),
            Arguments.of(new String[] {"1", "C"}, 0)
        );
    }
}
