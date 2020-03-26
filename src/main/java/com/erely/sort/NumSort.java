package com.erely.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class NumSort {

    public static long startTime;

    public static void startTime() {
        startTime = 0L;
        startTime = System.nanoTime();
    }

    public static void endTime(String s) {
        System.out.println(s + "花费时间=" + (System.nanoTime() - startTime));
    }

    /**
     * 两个数比较大小，较大的数下沉，较小的数冒起来。
     * 间复杂度O(n^n) 空间复杂度O(1)
     *
     * @param s
     */
    public static void bubbleSort(int[] s) {
        startTime();
        int temp;
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i; j < s.length - 1; j++) {
                if (s[i] > s[j + 1]) {
                    temp = s[i];
                    s[i] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }
        endTime("冒泡");
        print(s);
    }


    /**
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     * 时间复杂度O(n^n) 空间复杂 O(2)
     *
     * @param s
     */
    public static void selectionSort(int[] s) {
        startTime();
        int index;
        int temp;
        for (int i = 0; i < s.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < s.length - 1; j++) {
                if (s[index] > s[j]) {
                    index = j;
                }
            }
            if (index != i) {
                temp = s[index];
                s[index] = s[i];
                s[i] = temp;
            }
        }
        endTime("选择");
        print(s);
    }

    /**
     * 在要排序的一组数中，**假定前n-1个数已经排好序，**
     * 现在将第n个数插到前面的有序数列中，
     * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     * 时间O(n^n) 空间O(1)
     *
     * @param s
     */
    public static void insertSort(int[] s) {
        startTime();
        int temp;
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (s[i] > s[j]) {
                    temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                } else {
                    break; //前面已经是有序了比倒数的就行
                }
            }
        }
        endTime("插入");
        print(s);
    }

    /**
     * @param s
     */
    public static void shellSort(int[] s) {
        startTime();
        int len = s.length;
        for (int tag = len / 2; len > 0; len = len / 2) {
            for (int i = tag; i < len; i++) {
                int j = i;
                int curr = s[i];
//                for()
            }

        }
        endTime("希尔");
        print(s);
    }

    /**
     * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
     * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * @param s
     */
    public static void quickSort(int[] s) {
        startTime();
        quickSort(0, s.length - 1, s);
        endTime("快排");
        print(s);
    }

    public static void quickSort(int low, int high, int[] s) {
        if (low < high) {
            int index = getIndex(low, high, s); //获取基准对象 需要怎么去处理左右两边
            quickSort(0, index - 1, s);
            quickSort(index + 1, high, s);
        }
    }

    public static int getIndex(int low, int high, int[] s) {
        int temp = s[low];//首先以该数作为基准
        while (low < high) {
            while (low < high && s[high] >= temp) { //先从高向低扫描
                high--;//如果高位比地位大则减一
            }
            if (low < high) {
                s[low] = s[high];
                high--;
            }
            while (low < high && s[low] < temp) { //由低向高位扫描
                low++;
            }
            if (low < high) {
                s[high] = s[low];
                low++;
            }
            s[low] = temp;
        }
        return low;
    }

    /**
     * 先分解后合并 分而治之
     *
     * @param s
     */
    public static void mergeSort(int[] s) {
        startTime();
        mergeResolve(s, 0, s.length - 1, new int[10]);
        endTime("归并");
        print(s);
    }

    public static void mergeResolve(int[] s, int first, int last, int[] temp) {
        if (first < last) {
            int middle = (first + last) / 2;
            //分解
            mergeResolve(s, first, middle, temp);
            mergeResolve(s, middle + 1, last, temp);
            //合并
            mergeArray(s, first, middle, last, temp);
        }
    }

    public static void mergeArray(int[] s, int first, int middle, int last, int[] temp) {

        int low = first;
        int high = last;
        int mid = middle + 1;
        int index = 0;//目标数组下标
        while (low <= middle && mid <= high) { //处理完成剩下最后为处理的
            if (s[low] <= s[mid]) {
                temp[index] = s[low];
                low++;
                index++;
            } else {
                temp[index] = s[mid];
                mid++;
                index++;
            }
        }
        //分别处理没有放完的
        while (low <= middle) {
            temp[index++] = s[low++];
        }
        while (mid <= high) {
            temp[index++] = s[mid++];
        }
        //排完序放回去
        for (int i = 0; i < index; i++) {
            s[first + i] = temp[i];
        }
    }

    /**
     * 完全二叉树
     *
     * @param s
     */
    public static void heapSort(int[] s) {
        startTime();
        //构造大顶堆
        heapInsert(s);
        int size = s.length;
        while (size > 1) {
            swap(s, 0, size - 1);
            size--;
            heapify(s, 0, size); //重新构造
        }
        endTime("堆");
        print(s);
    }

    public static void heapInsert(int[] s) {
        for (int i = 0; i < s.length; i++) {
            int index = i;  //当前节点
            int parentIndex = (index - 1) / 2;//获取父节点
            while (s[index] > s[parentIndex]) { //如果子节点的子大于父节点则需要交换
                swap(s, index, parentIndex);
                index = parentIndex;//当前节点指向父节点
                parentIndex = (index - 1) / 2;//重新计算父节点
            }
        }
    }

    //重新构造大顶堆
    public static void heapify(int[] s, int start, int size) {
        int left = 2 * start + 1; //左孩子下标
        int right = 2 * start + 2; //右孩子下标
        if (left < size) { //如果左孩子不在范围内 这说明右孩子肯定不在范围内
            int maxIndex;//两个孩子中较大的索引值
            if (s[left] < s[right] && right < size) {
                maxIndex = right;
            } else {
                maxIndex = left;
            }
            if (s[start] > s[maxIndex]) { //如果父节点大于孩子节点 满足直接退出

            } else {//如果父节点不满足则进行交换
                swap(s, start, maxIndex);
                start = maxIndex;//重新计算该值索引再次比较孩子
                heapify(s, start, size);
            }
        }
    }

    //堆顶和堆尾交换并构造
    public static void swap(int[] s, int start, int end) {
        int temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }

    public static void countSort(int[] s) {

    }

    public static void print(int[] s) {
        for (int i : s) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] s = {3, 2, 6, 7, 8, 1, 4, 5, 10, 9};
        bubbleSort(s);
        selectionSort(s);
        insertSort(s);
        quickSort(s);
        mergeSort(s);
        heapSort(s);
        minHeapSort(s); //小顶堆
        shellSort(s);
    }

    private static void minHeapSort(int[] s) {
        startTime();
        minHeapInsert(s);
        int size = s.length;
        while (size > 1) {
            swap(s, 0, size - 1);
            System.out.print(s[size - 1] + " ");
            size--;
            minHeapify(s, 0, size); //重新构造
        }
        System.out.println(s[0]);
        endTime("小顶堆");
//        print(s);
    }

    private static void minHeapify(int[] s, int start, int size) {
        int left = 2 * start + 1; //左孩子下标
        int right = 2 * start + 2; //右孩子下标
        if (left < size) { //如果左孩子不在范围内 这说明右孩子肯定不在范围内
            int minIndex;//两个孩子中较大的索引值
            if (s[left] > s[right] && right < size) {
                minIndex = right;
            } else {
                minIndex = left;
            }
            if (s[start] < s[minIndex]) { //如果父节点大于孩子节点 满足直接退出

            } else {//如果父节点不满足则进行交换
                swap(s, start, minIndex);
                start = minIndex;//重新计算该值索引再次比较孩子
                heapify(s, start, size);
            }
        }
    }

    private static void minHeapInsert(int[] s) {
        for (int i = 0; i < s.length; i++) {
            int index = i;
            int parent = (index - 1) / 2;//获取父节点
            while (s[i] < s[parent]) { //当前节点小于父节点进行交换
                swap(s, index, parent);
                index = parent;//当前节点指向父节点
                parent = (index - 1) / 2;//重新计算父节点
            }
        }
    }
}
