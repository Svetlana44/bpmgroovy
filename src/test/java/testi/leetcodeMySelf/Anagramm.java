package testi.leetcodeMySelf;

import java.util.Arrays;

public class Anagramm {
    /*Valid Anagram
Easy
Topics
premium lock icon
Companies
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 */
    public static void main(String[] args) {
        //     String s = "rat", t = "car";
        String s = "anagram", t = "nagaram";
        boolean result = true;
        if (s.length() != t.length()) {
            System.out.println("false");
            result = false;
        }

        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();

        Arrays.sort(charS);
        Arrays.sort(charT);
        for (int i = 0; i < charT.length; i++) {
            if (charS[i] != charT[i]) {
                System.out.println("false");
                result = false;
            }
        }
        System.out.println(result);
    }
}