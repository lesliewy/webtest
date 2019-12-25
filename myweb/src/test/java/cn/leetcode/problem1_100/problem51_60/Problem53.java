package cn.leetcode.problem1_100.problem51_60;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     最大子序和
 *     给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

 进阶:
 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
 * </pre>
 * 
 * Created by leslie on 2019/12/22.
 */
public class Problem53 {

    @Test
    public void test1() {
        Assert.assertEquals(6, maxSubArray2(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        Assert.assertEquals(1, maxSubArray2(new int[] { -2, 1 }));
        Assert.assertEquals(0, maxSubArray2(new int[] { -1, 0, -2 }));
    }

    /**
     * <pre>
     *     方法一: 穷举.
     *     时间复杂度: O(N^2)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int ans = nums[0], sum = nums[0];
        for (int i = 0; i < len; i++) {
            sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                ans = Math.max(ans, sum);
            }
        }
        return Math.max(ans, sum);
    }

    /**
     * <pre>
     *     方法二: 贪心.
     *     时间复杂度: O(N)
     *     空间复杂度: O(1)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            // 当前位置的最大和.
            currSum = Math.max(nums[i], currSum + nums[i]);
            // 维护全局最大和.
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    /**
     * <pre>
     *     方法二: 动态规划.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
