package com.erely.leetcode;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String s = "ABBAABBBABAAABCDABDAAABBSCD";
        String p = "ABCDABD";
        int[] next = getNext(p); //获取next数组
//        Arrays.stream(next).forEach(a-> System.out.println(a));
        int j = 0;
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
            while (true) {
                if (j == next.length - 1) {

                }
            }
        }


    }

    //计算next数组
    private static int[] getNext(String p) {
        int[] next = new int[p.length()];
        next[0] = 0;
        int i = 0;
        for (int j = 1; j < p.length(); j++) {
            while (p.charAt(i) != p.charAt(j) && i > 0) {
                i = 0; //如果不同直接从头开始比较
            }
            if (p.charAt(i) == p.charAt(j)) { //如果存在相同则说明存在相同前后缀需要加1
                i++;
                next[j] = i;
            } else { //如果不相同则说明直接就是0
                next[j] = 0;
            }
        }
        return next;
    }
}
