package com.erely.leetcode;

public class singleNumber {

    public static void main(String[] args) {

    }

    public static int f1(int[] nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            a ^= nums[i];
        }
        return a;
    }
}
