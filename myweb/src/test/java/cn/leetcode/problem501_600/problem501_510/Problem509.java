package cn.leetcode.problem501_600.problem501_510;

/**
 * <pre>
 *     斐波那契数
 *     斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 F(0) = 0,   F(1) = 1
 F(n) = F(n - 1) + F(n - 2), 其中 n > 1.
 给定 n，计算 F(n)。

 示例 1：
 输入：2
 输出：1
 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.

 示例 2：
 输入：3
 输出：2
 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.

 示例 3：
 输入：4
 输出：3
 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.

 提示：
 0 ≤ n ≤ 30
 * </pre>
 * 
 * Created by leslie on 2020/11/19.
 */
public class Problem509 {

    /**
     * <pre>
     *     方法一: 递归.
     * </pre>
     * 
     * @param N
     * @return
     */
    public int fib(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * <pre>
     *     方法二: 带备忘录的递归.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n < 1) {
            return 0;
        }
        // 备忘录全初始化为 0
        int[] memo = new int[n + 1];
        // 进行带备忘录的递归
        return helper(memo, n);
    }

    private int helper(int[] memo, int n) {
        // base case
        if (n == 1 || n == 2) {
            return 1;
        }
        // 已经计算过
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     * <pre>
     *     方法三: 动态规划.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int fib3(int n) {
        int[] dp = new int[n + 1];
        // base case
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /**
     * <pre>
     *     方法三中动态规划的状态压缩.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int fib4(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
