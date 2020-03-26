package com.erely.sort;

/**
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 6.1 算法描述
 * <p>
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * <p>
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] s = {3, 2, 6, 7, 8, 1, 4, 5, 10, 9};
        new QuickSort().quick(s, 0, 9);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "  ");
        }
    }

    public void quick(int[] nums, int start, int end) {
        int tag = nums[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (j > i && nums[j] > tag) { //右边大的全在右边
                j--;
            }
            while (j > i && nums[i] < tag) { //小的全在左边
                i++;
            }
            if (i < j && nums[i] == nums[j]) {
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if (i - 1 > start) quick(nums, start, i - 1);
        if (j + 1 < end) quick(nums, j + 1, end);
    }

}
