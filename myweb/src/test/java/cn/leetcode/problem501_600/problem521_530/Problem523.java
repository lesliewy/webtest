package cn.leetcode.problem501_600.problem521_530;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
       示例 1:
       输入: [23,2,4,6,7], k = 6
       输出: True
       解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。

 示例 2:
 输入: [23,2,6,4,7], k = 6
 输出: True
 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 说明:
 数组的长度不会超过10,000。
 你可以认为所有数字总和在 32 位有符号整数范围内
 * </pre>
 * 
 * Created by leslie on 2019/12/10.
 */
public class Problem523 {

    @Test
    public void test1() {
        Assert.assertTrue(checkSubarraySum3(new int[] { 23, 2, 4, 6, 7 }, 6));
        Assert.assertTrue(checkSubarraySum3(new int[] { 23, 2, 6, 4, 7 }, 6));
        Assert.assertFalse(checkSubarraySum3(new int[] { 23, 26, 4, 7 }, 0));
        Assert.assertTrue(checkSubarraySum3(new int[] { 0, 0 }, 0));
        Assert.assertTrue(checkSubarraySum3(new int[] { 0, 0, 0, 0, 0, 0 }, 1));
        Assert.assertFalse(checkSubarraySum3(new int[] { 0, 1, 0 }, 0));
    }

    /**
     * <pre>
     *    方法一: 穷举
     *    穷举所有子数组.
     *    注意k == 0, sum == 0 情况
     *
     *    时间复杂度: O(n^2) 空间复杂度: O(n)
     * </pre>
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1) {
            return false;
        }

        for (int i = 0; i < len - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < len; j++) {
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) {
                        return true;
                    }
                } else {
                    if (sum == 0 || (sum >= k && sum % k == 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     *     方法一的优化.
     * </pre>
     * 
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        // 不需要特殊处理.

        for (int i = 0; i < len - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < len; j++) {
                sum += nums[j];
                // 合并前面的2个分支.
                if (k == sum || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     *     方法二:  找规律.
     *     0 - i个元素和模k值为a,  假设i+1到j 这个子数组和为n*k, 即 (a + n * k) % k,  结果就是a, 也就是从0开始只要发现重复的a, 就可以确定有子数组和是n*k
     *     时间复杂度: O(n) 空间复杂度: O(n)
     * </pre>
     * 
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum3(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // k == 0 时, 不可以进行 % 计算.
            if (k != 0) sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) return true;
            } else map.put(sum, i);
        }
        return false;
    }
}
