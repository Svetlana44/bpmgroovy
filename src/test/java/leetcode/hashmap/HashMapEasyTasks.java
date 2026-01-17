package leetcode.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("leetcode")
class HashMapEasyTasks {

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Ransom Note (Easy)                                            │
    │ Верни true, если ransom можно составить из magazine.          │
    │ Примеры:                                                      │
    │   ransom="aa", magazine="aab" → true                          │
    │   ransom="aa", magazine="ab"  → false                         │
    │ Идея: посчитать частоты (HashMap). Массивом тоже можно        │
    │ при ограниченном алфавите.                                    │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean canConstruct(String ransomNote, String magazine) {

        boolean result = false;
        if (ransomNote.length() > magazine.length()) {
            return result;
        }
        HashMap<Character, Integer> resours = new HashMap<Character, Integer>();
        for (int i = 0; i < ransomNote.length(); i++) {
            resours.merge(ransomNote.charAt(i), 1, Integer::sum);

        }

        HashMap<Character,Integer>target=new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            target.merge(magazine.charAt(i),1,Integer::sum);
        }
        for(Map.Entry<Character,Integer> entry:resours.entrySet()){
            if(target.containsKey(entry.getKey()) && target.get(entry.getKey()) >= entry.getValue()){
                result = true;
            } 
        }


        return result;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Majority Element (Easy)                                       │
    │ Верни элемент, встречающийся > n/2 раз.                       │
    └──────────────────────────────────────────────────────────────┘
    */
    int majorityElement(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Isomorphic Strings (Easy)                                     │
    │ Строки изоморфны, если можно заменить символы из s в t.        │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isIsomorphic(String s, String t) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find the Difference (Easy)                                    │
    │ t — это s с добавленным символом, верни этот символ.          │
    └──────────────────────────────────────────────────────────────┘
    */
    char findTheDifference(String s, String t) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Intersection of Two Arrays (Easy)                             │
    │ Верни уникальные элементы пересечения.                        │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] intersection(int[] nums1, int[] nums2) {
        throw new UnsupportedOperationException("TODO");
    }

    @ParameterizedTest
    @MethodSource("ransomProvider")
    void canConstruct_shouldDetect(String ransom, String magazine, boolean expected) {
        assertEquals(expected, canConstruct(ransom, magazine));
    }

    @ParameterizedTest
    @MethodSource("majorityProvider")
    void majorityElement_shouldFind(int[] nums, int expected) {
        assertEquals(expected, majorityElement(nums));
    }

    @ParameterizedTest
    @MethodSource("isomorphicProvider")
    void isIsomorphic_shouldDetect(String s, String t, boolean expected) {
        assertEquals(expected, isIsomorphic(s, t));
    }

    @ParameterizedTest
    @MethodSource("differenceProvider")
    void findTheDifference_shouldReturnChar(String s, String t, char expected) {
        assertEquals(expected, findTheDifference(s, t));
    }

    @ParameterizedTest
    @MethodSource("intersectionProvider")
    void intersection_shouldReturnUnique(int[] nums1, int[] nums2, int[] expected) {
        int[] result = intersection(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> ransomProvider() {
        return Stream.of(
                Arguments.of("a", "b", false),
                Arguments.of("aa", "ab", false),
                Arguments.of("aa", "aab", true)
        );
    }

    private static Stream<Arguments> majorityProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 3}, 3),
                Arguments.of(new int[]{2, 2, 1, 1, 1, 2, 2}, 2),
                Arguments.of(new int[]{1}, 1)
        );
    }

    private static Stream<Arguments> isomorphicProvider() {
        return Stream.of(
                Arguments.of("egg", "add", true),
                Arguments.of("foo", "bar", false),
                Arguments.of("paper", "title", true)
        );
    }

    private static Stream<Arguments> differenceProvider() {
        return Stream.of(
                Arguments.of("abcd", "abcde", 'e'),
                Arguments.of("", "y", 'y'),
                Arguments.of("a", "aa", 'a')
        );
    }

    private static Stream<Arguments> intersectionProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2}),
                Arguments.of(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{4, 9}),
                Arguments.of(new int[]{1}, new int[]{2}, new int[]{})
        );
    }
}
