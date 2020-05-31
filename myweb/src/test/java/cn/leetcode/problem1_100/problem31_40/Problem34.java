package cn.leetcode.problem1_100.problem31_40;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     在排序数组中查找元素的第一个和最后一个位置
 *    给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
      你的算法时间复杂度必须是 O(log n) 级别。
 如果数组中不存在目标值，返回 [-1, -1]。

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]
 * </pre>
 * 
 * Created by leslie on 2020/5/26.
 */
public class Problem34 {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[] { 3, 4 }, searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8));
        Assert.assertArrayEquals(new int[] { -1, -1 }, searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 6));
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    /**
     * <pre>
     *     二分查找:
     * </pre>
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = { -1, -1 };

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

        return targetRange;
    }
}
