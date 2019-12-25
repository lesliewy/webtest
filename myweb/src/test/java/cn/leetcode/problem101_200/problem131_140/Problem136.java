package cn.leetcode.problem101_200.problem131_140;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     只出现一次的数字
 *     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 说明：
 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 输入: [2,2,1]
 输出: 1

 输入: [4,1,2,1,2]
 输出: 4
 * </pre>
 * 
 * Created by leslie on 2019/12/22.
 */
public class Problem136 {

    @Test
    public void test1() {
        Assert.assertEquals(1, singleNumber2(new int[] { 2, 2, 1 }));
        Assert.assertEquals(4, singleNumber2(new int[] { 4, 1, 2, 1, 2 }));
    }

    /**
     * <pre>
     *     方法一: 使用hashset保存出现过的元素.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int len = nums.length;
        Set<Integer> all = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (all.contains(nums[i])) {
                all.remove(nums[i]);
            } else {
                all.add(nums[i]);
            }
        }
        return all.iterator().next();
    }

    /**
     * <pre>
     *     方法二: 位操作.
     *     a ^ a = 0
     *     a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
     *     所有数字做异或，结果就是只出现一次的那个.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}
