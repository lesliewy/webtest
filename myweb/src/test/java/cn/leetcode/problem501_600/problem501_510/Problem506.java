package cn.leetcode.problem501_600.problem501_510;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     相对名次
 *     给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 (注：分数越高的选手，排名越靠前。)
 输入: [5, 4, 3, 2, 1]
 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可

 提示:

 N 是一个正整数并且不会超过 10000。
 所有运动员的成绩都不相同
 * </pre>
 * 
 * Created by leslie on 2020/1/6.
 */
public class Problem506 {

    @Test
    public void testxxx() {
        Assert.assertArrayEquals(new String[] { "Gold Medal", "Silver Medal", "Bronze Medal", "4", "5" },
                                 findRelativeRanks(new int[] { 5, 4, 3, 2, 1 }));
        Assert.assertArrayEquals(new String[] { "Gold Medal", "5", "Bronze Medal", "Silver Medal", "4" },
                                 findRelativeRanks(new int[] { 10, 3, 8, 9, 4 }));

    }

    /**
     * <pre>
     *     方法一: 排序.  TreeMap  key 排序， value 记录位置.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, Integer> ordered = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            ordered.put(nums[i], i);
        }
        String[] ans = new String[nums.length];
        int seq = 0;
        for (Map.Entry<Integer, Integer> entry : ordered.entrySet()) {
            if (seq == 0) {
                ans[entry.getValue()] = "Gold Medal";
            } else if (seq == 1) {
                ans[entry.getValue()] = "Silver Medal";
            } else if (seq == 2) {
                ans[entry.getValue()] = "Bronze Medal";
            } else {
                ans[entry.getValue()] = String.valueOf(seq + 1);
            }
            seq++;
        }
        return ans;
    }
}
