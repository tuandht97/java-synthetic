package org.learn;

/**
 A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
 Alphanumeric characters include letters and numbers.
 Given a string s, return true if it is a palindrome, or false otherwise.

 Example 1:

 Input: s = "A man, a plan, a canal: Panama"
 Output: true
 Explanation: "amanaplanacanalpanama" is a palindrome.

 Example 2:

 Input: s = "race a car"
 Output: false
 Explanation: "raceacar" is not a palindrome.
 */

public class ValidPalindrome {
    private static boolean isPalindrome(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
        }

        String str = result.toString().toLowerCase();
        int len = str.length();
        for (int i = 0; i < len / 2; ++i) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(len -i - 1);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String s2 = "race a car";

        if (isPalindrome(s)) {
            System.out.println("s1 TRUE");
        } else {
            System.out.println("s1 FALSE");
        }

        if (isPalindrome(s2)) {
            System.out.println("s2 TRUE");
        } else {
            System.out.println("s2 FALSE");
        }
    }
}
