package cn.leetcode.problem1_100.problem1_10;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 两数相加.
 * Created by leslie on 2019/11/14.
 */
public class Problem1 {

    @Test
    public void test1() {
        Assert.assertArrayEquals("failed", new int[] { 0, 1 }, twoSum3(new int[] { 2, 7, 3, 5 }, 9));
        Assert.assertArrayEquals("failed", new int[] { 1, 2 }, twoSum3(new int[] { 1, 0, 15, 2, 7, 3, 5 }, 15));
        Assert.assertArrayEquals("failed", new int[] { 2, 3 }, twoSum3(new int[] { 2, 7, 3, 5 }, 8));
        Assert.assertArrayEquals("failed", new int[] { 0, 1 }, twoSum3(new int[] { 3, 3 }, 6));
        Assert.assertArrayEquals("failed", new int[] { 1, 2 }, twoSum3(new int[] { 2,3, 3 }, 6));
    }

    /**
     * O(n^2) 暴力破解.
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Assert.assertNotNull(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * O(n): 哈希表将O(n)降到O(1).
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        Map<Integer, Integer> intHash = new HashMap<>();
        int index = 0;
        for (int i : nums) {
            intHash.put(i, index++);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer j = intHash.get(target - nums[i]);
            if (j != null && i != j) {
                return new int[] { i, j };
            }
        }
        return null;
    }

    /**
     * 同样是O(n)，可以只在一次遍历中解决.
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        Map<Integer, Integer> intHash = new HashMap<>();
        int index = 0;
        for (int i : nums) {
            Integer j = intHash.get(target - i);
            if (j != null && index != j) {
                return new int[] { j, index };
            }
            intHash.put(i, index);
            index++;
        }
        return null;
    }
}
