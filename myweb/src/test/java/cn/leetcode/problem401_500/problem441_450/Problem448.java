package cn.leetcode.problem401_500.problem441_450;

/**
 * <pre>
 *    找到所有数组中消失的数字
 *    给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

 示例:
 输入:
 [4,3,2,7,8,2,3,1]
 输出:
 [5,6]
 * </pre>
 * 
 * Created by leslie on 2021/1/29.
 */
public class Problem448 {

    /**
     * <pre>
     *    a, 排序再遍历;
     *    b, hashset 存储再依次查找;
     * </pre>
     */

    /**
     * <pre>
     *     位运算.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 新补的索引
        res += n - 0;
        // 剩下索引和元素的差加起来
        for (int i = 0; i < n; i++)
            res += i - nums[i];
        return res;
    }
}
