package cn.leetcode.problem301_400.problem321_330;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *    零钱兑换
 *    给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 你可以认为每种硬币的数量是无限的。

 示例 1：
 输入：coins = [1, 2, 5], amount = 11
 输出：3
 解释：11 = 5 + 5 + 1

 示例 2：
 输入：coins = [2], amount = 3
 输出：-1

 示例 3：
 输入：coins = [1], amount = 0
 输出：0

 示例 4：
 输入：coins = [1], amount = 1
 输出：1

 示例 5：
 输入：coins = [1], amount = 2
 输出：2

 提示：
 1 <= coins.length <= 12
 1 <= coins[i] <= 231 - 1
 0 <= amount <= 104
 * </pre>
 * 
 * Created by leslie on 2020/11/20.
 */
public class Problem322 {

    @Test
    public void testxxx() {
        Assert.assertEquals(-1, coinChange2(new int[] { 2 }, 3));
    }

    /**
     * <pre>
     *     递归解法.
     * </pre>
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        // 求最小值，所以初始化为正无穷
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subproblem = coinChange(coins, amount - coin);
            // 子问题无解，跳过
            if (subproblem == -1) {
                continue;
            }
            res = Math.min(res, 1 + subproblem);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * <pre>
     *     带备忘录的递归.
     * </pre>
     */
    private int[] memo = new int[10001];

    public int coinChange2(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        // 求最小值，所以初始化为正无穷
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subproblem = coinChange2(coins, amount - coin);
            // 子问题无解，跳过
            if (subproblem == -1) {
                continue;
            }
            res = Math.min(res, 1 + subproblem);
        }
        // 记入备忘录
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }

    /**
     * <pre>
     *     动态规划.
     * </pre>
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        // 数组大小为 amount + 1，初始值也为 amount + 1
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

}
