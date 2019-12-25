package cn.leetcode.problem301_400.problem361_370;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     有效的完全平方数
 *     给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 说明：不要使用任何内置的库函数，如  sqrt

 输入：16
 输出：True

 输入：14
 输出：False
 * </pre>
 * 
 * Created by leslie on 2019/12/17.
 */
public class Problem367 {

    @Test
    public void testxxx() {
        Assert.assertTrue(isPerfectSquare2(16));
        Assert.assertFalse(isPerfectSquare2(14));
        Assert.assertTrue(isPerfectSquare2(104976));
    }

    /**
     * <pre>
     *     方法一: 二分查找。
     * </pre>
     * 
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        long low = 1, high = num, mid;
        // 因为后面的high low 使用的是mid + 1, mid - 1, 所以这里必须要有等于.
        while (low <= high) {
            mid = (low + high) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    /**
     * <pre>
     *     方法二: 牛顿迭代法.
     * </pre>
     * 
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num) {
        int x = num;
        // 提高取值范围.
        while ((double) x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }

}
