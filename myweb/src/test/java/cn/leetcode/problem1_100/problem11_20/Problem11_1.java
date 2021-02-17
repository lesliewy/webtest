package cn.leetcode.problem1_100.problem11_20;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     旋转数组的最小数字
 *     把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

 示例 1：

 输入：[3,4,5,1,2]
 输出：1
 示例 2：

 输入：[2,2,2,0,1]
 输出：0
 * </pre>
 * 
 * Created by leslie on 2021/2/10.
 */

public class Problem11_1 {

    @Test
    public void test1() {
        Assert.assertEquals(1, minArray3(new int[] { 3, 4, 5, 1, 2 }));
        Assert.assertEquals(0, minArray3(new int[] { 2, 2, 2, 0, 1 }));
        Assert.assertEquals(1, minArray3(new int[] { 1, 3, 5 }));
        Assert.assertEquals(1, minArray3(new int[] { 3, 1, 1 }));
        Assert.assertEquals(1, minArray3(new int[] { 3, 1 }));
        Assert.assertEquals(1, minArray3(new int[] { 10, 1, 10, 10, 10 }));
    }

    /**
     * <pre>
     *     顺序遍历，找到突然变小的那个数.
     *     O(n)
     * </pre>
     * 
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int pre = numbers[0], curr;
        for (int i = 1; i < numbers.length; i++) {
            curr = numbers[i];
            if (curr < pre) {
                return curr;
            }
            pre = curr;
        }
        return numbers[0];
    }

    /**
     * <pre>
     *    二分.  这里是错误的.
     * </pre>
     * 
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }

        int low = 0, high = numbers.length - 1, mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (numbers[mid] > numbers[low]) {
                low = mid + 1;
            } else if (numbers[mid] < numbers[low]) {
                high = mid;
            } else if (numbers[mid] == numbers[low]) {   // 问题在这里.
                low += 1;
            }
        }
        return numbers[low];
    }

    /**
     * <pre>
     *     二分. 正确.
     *     选择numbers[high]作为判断条件，而不是numbers[low]   因为当出现numbers[mid] == numbers[low] 时， mid 有可能等于low,  但是 mid 一定不等于 high.
     *     因为 mid = low + (high - low)/2 的原因，向下取整的.
     * </pre>
     * @param numbers
     * @return
     */
    public int minArray3(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        // 这里不能用 <= , 等于时, mid, low, high 三者是同一个值.
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) { // 说明已经到了旋转部分;
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {  // 说明旋转部分在右侧.
                low = pivot + 1;
            } else {   // 不确定在哪边, 但numbers[pivot] == numbers[high],  可以排除掉该high. 最关键的是这里pivot 一定不等于high.
                high -= 1;
            }
        }
        return numbers[low];   // 也可以 return numbers[high]   因为low == high时跳出while.
    }
}
