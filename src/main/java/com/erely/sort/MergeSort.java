package com.erely.sort;

/**
 *
 *
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 * 5.1 算法描述
 *
 *     把长度为n的输入序列分成两个长度为n/2的子序列；
 *     对这两个子序列分别采用归并排序；
 *     将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] s = {3, 2, 6, 7, 8, 1, 4, 5, 10, 9};
        new MergeSort().merge(s, 0, 9);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "  ");
        }

    }

    public void merge(int[] nums, int first, int last) {
        if(first == last) return;
        int middle = (last + first) / 2;
        merge(nums, first, middle);
        merge(nums, middle + 1, last);
        mergeArray(nums, first, middle, last);//合并
    }

    public void mergeArray(int[] s, int first, int middle, int last) {
        int[] temp = new int[last - first + 1];
        int i = 0;
        int left = first;
        int right = middle + 1;
        while (left <= middle && right <= last) {
            temp[i++] = s[left] < s[right] ? s[left++] : s[right++];
        }

        if (left <= middle) {
            for (; left <= middle; left++) {
                temp[i++] = s[left];
            }
        }
        if (right <= last) {
            for (; right <= last; right++) {
                temp[i++] = s[right];
            }
        }
        for (int i1 = 0; i1 < temp.length; i1++) {
            s[first + i1] = temp[i1];

        }
    }

}
