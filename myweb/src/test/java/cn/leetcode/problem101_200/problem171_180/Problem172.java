package cn.leetcode.problem101_200.problem171_180;

/**
 * <pre>
 *     阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。

 示例 1:
 输入: 3
 输出: 0
 解释: 3! = 6, 尾数中没有零。

 示例 2:
 输入: 5
 输出: 1
 解释: 5! = 120, 尾数中有 1 个零.
 说明: 你算法的时间复杂度应为 O(log n) 。
 * </pre>
 * 
 * Created by leslie on 2021/1/25.
 */
public class Problem172 {

    /**
     * <pre>
     *     n! 最多可以分解出多少个因子 2 和 5  -> n! 最多可以分解出多少个因子 5  ->
     * </pre>
     * 
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }

    /**
     * <pre>
     *     优化.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int res = 0;
        for (int d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }
}
