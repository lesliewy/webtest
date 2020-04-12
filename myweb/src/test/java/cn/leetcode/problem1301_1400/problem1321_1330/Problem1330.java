package cn.leetcode.problem1301_1400.problem1321_1330;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     翻转子数组得到最大的数组值
 *     给你一个整数数组 nums 。「 数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
 请你找到可行的最大 数组值 。

 输入：nums = [2,3,1,5,4]
 输出：10
 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10

 输入：nums = [2,4,9,24,2,1,10]
 输出：68

 1 <= nums.length <= 3*10^4
 -10^5 <= nums[i] <= 10^5
 * </pre>
 * 
 * Created by leslie on 2020/3/2.
 */
public class Problem1330 {

    @Test
    public void test1() {
        Assert.assertEquals(10, maxValueAfterReverse(new int[] { 2, 3, 1, 5, 4 }));
        Assert.assertEquals(68, maxValueAfterReverse(new int[] { 2, 4, 9, 24, 2, 1, 10 }));
    }

    /**
     * <pre>
     *     方法一： 穷举所有子数组.
     *     O(n^2)
     *     超出时间限制: n为 3 * 10^4
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxValueAfterReverse(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        // 未翻转前数组之和.
        int preSum = sumOfArr(nums);

        // 找出翻转前后的差值最大的子数组。 i j 标识子数组的头尾.
        int maxDiff = 0;
        int pre = 0, after = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 子数组从第0个开始
                if (i == 0 && j < len - 1) {
                    pre = Math.abs(nums[j + 1] - nums[j]);
                    after = Math.abs(nums[j + 1] - nums[i]);

                }
                // 子数组以最后一个结尾.
                if (i > 0 && j == len - 1) {
                    pre = Math.abs(nums[i - 1] - nums[i]);
                    after = Math.abs(nums[i - 1] - nums[j]);
                }
                // 子数组在中间
                if (i > 0 && j < len - 1) {
                    pre = Math.abs(nums[i - 1] - nums[i]) + Math.abs(nums[j + 1] - nums[j]);
                    after = Math.abs(nums[i - 1] - nums[j]) + Math.abs(nums[j + 1] - nums[i]);
                }
                if (i == 0 && j == len - 1) {
                    continue;
                }
                if (after < pre) {
                    continue;
                }
                maxDiff = Math.abs(after - pre) > maxDiff ? Math.abs(after - pre) : maxDiff;
            }
        }
        return preSum + maxDiff;
    }

    /**
     * <pre>
     *     方法二: 贪心算法.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maxValueAfterReverse2(int[] nums) {
        return 0;
    }

    private int sumOfArr(int[] arr) {
        int sum = 0;
        int len = arr.length;
        if (len == 1) {
            return arr[0];
        }
        for (int i = 1; i < len; i++) {
            sum += Math.abs(arr[i] - arr[i - 1]);
        }
        return sum;
    }
}
