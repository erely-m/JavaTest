package com.erely.leetcode;

import java.util.HashMap;

public class numTest {

    public static void main(String[] args) {
        int[] num = new int[]{2, 7, 11, 15, 9};
        new numTest().twoSum(num, 9);
    }

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        HashMap<Integer, Integer> hash = null;
        if (length < 16) {
            hash = new HashMap<Integer, Integer>();
        } else {
            hash = new HashMap<Integer, Integer>(length / 2);
        }
        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            if (hash.containsKey(nums[i])) {
                result[0] = hash.get(nums[i]);
                result[1] = i;
                return result;
            }
            hash.put(target - nums[i], i);
        }
        return result;
    }
}
