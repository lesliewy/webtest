package cn.leetcode.problem101_200.problem131_140;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     只出现一次的数字 II
 *     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

 说明：
 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗

 输入: [2,2,3,2]
 输出: 3

 输入: [0,1,0,1,0,1,99]
 输出: 99
 * </pre>
 * 
 * Created by leslie on 2019/12/22.
 */
public class Problem137 {

    @Test
    public void test1() {
        Assert.assertEquals(3, singleNumber(new int[] { 2, 2, 3, 2 }));
        Assert.assertEquals(99, singleNumber(new int[] { 0, 1, 0, 1, 0, 1, 99 }));
    }

    /**
     * <pre>
     *     方法一: hashmap 计数.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            Integer times = map.get(i);
            if (times == null) {
                map.put(i, 1);
            } else {
                map.put(i, times + 1);
            }
        }

        return map.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();
        /*
         * for (Map.Entry<Integer, Integer> e : map.entrySet()) { if (e.getValue() == 1) { return e.getKey(); } } return
         * 0;
         */
    }

    /**
     * <pre>
     *     方法二: 位操作.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        return 0;
    }
}
