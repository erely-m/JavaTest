package com.erely.leetcode;

public class reverseLeftWords {

    public String reverseLeftWords(String s, int n) {
        StringBuilder builder = new StringBuilder();
        return builder.append(s.substring(n)).append(s.substring(0, n)).toString();
    }
}
