package leetcode.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
class ArrayEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Two Sum (Easy)                                                │
    │ Дан массив nums и target. Верни индексы двух чисел,            │
    │ сумма которых равна target. Ровно одно решение.               │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] twoSum(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Best Time to Buy and Sell Stock (Easy)                        │
    │ Дан массив цен. Верни максимальную прибыль за одну сделку.     │
    └──────────────────────────────────────────────────────────────┘
    */
    int maxProfit(int[] prices) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Contains Duplicate (Easy)                                     │
    │ Верни true, если есть дубликаты.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean containsDuplicate(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Missing Number (Easy)                                         │
    │ Дан массив [0..n] без одного числа. Найди пропущенное.        │
    └──────────────────────────────────────────────────────────────┘
    */
    int missingNumber(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Move Zeroes (Easy)                                            │
    │ Перемести все нули в конец, сохрани порядок остальных.        │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] moveZeroes(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("twoSumProvider")
    void twoSum_shouldReturnIndices(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, twoSum(nums, target));
    }

    @ParameterizedTest
    @MethodSource("maxProfitProvider")
    void maxProfit_shouldReturnMaxProfit(int[] prices, int expected) {
        assertEquals(expected, maxProfit(prices));
    }

    @ParameterizedTest
    @MethodSource("containsDuplicateProvider")
    void containsDuplicate_shouldDetect(int[] nums, boolean expected) {
        assertEquals(expected, containsDuplicate(nums));
    }

    @ParameterizedTest
    @MethodSource("missingNumberProvider")
    void missingNumber_shouldFindMissing(int[] nums, int expected) {
        assertEquals(expected, missingNumber(nums));
    }

    @ParameterizedTest
    @MethodSource("moveZeroesProvider")
    void moveZeroes_shouldMoveZeros(int[] nums, int[] expected) {
        assertArrayEquals(expected, moveZeroes(nums));
    }

    @Test
    void moveZeroes_shouldPreserveNonZeroOrder() {
        int[] nums = {0, 1, 0, 3, 12};
        int[] result = moveZeroes(nums);
        int[] nonZero = Arrays.stream(result).filter(n -> n != 0).toArray();
        assertArrayEquals(new int[] {1, 3, 12}, nonZero);
        assertTrue(result.length == nums.length);
    }

    private static Stream<Arguments> twoSumProvider() {
        return Stream.of(
            Arguments.of(new int[] {2, 7, 11, 15}, 9, new int[] {0, 1}),
            Arguments.of(new int[] {3, 2, 4}, 6, new int[] {1, 2}),
            Arguments.of(new int[] {3, 3}, 6, new int[] {0, 1})
        );
    }

    private static Stream<Arguments> maxProfitProvider() {
        return Stream.of(
            Arguments.of(new int[] {7, 1, 5, 3, 6, 4}, 5),
            Arguments.of(new int[] {7, 6, 4, 3, 1}, 0),
            Arguments.of(new int[] {1, 2, 3, 4, 5}, 4)
        );
    }

    private static Stream<Arguments> containsDuplicateProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 1}, true),
            Arguments.of(new int[] {1, 2, 3, 4}, false),
            Arguments.of(new int[] {1, 1, 1, 3, 3, 4}, true)
        );
    }

    private static Stream<Arguments> missingNumberProvider() {
        return Stream.of(
            Arguments.of(new int[] {3, 0, 1}, 2),
            Arguments.of(new int[] {0, 1}, 2),
            Arguments.of(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}, 8)
        );
    }

    private static Stream<Arguments> moveZeroesProvider() {
        return Stream.of(
            Arguments.of(new int[] {0, 1, 0, 3, 12}, new int[] {1, 3, 12, 0, 0}),
            Arguments.of(new int[] {0, 0, 1}, new int[] {1, 0, 0}),
            Arguments.of(new int[] {1, 2, 3}, new int[] {1, 2, 3})
        );
    }
}
