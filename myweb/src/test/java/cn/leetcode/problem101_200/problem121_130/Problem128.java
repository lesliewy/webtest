package cn.leetcode.problem101_200.problem121_130;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     最长连续序列
 *     给定一个未排序的整数数组，找出最长连续序列的长度。
 要求算法的时间复杂度为 O(n)。

 输入: [100, 4, 200, 1, 3, 2]
 输出: 4
 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4
 * </pre>
 * 
 * Created by leslie on 2019/12/14.
 */
public class Problem128 {

    @Test
    public void test1() {
        Assert.assertEquals(4, longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
    }

    /**
     * <pre>
     *     方法一: 穷举.
     *     遍历每一个数，判断后续的数是否存在.
     *     时间复杂度: O(N^3)
     *     空间复杂度: O(1)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }

    private boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     *     方法二: 排序数组.
     *     时间复杂度: 取决于排序算法，通常O(NlogN)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        // 一次遍历，完成最长连续序列计算.
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
    }

    /**
     * <pre>
     *     方法三: hash + 线性空间构造.   穷举算法的优化版本.
     *     时间复杂度: O(N)  看起来是两层循环O(n^2), 但是内层while只有当元素是初始元素时才会执行.  具体待定？
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int longestConsecutive3(int[] nums) {
        // 优化一: 使用hash来存储，将判断元素是否存在降到O(1).
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            // 优化二: 只针对序列初始元素进行判断，显著减少不必要的计算. 初始元素也就是 cur - 1 不在当前数组的那些元素.
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
