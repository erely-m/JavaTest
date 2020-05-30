package com.erely.leetcode;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class strStr {

    public static void main(String[] args) {
        strStr s = new strStr();
        int result = s.strStr("mississippi", "sippia");
        System.out.println(result);
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0 || "".equals(needle)) {
            return 0;
        }
        if (haystack.length() == 0 || "".equals(haystack) || haystack.length() < needle.length()) {
            return -1;
        }

        int[] next = getNext(needle);
        int index = 0;
        boolean match = false;
        while (true) {
            for (int i = 0; i < next.length; i++) {
                if (index == haystack.length()) break;
                if (haystack.charAt(index) == needle.charAt(i)) {
                    index++;
                    if (i == next.length - 1) match = true;
                } else {
                    if (next[i] != 0) {
                        index = index - i + next[i];
                    } else {
                        index = index - i + 1;
                    }
                    break;
                }
            }
            if (match || index == haystack.length() - 1) break;
        }
        if (match) {
            return index - needle.length();
        }
        return -1;
    }

    public int[] getNext(String p) {
        int[] next = new int[p.length()];
        next[0] = 0;
        int i = 0;
        for (int j = 1; j < p.length(); j++) {
            while (p.charAt(i) != p.charAt(j) && i > 0) {
                i = 0;
            }
            if (p.charAt(i) == p.charAt(j)) {
                i++;
                next[j] = i;
            } else {
                next[j] = 0;
            }
        }
        return next;
    }

}
