package cn.leetcode.problem1201_1300.problem1261_1270;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 * 输入：nums = [3,6,5,1,8]
 输出：18
 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）

 输入：nums = [4]
 输出：0
 解释：4 不能被 3 整除，所以无法选出数字，返回 0

 输入：nums = [1,2,3,4,4]
 输出：12
 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）

 1 <= nums.length <= 4 * 10^4
 1 <= nums[i] <= 10^4
 * </pre>
 * 
 * Created by leslie on 2020/1/2.
 */
public class Problem1262 {

    @Test
    public void testxxx() {
        Assert.assertEquals(18, maxSumDivThree(new int[] { 3, 6, 5, 1, 8 }));
        Assert.assertEquals(0, maxSumDivThree(new int[] { 4 }));
        Assert.assertEquals(12, maxSumDivThree(new int[] { 1, 2, 3, 4, 4 }));
    }

    /**
     * <pre>
     *     方法一: dp[0,1,2] 分别存模式0，1，2的最大数字和.
     *     时间复杂度: O(N)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[] { 0, 0, 0 };

        for (int i = 0; i < nums.length; ++i) {
            int mod = nums[i] % 3;

            // 关键点.
            // a 用来处理模0: 当前数字模为0, a=dp[0]; 当前数字模为1，a = dp[2]; 当前数字模为2， a=dp[1]
            int a = dp[(3 + 0 - mod) % 3];
            // b 用来处理模1: 当前数字模为0, b=dp[1]; 当前数字模为1, b=dp[0]; 当前数字模为 2, b=dp[2]
            int b = dp[(3 + 1 - mod) % 3];
            int c = dp[(3 + 2 - mod) % 3];

            if (a != 0 || mod == 0) {
                dp[0] = Math.max(dp[0], a + nums[i]);
            }
            if (b != 0 || mod == 1) {
                dp[1] = Math.max(dp[1], b + nums[i]);
            }
            ;
            if (c != 0 || mod == 2) {
                dp[2] = Math.max(dp[2], c + nums[i]);
            }
        }
        return dp[0];
    }
}
