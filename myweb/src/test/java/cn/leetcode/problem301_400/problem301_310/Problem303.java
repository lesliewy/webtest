package cn.leetcode.problem301_400.problem301_310;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *      区域和检索 - 数组不可变
 *      给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

 示例：
 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 说明:

 你可以假设数组不可变。
 会多次调用 sumRange 方法。
 * </pre>
 * 
 * Created by leslie on 2020/8/4.
 */
public class Problem303 {

    @Test
    public void testxxx() {
//        Problem303 p1 = new Problem303(new int[] { -2, 0, 3, -5, 2, -1 });
        Problem303 p1 = new Problem303();
        p1.nums = new int[]{-2, 0, 3, -5, 2, -1 };

        Assert.assertEquals(1, p1.sumRange2(0, 2));
        Assert.assertEquals(1, p1.sumRange2(0, 2));
        Assert.assertEquals(-1, p1.sumRange2(2, 5));
        Assert.assertEquals(-3, p1.sumRange2(0, 5));
    }

    public int[] nums;
    private Map<String, Integer> cache = new HashMap<>();

    public Problem303(){

    }
    /*
    public Problem303(int[] nums){
        this.nums = nums;
    }
    */

    /**
     * <pre>
     *     方法一: 每次调用都会进行for循环, 不带缓存.
     * </pre>
     * @param i
     * @param j
     * @return
     */
    public int sumRange1(int i, int j) {
        if (j < 0) {
            return 0;
        }
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }


    /**
     * <pre>
     *     方法二: 缓存每个区间的数字和. 利用空间换时间.
     * </pre>
     * @param i
     * @param j
     * @return
     */
    public int sumRange2(int i, int j) {
        if (j < 0) {
            return 0;
        }
        String key = String.valueOf(i) + String.valueOf(j);
        Integer valueInCache = cache.get(key);
        if(valueInCache != null){
            return valueInCache;
        }
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        cache.put(key, sum);
        return sum;
    }

    /**
     * <pre>
     *     方法三: 预生成cache.  O(n^2) 完成所有pair 的cache.
     * </pre>
     */

    /**
     * <pre>
     *     方法四: 预生成cache. 但是不需要生成所有的pair.   只生成0-i 的。  sum(i, j) = cache.get(j) - cache.get(i-1).
     * </pre>
     */
}
