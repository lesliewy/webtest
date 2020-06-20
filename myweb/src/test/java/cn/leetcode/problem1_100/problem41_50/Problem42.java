package cn.leetcode.problem1_100.problem41_50;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *   连续子数组的最大和
 *   输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 要求时间复杂度为O(n)。

 示例1:
 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

 提示：
 1 <= arr.length <= 10^5
 -100 <= arr[i] <= 100
 * </pre>
 * 
 * Created by leslie on 2020/6/18.
 */
public class Problem42 {

    @Test
    public void testxxx() {
        Assert.assertEquals(6, maxSubArray(new int[] { -1, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

    /**
     * 方法一: 动态规划.
     *     dp[i]: 0 ~ i 个数的最大和.
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

    }

    public int maxSubArray2(int[] nums) {
        int ans = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
