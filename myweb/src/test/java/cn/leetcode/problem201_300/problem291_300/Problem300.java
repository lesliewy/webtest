package cn.leetcode.problem201_300.problem291_300;

import java.util.Arrays;

/**
 * <pre>
 *     最长上升子序列(最长递增子序列)
 *
 *     给定一个无序的整数数组，找到其中最长上升子序列的长度。

 示例:
 输入: [10,9,2,5,3,7,101,18]
 输出: 4
 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

 说明:
 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 你算法的时间复杂度应该为 O(n2) 。
 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * </pre>
 * 
 * Created by leslie on 2020/9/7.
 */
public class Problem300 {

    /**
     * <pre>
     *     动态规划: 迭代
     *     dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        // dp 数组全部初始化为1. 因为至少包含自己.
        Arrays.fill(dp, 1);
        // 遍历所有状态.
        for (int i = 0; i < nums.length; i++) {
            // 遍历所有选择
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // dp[] 中最大值.
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
