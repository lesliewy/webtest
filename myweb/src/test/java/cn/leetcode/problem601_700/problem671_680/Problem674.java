package cn.leetcode.problem601_700.problem671_680;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     最长连续递增序列
 *     给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 *     输入: [1,3,5,4,7]
 输出: 3
 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。


 输入: [2,2,2,2,2]
 输出: 1
 解释: 最长连续递增序列是 [2], 长度为1。
 * </pre>
 * 
 * Created by leslie on 2019/12/13.
 */
public class Problem674 {

    @Test
    public void test1() {
        Assert.assertEquals(3, findLengthOfLCIS2(new int[] { 1, 3, 5, 4, 7 }));
        Assert.assertEquals(1, findLengthOfLCIS2(new int[] { 2, 2, 2, 2, 2 }));
        Assert.assertEquals(4, findLengthOfLCIS2(new int[] { 1, 3, 5, 7 }));
        Assert.assertEquals(4, findLengthOfLCIS2(new int[] { 1, 3, 5, 4, 2, 3, 4, 5 }));
    }

    /**
     * <pre>
     *     方法一: 遍历一次，当前元素和前一个元素比较大小, 计数器记录.
     *     时间复杂度: O(N)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int pre = nums[0], cur, curAns = 1, ans = -1;
        for (int i = 1; i < len; i++) {
            cur = nums[i];
            // 比前一个大, 计数加1.
            if (cur > pre) {
                curAns++;
                pre = cur;
            } else {
                // 小于等于前一个,记录最大值，重置计数器.
                ans = Math.max(curAns, ans);
                curAns = 1;
                pre = cur;
            }
        }
        return Math.max(curAns, ans);
    }

    /**
     * <pre>
     *     方法二: 优化.
     *     简化代码.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        int ans = 0, anchor = 0;
        // 从0开始, 减少哨兵代码.
        for (int i = 0; i < nums.length; ++i) {
            // 不是通过计数， 而是借助i来计算.
            if (i > 0 && nums[i - 1] >= nums[i]) anchor = i;
            ans = Math.max(ans, i - anchor + 1);
        }
        return ans;
    }
}
