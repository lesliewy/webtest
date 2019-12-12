package cn.leetcode.problem1_100.problem61_70;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.ArrayUtils;

/**
 * <p>
 * x的平方根.
 * </p>
 * <p>
 * 实现 int sqrt(int x) 函数。 计算并返回 x 的平方根，其中 x 是非负整数。 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * </p>
 * Created by leslie on 2019/11/28.
 */
public class Problem69 {

    @Test
    public void test1() {
        Assert.assertEquals(2, mySqrt3(4));
        Assert.assertEquals(2, mySqrt3(5));
        Assert.assertEquals(2, mySqrt3(8));
        Assert.assertEquals(3, mySqrt3(13));
        Assert.assertEquals(5, mySqrt3(25));
        Assert.assertEquals(8, mySqrt3(77));
        Assert.assertEquals(46339, mySqrt3(2147395599));
        Assert.assertEquals(46340, mySqrt3(2147483647));
    }

    /**
     * <p>
     * 方法一: 取x/2作为最大值建数组. 二分查找，依次判断数字n的平方是否等于x, n-1, n+1 的情况. 找出合适的.
     * </p>
     * <p>
     * 空间复杂度很高，x取2147395599时超出内存限制.
     * </p>
     * 
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1 || x == 2 || x == 3) {
            return 1;
        }
        if (x == 5) {
            return 2;
        }
        int[] arr = ArrayUtils.genIntRangeInclusive(1, x / 2);
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) >> 1;
            double pow2 = Math.pow(arr[middle], 2);
            if (pow2 == x) {
                return arr[middle];
            }
            if (pow2 > x) {
                if (Math.pow(arr[middle - 1], 2) < x) {
                    return arr[middle - 1];
                }
                high = middle - 1;

            }
            if (pow2 < x) {
                if (Math.pow(arr[middle + 1], 2) > x) {
                    return arr[middle];
                }
                low = middle + 1;
            }
        }
        return 0;
    }

    /**
     * 方法二: 思想同方法一，但是不需要数组.
     * <p>
     * high 需要用long， 否则high + low 可能超出int的范围.
     * </p>
     * <p>
     * 时间复杂度: O(logN)
     * </p>
     * 
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        long low = 1;
        long high = x;
        while (low <= high) {
            long middle = (low + high) >>> 1;
            double pow2 = Math.pow(middle, 2);
            if (pow2 == x) {
                return (int) middle;
            } else if (pow2 > x) {
                if (Math.pow(middle - 1, 2) < x) {
                    return (int) middle - 1;
                }
                high = middle - 1;
            } else if (pow2 < x) {
                if (Math.pow(middle + 1, 2) > x) {
                    return (int) middle;
                }
                low = low + 1;
            }
        }
        return (int) low;
    }

    /**
     * 方法三: 牛顿迭代法: 下面这种方法可以很有效地求出根号 aa 的近似值：首先随便猜一个近似值 xx，然后不断令 xx 等于 xx 和 a/xa/x 的平均数，迭代个六七次后 xx 的值就已经相当精确了。
     * <p>
     * 时间上比方法二快了一个2个数量级.
     * </p>
     */
    public int mySqrt3(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        return (int) x;
    }
}
