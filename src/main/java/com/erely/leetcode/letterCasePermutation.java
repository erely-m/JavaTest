package com.erely.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * <p>
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * <p>
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */

public class letterCasePermutation {

    public static void main(String[] args) {
        new letterCasePermutation().f("C").stream().forEach(s -> System.out.println(s));
    }

    public List<String> f(String S) {
        ArrayList list = new ArrayList();
        dfs(S.toCharArray(), 0, list);
        return list;
    }

    public void dfs(char[] chs, int index, List<String> list) {
        if (index > chs.length - 1) {
            list.add(new String(chs));
            return;
        }
        if ((chs[index] >= 65 && chs[index] <= 90) || (chs[index] >= 97 && chs[index] <= 122)) {//需要回溯变大写后去回溯 变小写后回溯
            dfs(chs, index + 1, list); //原样搜索
            //转换
            if ((chs[index] >= 65 && chs[index] <= 90)) {
                chs[index] += 32;
            } else {
                chs[index] -= 32;
            }
            dfs(chs, index + 1, list); //转换后搜索
        } else { //遇到数组直接跳过
            dfs(chs, index + 1, list);
        }

    }

}
