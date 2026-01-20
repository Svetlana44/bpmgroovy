package leetcode.hashmap;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.checkerframework.checker.units.qual.K;
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

        HashMap<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            target.merge(magazine.charAt(i), 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : resours.entrySet()) {
            if (target.containsKey(entry.getKey()) && target.get(entry.getKey()) >= entry.getValue()) {
                result = true;
            }
        }


        return result;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Majority Element (Easy)                                       │
    │ Верни элемент, встречающийся > n/2 раз (строго больше половины│
    │ длины массива). Например: n=7 → нужно > 3.5, т.е. минимум 4.  │
    │ Гарантируется, что такой элемент всегда существует.           │
    └──────────────────────────────────────────────────────────────┘
    */
    int majorityElement(int[] nums) {
        int count = nums.length / 2;
        HashMap<Integer, Integer> numsCount = new HashMap<>();
        for (int n : nums) {
            numsCount.merge(n, 1, Integer::sum);
        }
        for (int k : nums) {
            if (numsCount.get(k).intValue() > count) {
                return k;
            }
        }
        return 0;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Isomorphic Strings (Easy)                                     │
    │ Строки изоморфны, если можно заменить символы из s в t.        │
    └──────────────────────────────────────────────────────────────┘
    */
    boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>();  // s -> t
        Map<Character, Character> tToS = new HashMap<>();  // t -> s

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // Проверяем s -> t
            if (sToT.containsKey(sc)) {
                if (sToT.get(sc) != tc) return false;  // 'o' уже -> 'a', но нужен 'r'
            } else {
                sToT.put(sc, tc);
            }

            // Проверяем t -> s (чтобы разные символы s не указывали на один tc)
            if (tToS.containsKey(tc)) {
                if (tToS.get(tc) != sc) return false;
            } else {
                tToS.put(tc, sc);
            }
        }

        return true;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find the Difference (Easy)                                    │
    │ t — это s с добавленным символом, верни этот символ.          │
    └──────────────────────────────────────────────────────────────┘
    */
    char findTheDifference(String s, String t) {
        char res = ' ';
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tMap.merge(t.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < s.length(); i++) {
            sMap.merge(s.charAt(i), 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character tKey = entry.getKey();

            if (!sMap.containsKey(tKey)) {
                return (char) entry.getKey();
            }
            if (sMap.get(tKey) < tMap.get(tKey)) {
                return (char) entry.getKey();
            }
        }
        return res;
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Intersection of Two Arrays (Easy)                             │
    │ Верни уникальные элементы пересечения.                        │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            list1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (list1.contains(nums2[i])) {
                set.add((Integer) nums2[i]);
            }
        }


        return set.stream()
                .mapToInt(i -> (int) i)
                .toArray();
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
