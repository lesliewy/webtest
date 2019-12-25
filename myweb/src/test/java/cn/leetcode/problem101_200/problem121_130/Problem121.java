package cn.leetcode.problem101_200.problem121_130;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     买卖股票的最佳时机
 *     给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 注意你不能在买入股票前卖出股票。

 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0
 * </pre>
 * 
 * Created by leslie on 2019/12/22.
 */
public class Problem121 {

    @Test
    public void test1() {
        Assert.assertEquals(5, maxProfit2(new int[] { 7, 1, 5, 3, 6, 4 }));
        Assert.assertEquals(0, maxProfit2(new int[] { 7, 6, 4, 3, 1 }));
    }

    /**
     * <pre>
     *     方法一: 穷举.
     *     两轮遍历找到利润最大.
     * </pre>
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                ans = Math.max(ans, prices[j] - prices[i]);
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     方法二:
     * </pre>
     * 
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int buy = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            // 如果当前价格大于之前的买入价格, 则今天买入.
            buy = Math.min(buy, prices[i]);
            // 比较利润大小.
            ans = Math.max(ans, prices[i] - buy);
        }
        return ans;
    }

}
