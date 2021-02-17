package cn.leetcode.problem1_100.problem41_50;

/**
 * <pre>
 *    接雨水
 *    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *    示例1:
 *    输入：height3 = [0,1,0,2,1,0,1,3,2,1,2,1]
 输出：6
 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

 示例 2：
 输入：height3 = [4,2,0,3,2,5]
 输出：9

 提示：
 n == height3.length
 0 <= n <= 3 * 104
 0 <= height3[i] <= 105
 * </pre>
 * 
 * Created by leslie on 2021/2/7.
 */
public class Problem42 {

    /**
     * <pre>
     *     暴力解法.
     * </pre>
     * 
     * @param height
     * @return
     */
    int trap(int[] height) {
        int n = height.length;
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int l_max = 0, r_max = 0;
            // 找右边最高的柱子
            for (int j = i; j < n; j++)
                r_max = Math.max(r_max, height[j]);
            // 找左边最高的柱子
            for (int j = i; j >= 0; j--)
                l_max = Math.max(l_max, height[j]);
            // 如果自己就是最高的话，
            // l_max == r_max == height3[i]
            res += Math.min(l_max, r_max) - height[i];
        }
        return res;
    }

    /**
     * <pre>
     *     备忘录优化.
     * </pre>
     * 
     * @return
     */
    int trap2(int[] height) {
        if (height == null || height.length <= 0) return 0;
        int n = height.length;
        int res = 0;
        // 数组充当备忘录
        int[] l_max = new int[n], r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for (int i = 1; i < n - 1; i++)
            res += Math.min(l_max[i], r_max[i]) - height[i];
        return res;
    }

    /**
     * <pre>
     *     双指针解法.
     * </pre>
     * @return
     */
    int trap3(int[] height) {
        if (height == null || height.length <= 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;

        int l_max = height[0];
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // res += min(l_max, r_max) - height3[i]
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }
}
