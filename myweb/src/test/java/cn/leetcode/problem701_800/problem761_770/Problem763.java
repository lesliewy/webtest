package cn.leetcode.problem701_800.problem761_770;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * <pre>
 *     划分字母区间
 *     字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *     输入: S = "ababcbacadefegdehijhklij"
 *     输出: [9,7,8]
 *     解释:
 *     划分结果为 "ababcbaca", "defegde", "hijhklij"。
 *     每个字母最多出现在一个片段中。    像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * </pre>
 * 
 * Created by leslie on 2019/11/30.
 */
public class Problem763 {

    @Test
    public void test1() {

        // Assert.assertThat(partitionLabels("ababcbacadefegdehijhklij"),
        // CoreMatchers.is(Arrays.stream(new int[] { 9, 7, 8 }).boxed().collect(Collectors.toList())));
    }

    /**
     * <pre>
     *     方法一: 贪心算法.
     *     策略就是不断地选择从最左边起最小的区间。可以从第一个字母开始分析，假设第一个字母是 'a'，那么第一个区间一定包含最后一次出现的 'a'。
     *     但第一个出现的 'a' 和最后一个出现的 'a' 之间可能还有其他字母，这些字母会让区间变大。举个例子，在 "abccaddbeffe" 字符串中，第一个最小的区间是 "abccaddb"。
     *     通过以上的分析，我们可以得出一个算法：对于遇到的每一个字母，去找这个字母最后一次出现的位置，用来更新当前的最小区间
     * </pre>
     * 
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        // 一次循环找到所有字符最后一次出现的index.
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            // 当前区间的最大值.
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}
