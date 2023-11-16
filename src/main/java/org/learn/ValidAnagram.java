package org.learn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 */

public class ValidAnagram {

    // Best Solution Memory
    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char c1 = t.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
            hm.put(c1, hm.getOrDefault(c1, 0) - 1);
        }
        for (char c : hm.keySet()) {
            if (hm.get(c) != 0)
                return false;
        }
        return true;
    }

    private static boolean bestSolutionRunTime(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        if (isAnagram(s, t)) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}
