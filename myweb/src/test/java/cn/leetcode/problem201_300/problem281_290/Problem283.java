package cn.leetcode.problem201_300.problem281_290;

import java.util.Arrays;

import org.junit.Test;

/**
 * <pre>
 *     移动零
 *     给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:
 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]

 说明:
 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。
 * </pre>
 * 
 * Created by leslie on 2020/10/27.
 */
public class Problem283 {

    @Test
    public void test1() {
        moveZeroes(new int[] { 0, 1, 0, 3, 12 });
    }

    /**
     * <pre>
     *    先去除0， 再后补0.
     *    去除0使用快慢指针，参考 Problem 27
     * </pre>
     * 
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
        Arrays.stream(nums).forEach(i -> System.out.println(i));
    }
}
