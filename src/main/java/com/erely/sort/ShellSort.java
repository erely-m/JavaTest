package com.erely.sort;

/**
 * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 4.1 算法描述
 * <p>
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
 * <p>
 * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 按增量序列个数k，对序列进行k 趟排序；
 * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] s = {3, 2, 6, 7, 8, 1, 4, 5, 10, 9};
        int[] result = new ShellSort().shell(s);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + "  ");
        }
    }

    public int[] shell(int[] nums) {
        int temp;
        for (int gap = nums.length / 2; gap > 0; gap = gap / 2)
            for (int i = gap; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    temp = nums[i];
                    for (int j = i; j >= 0; j--) {
                        if (j > 0 && nums[j - 1] > temp) {
                            nums[j] = nums[j - 1];
                        } else {
                            nums[j] = temp;
                            break;
                        }
                    }
                }

            }

        return nums;
    }

}
