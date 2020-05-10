package com.erely.leetcode;


public class uniqueOccurrences {

    public static void main(String[] args) {

        System.out.println(new uniqueOccurrences().uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
    }

    public boolean uniqueOccurrences(int[] arr) {
        int[] nums = new int[2001];
        for (int x : arr) {
            if (x < 0) {
                nums[1000 + x]++;
            } else {
                nums[x]++;
            }
        }
        byte[] b = new byte[2001];
        int temp;
        for (int i : nums) {
            if (i == 0) continue;
            else {
                temp = i % 2001;
                if (b[temp] == '1') return false;
                else b[temp] = '1';
            }
        }
        return true;
    }
}
