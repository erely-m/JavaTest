package com.erely.leetcode;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class coinChange {

    public static void main(String[] args) {
        int[] sum = new int[]{ 2};
        coinChange c = new coinChange();
//        long start = System.currentTimeMillis();
//        System.out.println(c.f(sum, 100));
//        System.out.println("执行用时" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        System.out.println(c.ff(sum, 100));
//        System.out.println("执行用时" + (System.currentTimeMillis() - start));
        System.out.println(c.dp(sum, 3));
    }

    public int f(int[] coins, int amount) {
        if (amount == 0) return 0;
        int result = dfs(coins, amount, 0, Integer.MAX_VALUE);
        if (result != Integer.MAX_VALUE)
            return result;
        else
            return -1;
    }

    public int ff(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinsChange(coins, amount, new int[amount]);
    }

    public int dfs(int[] coins, int amount, int count, int min) {

        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount - coins[i] == 0) {
                if (min > count) min = ++count;
            } else if (amount - coins[i] < 0 || count > min) {
                continue;
            }
            min = dfs(coins, amount - coins[i], ++count, min);
            count--;
        }
        return min;
    }

    public int coinsChange(int[] coins, int amount, int[] sum) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (sum[amount - 1] != 0) return sum[amount - 1];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = coinsChange(coins, amount - coins[i], sum);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        sum[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return sum[amount - 1];
    }

    public int dp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
