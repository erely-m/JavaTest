package com.erely.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class climbStairs {

    public static void main(String[] args) {
        System.out.println(f1(45));
    }

//    public static int f(int n) {
//        return f1(n, 1) + f1(n, 2);
//    }

    public static int f1(int n) {
//        if (n == 1) {
//            return 1;
//        } else if (n == 2) {
//            return 2;
//        } else {
//            return f1(n - 1) + f1(n - 2);
//        }
        if (n <= 2) {
            return n;
        } else {
            int i1 = 1;
            int i2 = 2;
            for (int i = 3; i <= n; i++) {
                int temp = i1 + i2;
                i1 = i2;
                i2 = temp;
            }
            return i2;
        }
    }
}
