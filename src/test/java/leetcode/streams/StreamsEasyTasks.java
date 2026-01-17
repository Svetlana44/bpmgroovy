package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class StreamsEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Running Sum of 1d Array (Easy)                               │
    │ Верни массив префиксных сумм.                                 │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] runningSum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sort Array By Parity (Easy)                                  │
    │ Верни массив: все чётные перед нечётными.                     │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] sortArrayByParity(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Maximum Number of Words Found in Sentences (Easy)            │
    │ Верни максимум слов в предложениях.                           │
    └──────────────────────────────────────────────────────────────┘
    */
    int mostWordsFound(String[] sentences) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Summary Ranges (Easy)                                        │
    │ Верни диапазоны подряд идущих чисел.                          │
    └──────────────────────────────────────────────────────────────┘
    */
    List<String> summaryRanges(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Pivot Index (Easy)                                      │
    │ Верни индекс, где сумма слева равна сумме справа.             │
    └──────────────────────────────────────────────────────────────┘
    */
    int pivotIndex(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Pascal's Triangle (Easy)                                     │
    │ Верни первые numRows строк треугольника Паскаля.              │
    └──────────────────────────────────────────────────────────────┘
    */
    List<List<Integer>> generate(int numRows) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Product of Array Except Self (Medium)                        │
    │ Верни массив произведений, исключая текущий элемент.          │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] productExceptSelf(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Subarray Sum Equals K (Medium)                               │
    │ Верни число подмассивов с суммой k.                           │
    └──────────────────────────────────────────────────────────────┘
    */
    int subarraySum(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("runningSumProvider")
    void runningSum_shouldCalculate(int[] nums, int[] expected) {
        assertArrayEquals(expected, runningSum(nums));
    }

    @ParameterizedTest
    @MethodSource("parityProvider")
    void sortArrayByParity_shouldPartition(int[] nums, int[] expectedSorted) {
        int[] result = sortArrayByParity(nums);
        int[] sortedResult = Arrays.copyOf(result, result.length);
        Arrays.sort(sortedResult);
        Arrays.sort(expectedSorted);
        assertArrayEquals(expectedSorted, sortedResult);
        assertEvenFirst(result);
    }

    @ParameterizedTest
    @MethodSource("wordsProvider")
    void mostWordsFound_shouldReturnMax(String[] sentences, int expected) {
        assertEquals(expected, mostWordsFound(sentences));
    }

    @ParameterizedTest
    @MethodSource("rangesProvider")
    void summaryRanges_shouldReturnRanges(int[] nums, List<String> expected) {
        assertEquals(expected, summaryRanges(nums));
    }

    @ParameterizedTest
    @MethodSource("pivotProvider")
    void pivotIndex_shouldFind(int[] nums, int expected) {
        assertEquals(expected, pivotIndex(nums));
    }

    @ParameterizedTest
    @MethodSource("pascalProvider")
    void generate_shouldReturnTriangle(int numRows, List<List<Integer>> expected) {
        assertEquals(expected, generate(numRows));
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void productExceptSelf_shouldReturn(int[] nums, int[] expected) {
        assertArrayEquals(expected, productExceptSelf(nums));
    }

    @ParameterizedTest
    @MethodSource("subarrayProvider")
    void subarraySum_shouldCount(int[] nums, int k, int expected) {
        assertEquals(expected, subarraySum(nums, k));
    }

    private static void assertEvenFirst(int[] nums) {
        boolean seenOdd = false;
        for (int num : nums) {
            if (num % 2 == 0) {
                assertTrue(!seenOdd, "Found even number after odd block");
            } else {
                seenOdd = true;
            }
        }
    }

    private static Stream<Arguments> runningSumProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {1, 3, 6, 10}),
            Arguments.of(new int[] {1, 1, 1, 1, 1}, new int[] {1, 2, 3, 4, 5}),
            Arguments.of(new int[] {3, 1, 2, 10, 1}, new int[] {3, 4, 6, 16, 17})
        );
    }

    private static Stream<Arguments> parityProvider() {
        return Stream.of(
            Arguments.of(new int[] {3, 1, 2, 4}, new int[] {1, 2, 3, 4}),
            Arguments.of(new int[] {0}, new int[] {0}),
            Arguments.of(new int[] {2, 4, 6, 1, 3}, new int[] {1, 2, 3, 4, 6})
        );
    }

    private static Stream<Arguments> wordsProvider() {
        return Stream.of(
            Arguments.of(new String[] {"alice and bob love leetcode", "i think so too", "this is great thanks very much"}, 6),
            Arguments.of(new String[] {"please wait", "continue to fight", "continue to win"}, 3),
            Arguments.of(new String[] {"single"}, 1)
        );
    }

    private static Stream<Arguments> rangesProvider() {
        return Stream.of(
            Arguments.of(new int[] {0, 1, 2, 4, 5, 7}, List.of("0->2", "4->5", "7")),
            Arguments.of(new int[] {0, 2, 3, 4, 6, 8, 9}, List.of("0", "2->4", "6", "8->9")),
            Arguments.of(new int[] {}, new ArrayList<>())
        );
    }

    private static Stream<Arguments> pivotProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 7, 3, 6, 5, 6}, 3),
            Arguments.of(new int[] {1, 2, 3}, -1),
            Arguments.of(new int[] {2, 1, -1}, 0)
        );
    }

    private static Stream<Arguments> pascalProvider() {
        return Stream.of(
            Arguments.of(1, List.of(List.of(1))),
            Arguments.of(2, List.of(List.of(1), List.of(1, 1))),
            Arguments.of(5, List.of(
                List.of(1),
                List.of(1, 1),
                List.of(1, 2, 1),
                List.of(1, 3, 3, 1),
                List.of(1, 4, 6, 4, 1)
            ))
        );
    }

    private static Stream<Arguments> productProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {24, 12, 8, 6}),
            Arguments.of(new int[] {-1, 1, 0, -3, 3}, new int[] {0, 0, 9, 0, 0}),
            Arguments.of(new int[] {2, 3}, new int[] {3, 2})
        );
    }

    private static Stream<Arguments> subarrayProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 1, 1}, 2, 2),
            Arguments.of(new int[] {1, 2, 3}, 3, 2),
            Arguments.of(new int[] {-1, -1, 1}, 0, 1)
        );
    }
}
