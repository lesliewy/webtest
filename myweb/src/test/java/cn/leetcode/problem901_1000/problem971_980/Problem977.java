package cn.leetcode.problem901_1000.problem971_980;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     有序数组的平方
 *     给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

 示例 1：
 输入：nums = [-4,-1,0,3,10]
 输出：[0,1,9,16,100]
 解释：平方后，数组变为 [16,1,0,9,100]
 排序后，数组变为 [0,1,9,16,100]

 示例 2：
 输入：nums = [-7,-3,2,3,11]
 输出：[4,9,9,49,121]

 提示：

 1 <= nums.length <= 104
 -104 <= nums[i] <= 104
 nums 已按 非递减顺序 排序

 进阶：

 请你设计时间复杂度为 O(n) 的算法解决本问题
 * </pre>
 * 
 * Created by leslie on 2021/2/12.
 */
public class Problem977 {

    @Test
    public void testxxx() {
        Assert.assertArrayEquals(new int[] { 0, 1, 9, 16, 100 }, sortedSquares(new int[] { -4, -1, 0, 3, 10 }));
    }

    /**
     * <pre>
     *     双指针. 两头往中间遍历.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1;
        int[] ans = new int[nums.length];
        int k = nums.length - 1;
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                ans[k--] = nums[i] * nums[i];
                i++;
            } else {
                ans[k--] = nums[j] * nums[j];
                j--;
            }
        }
        return ans;
    }
}
