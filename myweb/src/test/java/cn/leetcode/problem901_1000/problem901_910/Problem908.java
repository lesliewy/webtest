package cn.leetcode.problem901_1000.problem901_910;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *    最小差值 I
 *    给你一个整数数组 A，对于每个整数 A[i]，我们可以选择处于区间 [-K, K] 中的任意数 x ，将 x 与 A[i] 相加，结果存入 A[i] 。
 在此过程之后，我们得到一些数组 B。
 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。

 示例 1：
 输入：A = [1], K = 0
 输出：0
 解释：B = [1]
 示例 2：

 输入：A = [0,10], K = 2
 输出：6
 解释：B = [2,8]
 示例 3：

 输入：A = [1,3,6], K = 3
 输出：0
 解释：B = [3,3,3] 或 B = [4,4,4]

 提示：

 1 <= A.length <= 10000
 0 <= A[i] <= 10000
 0 <= K <= 10000
 * </pre>
 * 
 * Created by leslie on 2020/6/17.
 */
public class Problem908 {

    @Test
    public void testxxx() {
        Assert.assertEquals(0, smallestRangeI(new int[] { 1 }, 0));
        Assert.assertEquals(6, smallestRangeI(new int[] { 0, 10 }, 2));
        Assert.assertEquals(0, smallestRangeI(new int[] { 1, 3, 6 }, 3));
    }

    /**
     * <pre>
     *   方法一: max: b; min: f;     b+k - (f-k) = b-f + 2k
     *   b-f <= 2k: 0
     *   b-f > 2k:  b-f -2k
     * </pre>
     * 
     * @param a
     * @param k
     * @return
     */
    public int smallestRangeI(int[] a, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : a) {
            max = (i > max) ? i : max;
            min = (i < min) ? i : min;
        }
        return (max - min) <= 2 * k ? 0 : (max - min - 2 * k);
    }
}
