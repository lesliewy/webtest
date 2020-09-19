package cn.leetcode.problem701_800.problem741_750;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *    使用最小花费爬楼梯
 *数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

 示例 1:
 输入: cost = [10, 15, 20]
 输出: 15
 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。

  示例 2:
 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 输出: 6
 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。

 注意：
 cost 的长度将会在 [2, 1000]。
 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]
 * </pre>
 * 
 * Created by leslie on 2020/8/25.
 */
public class Problem746 {

    @Test
    public void testxxx() {
        Assert.assertEquals(15, minCostClimbingStairs1(new int[] { 10, 15, 20 }));
        Assert.assertEquals(6, minCostClimbingStairs1(new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 }));
    }

    /**
     * <pre>
     *     方法一: 动态规划
     *     先到i-1, 再踏上i;
     *     先到i-2, 再到i-1, 再2步跨越i;
     *     minCost[i] = min(minCost[i-1] + cost[i], minCost[i-2] + cost[i-1])
     * </pre>
     * 
     * @param cost
     * @return
     */
    public int minCostClimbingStairs1(int[] cost) {
        int size = cost.length;
        int[] minCost = new int[size];
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < size; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }
        return minCost[size - 1];
    }

}
