package cn.leetcode.problem1_100.problem31_40;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     搜索旋转排序数组
 *     假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 你可以假设数组中不存在重复的元素。
 你的算法时间复杂度必须是 O(log n) 级别。

 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4

 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 * </pre>
 * 
 * Created by leslie on 2019/12/13.
 */
public class Problem33 {

    @Test
    public void test1() {
        Assert.assertEquals(4, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
        Assert.assertEquals(-1, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));
    }

    /**
     * <pre>
     *     方法一: 二分查找.
     *     二分查找除了有序数组外，也可以用于旋转有序数据.
     *     时间复杂度都是O(logN)
     * </pre>
     * 
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 前半部分有序,注意此处用小于等于
            if (nums[start] <= nums[mid]) {
                // 需要再次判断，针对target的取值，来决定搜索哪半边.
                // target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }
}
