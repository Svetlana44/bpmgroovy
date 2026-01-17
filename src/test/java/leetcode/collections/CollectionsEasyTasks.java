package leetcode.collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class CollectionsEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Contains Duplicate II (Easy)                                 │
    │ Верни true, если есть равные элементы на расстоянии <= k.     │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean containsNearbyDuplicate(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Happy Number (Easy)                                          │
    │ Верни true, если сумма квадратов цифр приводит к 1.           │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isHappy(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Jewels and Stones (Easy)                                     │
    │ Верни количество камней, которые являются драгоценными.       │
    └──────────────────────────────────────────────────────────────┘
    */
    int numJewelsInStones(String jewels, String stones) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Intersection of Two Arrays II (Easy)                         │
    │ Верни пересечение с учётом кратности.                         │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] intersect(int[] nums1, int[] nums2) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Word Pattern (Easy)                                          │
    │ Проверь соответствие шаблона и слов.                          │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean wordPattern(String pattern, String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Unique Email Addresses (Easy)                                │
    │ Верни количество уникальных адресов после нормализации.       │
    └──────────────────────────────────────────────────────────────┘
    */
    int numUniqueEmails(String[] emails) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Group Anagrams (Medium)                                      │
    │ Сгруппируй строки по анаграммам.                              │
    └──────────────────────────────────────────────────────────────┘
    */
    List<List<String>> groupAnagrams(String[] strs) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Top K Frequent Elements (Medium)                             │
    │ Верни k самых частых элементов.                               │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] topKFrequent(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("nearbyDuplicateProvider")
    void containsNearbyDuplicate_shouldDetect(int[] nums, int k, boolean expected) {
        assertEquals(expected, containsNearbyDuplicate(nums, k));
    }

    @ParameterizedTest
    @MethodSource("happyProvider")
    void isHappy_shouldDetect(int n, boolean expected) {
        assertEquals(expected, isHappy(n));
    }

    @ParameterizedTest
    @MethodSource("jewelsProvider")
    void numJewelsInStones_shouldCount(String jewels, String stones, int expected) {
        assertEquals(expected, numJewelsInStones(jewels, stones));
    }

    @ParameterizedTest
    @MethodSource("intersectProvider")
    void intersect_shouldReturnIntersection(int[] nums1, int[] nums2, int[] expected) {
        int[] result = intersect(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("patternProvider")
    void wordPattern_shouldValidate(String pattern, String s, boolean expected) {
        assertEquals(expected, wordPattern(pattern, s));
    }

    @ParameterizedTest
    @MethodSource("uniqueEmailProvider")
    void numUniqueEmails_shouldCount(String[] emails, int expected) {
        assertEquals(expected, numUniqueEmails(emails));
    }

    @ParameterizedTest
    @MethodSource("anagramProvider")
    void groupAnagrams_shouldGroup(String[] input, List<List<String>> expected) {
        assertEquals(normalizeGroups(expected), normalizeGroups(groupAnagrams(input)));
    }

    @ParameterizedTest
    @MethodSource("topKProvider")
    void topKFrequent_shouldReturn(int[] nums, int k, int[] expected) {
        int[] result = topKFrequent(nums, k);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    private static List<List<String>> normalizeGroups(List<List<String>> groups) {
        return groups.stream()
            .map(group -> {
                List<String> sorted = new ArrayList<>(group);
                sorted.sort(String::compareTo);
                return sorted;
            })
            .sorted((left, right) -> {
                if (left.isEmpty() && right.isEmpty()) {
                    return 0;
                }
                if (left.isEmpty()) {
                    return -1;
                }
                if (right.isEmpty()) {
                    return 1;
                }
                return left.get(0).compareTo(right.get(0));
            })
            .collect(Collectors.toList());
    }

    private static Stream<Arguments> nearbyDuplicateProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 1}, 3, true),
            Arguments.of(new int[] {1, 0, 1, 1}, 1, true),
            Arguments.of(new int[] {1, 2, 3, 1, 2, 3}, 2, false)
        );
    }

    private static Stream<Arguments> happyProvider() {
        return Stream.of(
            Arguments.of(19, true),
            Arguments.of(2, false),
            Arguments.of(1, true)
        );
    }

    private static Stream<Arguments> jewelsProvider() {
        return Stream.of(
            Arguments.of("aA", "aAAbbbb", 3),
            Arguments.of("z", "ZZ", 0),
            Arguments.of("bcd", "cdbbd", 4)
        );
    }

    private static Stream<Arguments> intersectProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 2, 1}, new int[] {2, 2}, new int[] {2, 2}),
            Arguments.of(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4}, new int[] {4, 9}),
            Arguments.of(new int[] {1}, new int[] {2}, new int[] {})
        );
    }

    private static Stream<Arguments> patternProvider() {
        return Stream.of(
            Arguments.of("abba", "dog cat cat dog", true),
            Arguments.of("abba", "dog cat cat fish", false),
            Arguments.of("aaaa", "dog cat cat dog", false)
        );
    }

    private static Stream<Arguments> uniqueEmailProvider() {
        return Stream.of(
            Arguments.of(new String[] {"test.email+alex@leetcode.com", "test.e.mail+bob@leetcode.com", "testemail+david@lee.tcode.com"}, 2),
            Arguments.of(new String[] {"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}, 3),
            Arguments.of(new String[] {"a+b@leetcode.com", "a@leetcode.com"}, 1)
        );
    }

    private static Stream<Arguments> anagramProvider() {
        return Stream.of(
            Arguments.of(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(
                    List.of("eat", "tea", "ate"),
                    List.of("tan", "nat"),
                    List.of("bat")
                )),
            Arguments.of(new String[] {""}, List.of(List.of(""))),
            Arguments.of(new String[] {"a"}, List.of(List.of("a")))
        );
    }

    private static Stream<Arguments> topKProvider() {
        return Stream.of(
            Arguments.of(new int[] {1, 1, 1, 2, 2, 3}, 2, new int[] {1, 2}),
            Arguments.of(new int[] {1}, 1, new int[] {1}),
            Arguments.of(new int[] {4, 4, 4, 5, 5, 6}, 2, new int[] {4, 5})
        );
    }
}
