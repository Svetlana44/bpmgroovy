package testi.leetcodeMySelf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class Dubles {
    /*
    Example 1:
Input: nums = [1,2,3,1]
Output: true

Explanation:
The element 1 occurs at the indices 0 and 3.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
  */
    static int[] nums = {1, 2, 3, 1};
    static Set<Integer> hashSet = new HashSet<>();

    public static void main(String[] args) {
        hashSet = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        if (hashSet.size() != nums.length) {
            System.out.println("true");
        } else System.out.println("false");
    }

}
