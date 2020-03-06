package com.erely.leetcode;

public class lengthOfLongestSubstring {

    public static void main(String[] args) {

        new lengthOfLongestSubstring().ttt("abcabcbb");
    }

    public int ttt(String s) {
        int putIndex = 0;
        int getIndex = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = getIndex; j < putIndex; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    getIndex = j + 1;
                }
            }
            putIndex++;
            max = putIndex - getIndex > max ? putIndex - getIndex : max;
        }
        System.out.println(max);
        return max;
    }

    public int findE(String ss, char ch, int getIndex, int putIndex) {
        int result = -1;
        for (; getIndex < putIndex; getIndex++) {
            if (ss.charAt(getIndex) == ch) {
                result = getIndex;
            }
        }
        return result;
    }


}
