package cn.leetcode.problem101_200.problem151_160;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     乘积最大子序列
 *     给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *     输入: [2,3,-2,4]
 *     输出: 6
 *     解释: 子数组 [2,3] 有最大乘积 6。
 *
 *     输入: [-2,0,-1]
 *     输出: 0
 *     解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * </pre>
 * 
 * Created by leslie on 2019/11/29.
 */
public class Problem152 {

    @Test
    public void test1() {
        Assert.assertEquals(6, maxProduct2(new int[] { 2, 3 }));
        Assert.assertEquals(0, maxProduct2(new int[] { -2, 0, -1 }));
        Assert.assertEquals(6, maxProduct2(new int[] { 2, 3, -2, 4 }));
    }

    /**
     * <pre>
     *    方法一: 动态规划.
     *    我们先定义一个数组 dpMax，用 dpMax[i] 表示以第 i 个元素的结尾的子数组，乘积最大的值，也就是这个数组必须包含第 i 个元素。 那么 dpMax[i] 的话有几种取值。
     *    当 nums[i] >= 0 并且dpMax[i-1] > 0，dpMax[i] = dpMax[i-1] * nums[i]
     *    当 nums[i] >= 0 并且dpMax[i-1] < 0，此时如果和前边的数累乘的话，会变成负数，所以dpMax[i] = nums[i]
     *    当 nums[i] < 0，此时如果前边累乘结果是一个很大的负数，和当前负数累乘的话就会变成一个更大的数。所以我们还需要一个数组 dpMin 来记录以第 i 个元素的结尾的子数组，乘积最小的值。
     *         当dpMin[i-1] < 0，dpMax[i] = dpMin[i-1] * nums[i]
     *         当dpMin[i-1] >= 0，dpMax[i] = nums[i]
     *    当然，上边引入了 dpMin 数组，怎么求 dpMin 其实和上边求 dpMax 的过程其实是一样的。
     *    按上边的分析，我们就需要加很多的 if else来判断不同的情况，这里可以用个技巧。
     *    我们注意到上边dpMax[i] 的取值无非就是三种，dpMax[i-1] * nums[i]、dpMin[i-1] * nums[i] 以及 nums[i]。
     *    所以我们更新的时候，无需去区分当前是哪种情况，只需要从三个取值中选一个最大的即可。
     * </pre>
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    /**
     * <pre>
     *    方法二: 方法一的优化.
     *    动态规划的老问题，我们注意到更新 dp[i] 的时候，我们只用到 dp[i-1] 的信息，再之前的信息就用不到了。所以我们完全不需要一个数组，只需要一个变量去重复覆盖更新即可
     * </pre>
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int preMax = nums[0], preMin = nums[0], max = nums[0];
        for (int i = 1; i < n; i++) {
            int currMax = Math.max(preMin * nums[i], Math.max(preMax * nums[i], nums[i]));
            int currMin = Math.min(preMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            preMax = currMax;
            preMin = currMin;
            max = Math.max(max, currMax);
        }
        return max;
    }
}
