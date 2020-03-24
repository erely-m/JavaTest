package com.erely.leetcode;

import java.util.BitSet;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * <p>
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class majorityElement {
    public static void main(String[] args) {
        int[] s = new int[]{6,5,5};
        System.out.println(f(s));
    }

    public static int f(int[] nums) {
        int count = 0;
        int curr = 0;
        for (int num : nums) {
            if (count == 0) {
                curr = num;
            }
            count += curr == num ? 1 : -1;
        }
        return curr;
    }
}
