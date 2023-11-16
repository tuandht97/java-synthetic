package org.learn;

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
