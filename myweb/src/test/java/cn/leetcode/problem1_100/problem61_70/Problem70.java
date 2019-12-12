package cn.leetcode.problem1_100.problem61_70;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 爬楼梯
 * </p>
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 注意：给定 n 是一个正整数。
 * </p>
 * <p>
 * 输入： 2 输出： 2 解释： 有两种方法可以爬到楼顶。 1. 1 阶 + 1 阶 2. 2 阶
 * </p>
 * <p>
 * 输入： 3 输出： 3 解释： 有三种方法可以爬到楼顶。 1. 1 阶 + 1 阶 + 1 阶 2. 1 阶 + 2 阶 3. 2 阶 + 1 阶
 * </p>
 * Created by leslie on 2019/11/28.
 */
public class Problem70 {

    @Test
    public void test1() {
        Assert.assertEquals(2, climbStairs5(2));
        Assert.assertEquals(3, climbStairs5(3));
        Assert.assertEquals(5, climbStairs5(4));
    }

    /**
     * <p>
     * 方法一: 树形递归. 递推公式: f(n) = f(n-1) + f(n-2) 终止条件: f(1) = 1; f(2)=2;
     * </p>
     * <p>
     * 时间复杂度: O(2^N) 树形递归.
     * </p>
     * <p>
     * 空间复杂度: O(N) 树的高度.
     * </p>
     * 超时.
     * 
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * <p>
     * 方法二: 记忆化递归. 记住每次的结果.
     * </p>
     * <p>
     * 时间复杂度: O(N) 空间复杂度: O(N)
     * </p>
     * 
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];

        return doClimb(n, memo);
    }

    /**
     * <p>
     * 方法三: 动态规划. 最优子结构. 和递归有点像. f(n) = f(n-1) + f(n-2). 循环实现, 从底部开始.
     * </p>
     * <p>
     * 时间复杂度: O(N) 空间复杂度: O(N)
     * </p>
     * 
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 方法四: 斐波那契数. 显然动态规划中不需要整个dp[], 只需要最近的两个数. 从而将空间复杂度降至O(1).
     * <p>
     * 时间复杂度: O(N) 空间复杂度: O(1)
     * </p>
     * 
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int pre1 = 2;
        int pre2 = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

    /**
     * 方法五: 斐波那契计算公式.
     * <p>
     * 时间复杂度: O(logN) 空间复杂度: O(1)
     * </p>
     * 
     * @return
     */
    public int climbStairs5(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

    private int doClimb(int i, int[] memo) {
        if (i == 1 || i == 2) {
            return i;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = doClimb(i - 1, memo) + doClimb(i - 2, memo);
        return memo[i];
    }
}
