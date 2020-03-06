package com.erely.leetcode;

public class isPalindrome {

    public static void main(String[] args) {

        System.out.println(new isPalindrome().test(121));
    }

    public boolean test(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int y = 0;
        while (y < x) {
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y == x || x == y / 10;
    }


}
