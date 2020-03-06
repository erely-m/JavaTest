package com.erely.leetcode;

public class findMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 6};
        int[] nums2 = {3, 4, 8, 9};
        System.out.println(ttt(nums1, nums2));
    }

    public static double ttt(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;
        return (cut(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left) + cut(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right)) * 0.5;
    }

    public static int cut(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {

        int start1Len = end1 - start1 + 1;
        int start2Len = end2 - start2 + 1;

        if (start1Len > start2Len) return cut(nums2, start2, end2, nums1, start1, end1, k);
        if (start1Len == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(start1Len, k / 2) - 1;
        int j = start2 + Math.min(start2Len, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return cut(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return cut(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }

    }
}
