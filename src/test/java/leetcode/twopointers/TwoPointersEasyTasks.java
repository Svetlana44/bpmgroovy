package leetcode.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class TwoPointersEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Remove Duplicates from Sorted Array (Easy)                    │
    │ Удали дубликаты на месте и верни новую длину.                 │
    └──────────────────────────────────────────────────────────────┘
    */
    int removeDuplicates(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Merge Sorted Array (Easy)                                     │
    │ nums1 содержит m элементов и место под n из nums2.            │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Squares of a Sorted Array (Easy)                              │
    │ Верни отсортированные квадраты.                               │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] sortedSquares(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Remove Element (Easy)                                         │
    │ Удали все val на месте и верни новую длину.                   │
    └──────────────────────────────────────────────────────────────┘
    */
    int removeElement(int[] nums, int val) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("removeDuplicatesProvider")
    void removeDuplicates_shouldReturnLength(int[] nums, int expectedLength, int[] expectedPrefix) {
        int len = removeDuplicates(nums);
        assertEquals(expectedLength, len);
        assertArrayEquals(expectedPrefix, Arrays.copyOf(nums, len));
    }

    @ParameterizedTest
    @MethodSource("mergeProvider")
    void mergeSortedArray_shouldMerge(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        assertArrayEquals(expected, mergeSortedArray(nums1, m, nums2, n));
    }

    @ParameterizedTest
    @MethodSource("squaresProvider")
    void sortedSquares_shouldReturnSortedSquares(int[] nums, int[] expected) {
        assertArrayEquals(expected, sortedSquares(nums));
    }

    @ParameterizedTest
    @MethodSource("removeElementProvider")
    void removeElement_shouldReturnLength(int[] nums, int val, int expectedLength, int[] expectedPrefix) {
        int len = removeElement(nums, val);
        assertEquals(expectedLength, len);
        assertArrayEquals(expectedPrefix, Arrays.copyOf(nums, len));
    }

    private static Stream<Arguments> removeDuplicatesProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 1, 2}, 2, new int[] {1, 2}),
            Arguments.of(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5, new int[] {0, 1, 2, 3, 4}),
            Arguments.of(new int[] {1}, 1, new int[] {1})
        );
    }

    private static Stream<Arguments> mergeProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3, new int[] {1, 2, 2, 3, 5, 6}),
            Arguments.of(new int[] {1}, 1, new int[] {}, 0, new int[] {1}),
            Arguments.of(new int[] {0}, 0, new int[] {1}, 1, new int[] {1})
        );
    }

    private static Stream<Arguments> squaresProvider() {
        return Stream.of(
            Arguments.of(new int[] {-4, -1, 0, 3, 10}, new int[] {0, 1, 9, 16, 100}),
            Arguments.of(new int[] {-7, -3, 2, 3, 11}, new int[] {4, 9, 9, 49, 121})
        );
    }

    private static Stream<Arguments> removeElementProvider() {
        return Stream.of(
            Arguments.of(new int[] {3, 2, 2, 3}, 3, 2, new int[] {2, 2}),
            Arguments.of(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2, 5, new int[] {0, 1, 3, 0, 4}),
            Arguments.of(new int[] {1}, 1, 0, new int[] {})
        );
    }
}
