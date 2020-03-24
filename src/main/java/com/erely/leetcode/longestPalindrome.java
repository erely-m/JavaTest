package com.erely.leetcode;

//最长回文字符串
public class longestPalindrome {

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(ttt(s));
    }

    public static String ttt(String s) {
        String result = "";
        String temp = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if(chars[i] == chars[i + 1] && i - 1 >= 0 && i + 1 < chars.length && chars[i - 1] == chars[i + 1]){ //aaa型
                temp = "" + chars[i - 1] + chars[i] + temp + chars[i + 1];
                for (int j = 2; j < chars.length && i - j >= 0 && i + j < chars.length - 1; j++) {
                    if (chars[i - j] == chars[i + j + 1]) {
                        temp = "" + chars[i - j] + temp + chars[i + j + 1];
                    } else {
                        break;
                    }
                }
                if (temp.length() > result.length()) {
                    result = temp;
                }
                temp = "";
            }else if (chars[i] == chars[i + 1]) { //abba型
                temp = "" + chars[i] + temp + chars[i + 1];
                for (int j = 1; j < chars.length && i - j >= 0 && i + j < chars.length - 1; j++) {
                    if (chars[i - j] == chars[i + j + 1]) {
                        temp = "" + chars[i - j] + temp + chars[i + j + 1];
                    } else {
                        break;
                    }
                }
                if (temp.length() > result.length()) {
                    result = temp;
                }
                temp = "";
            } else if (i - 1 >= 0 && i + 1 < chars.length && chars[i - 1] == chars[i + 1]) { //aba型
                temp = "" + chars[i - 1] + chars[i] + temp + chars[i + 1];
                for (int j = 2; j < chars.length && i - j >= 0 && i + j < chars.length - 1; j++) {
                    if (chars[i - j] == chars[i + j + 1]) {
                        temp = "" + chars[i - j] + temp + chars[i + j + 1];
                    } else {
                        break;
                    }
                }
                if (temp.length() > result.length()) {
                    result = temp;
                }
                temp = "";
            } else { //不满足
                continue;
            }
        }
        if (result == "" && !"".equals(s)) {
            return chars[0] + "";
        }
        return result;
    }

}
