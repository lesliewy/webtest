package cn.leetcode.problem1_100.problem41_50;

/**
 * <pre>
 *     跳跃游戏 II
 *    给定一个非负整数数组，你最初位于数组的第一个位置。
 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 你的目标是使用最少的跳跃次数到达数组的最后一个位置。

 示例:
 输入: [2,3,1,1,4]
 输出: 2
 解释: 跳到最后一个位置的最小跳跃数是 2。
      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 说明:
 假设你总是可以到达数组的最后一个位置。
 * </pre>
 * 
 * Created by leslie on 2021/1/17.
 */
public class Problem45 {

    int[] memo;

    /**
     * <pre>
     *     动态规划-递归实现.
     *     超时.
     * </pre>
     * 
     * @param nums
     * @return
     */
    int jump(int[] nums) {
        int n = nums.length;
        // 备忘录都初始化为 n，相当于 INT_MAX
        // 因为从 0 调到 n - 1 最多 n - 1 步
        memo = new int[n];
        for (int i = 0; i < n; i++) {
            memo[i] = n;
        }
        return dp(nums, 0);
    }

    int dp(int[] nums, int p) {
        int n = nums.length;
        // base case
        if (p >= n - 1) {
            return 0;
        }
        // 子问题已经计算过
        if (memo[p] != n) {
            return memo[p];
        }
        int steps = nums[p];
        // 你可以选择跳 1 步，2 步...
        for (int i = 1; i <= steps; i++) {
            // 穷举每一个选择
            // 计算每一个子问题的结果
            int subProblem = dp(nums, p + i);
            // 取其中最小的作为最终结果
            memo[p] = Math.min(memo[p], subProblem + 1);
        }
        return memo[p];
    }

    /**
     * <pre>
     *   贪心算法.
     * </pre>
     * @param nums
     * @return
     */
    int jump2(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
