package cn.leetcode.problem1501_1600.problem1581_1590;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     所有奇数长度子数组的和
 *
 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 子数组 定义为原数组中的一个连续子序列。
 请你返回 arr 中 所有奇数长度子数组的和 。

 示例 1：
 输入：arr = [1,4,2,5,3]
 输出：58
 解释：所有奇数长度子数组和它们的和为：
 [1] = 1
 [4] = 4
 [2] = 2
 [5] = 5
 [3] = 3
 [1,4,2] = 7
 [4,2,5] = 11
 [2,5,3] = 10
 [1,4,2,5,3] = 15
 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58

 示例 2：
 输入：arr = [1,2]
 输出：3
 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。

 示例 3：
 输入：arr = [10,11,12]
 输出：66

 提示：
 1 <= arr.length <= 100
 1 <= arr[i] <= 1000
 * </pre>
 * 
 * Created by leslie on 2021/2/12.
 */
public class Problem1588 {

    @Test
    public void testxxx() {
        Assert.assertEquals(58, sumOddLengthSubarrays(new int[] { 1, 4, 2, 5, 3 }));
        Assert.assertEquals(3, sumOddLengthSubarrays(new int[] { 1, 2 }));
        Assert.assertEquals(66, sumOddLengthSubarrays(new int[] { 10, 11, 12 }));

    }

    /**
     * <pre>
     *     常规思路.  连续子数组和即可以使用数组的前缀和.
     *     时间复杂度O(N^2) 空间复杂度 O(N)
     * </pre>
     * 
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        // 前缀和第0个不使用，这样方便后面计算.
        int[] preSum = new int[len + 1];
        // preSum[1] = arr[0];
        // for (int i = 1; i <= len; i++) {
        // preSum[i] = preSum[i - 1] + arr[i-1];
        // }

        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }

        int ans = 0;
        for (int i = 1; i <= len; i += 2) {
            for (int k = i; k <= len; k++) {
                ans += preSum[k] - preSum[k - i];
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     O(N)解法.
     *     遍历数组, 计算每个元素在奇数长度子数组中出现的次数.
     *     比如题目给出的第一个测试用例 [1, 4, 2, 5, 3] 中；
     1 在 3 个长度为奇数的数组中出现过：[1], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终的和，要加上 1 * 3；
     4 在 4 个长度为奇数的数组中出现过：[4], [4, 2, 5], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终和，要加上 4 * 4；
     2 在 5 个长度为奇数的数组中出现过：[2], [2, 5, 3], [4, 2, 5], [1, 4, 2], [1, 4, 2, 5, 3]；所以最终和，要加上 5 * 2；
     ...
    
     对于一个数字，它所在的数组，可以在它前面再选择 0, 1, 2, ... 个数字，一共有 left = i + 1 个选择；
     可以在它后面再选择 0, 1, 2, ... 个数字，一共有 right = n - i 个选择。
     如果在前面选择了偶数个数字，那么在后面，也必须选择偶数个数字，这样加上它自身，才构成奇数长度的数组；
     如果在前面选择了奇数个数字，那么在后面，也必须选择奇数个数字，这样加上它自身，才构成奇数长度的数组；
     数字前面共有 left 个选择，其中偶数个数字的选择方案有 left_even = (left + 1) / 2 个，奇数个数字的选择方案有 left_odd = left / 2 个；
     数字后面共有 right 个选择，其中偶数个数字的选择方案有 right_even = (right + 1) / 2 个，奇数个数字的选择方案有 right_odd = right / 2 个；
     所以，每个数字一共在 left_even * right_even + left_odd * right_odd 个奇数长度的数组中出现过。
     * </pre>
     * 
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays2(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i + 1, right = arr.length - i, left_even = (left + 1) / 2, right_even = (right + 1) / 2,
                    left_odd = left / 2, right_odd = right / 2;
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }

}
