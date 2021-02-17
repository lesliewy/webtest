package cn.leetcode.problem701_800.problem711_720;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     两个字符串的最小ASCII删除和
 *     给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 *     输入: s1 = "sea", s2 = "eat"
 输出: 231
 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 在 "eat" 中删除 "t" 并将 116 加入总和。
 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。

 输入: s1 = "delete", s2 = "leet"
 输出: 403
 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。

 注意:
 0 < s1.length, s2.length <= 1000。
 所有字符串中的字符ASCII值在[97, 122]之间
 * </pre>
 * 
 * Created by leslie on 2019/12/18.
 */
public class Problem712 {

    @Test
    public void test1() {
        Assert.assertEquals(231, minimumDeleteSum("sea", "eat"));
        Assert.assertEquals(403, minimumDeleteSum("delete", "leet"));
    }

    /**
     * <pre>
     *     方法一: 动态规划.
     *     最优解问题优先考虑动态规划。
     *     最优子结构: 分类.
     *     动态数组: 可能需要初始化.
     *     for 循环中，区分不同的最优子结构.
     * </pre>
     * 
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i), dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    /**
     * <pre>
     *     动态规划: 递归思路.
     * </pre>
     */
    int memo[][];

    int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 备忘录值为 -1 代表未曾计算
        memo = new int[m][n];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dp(s1, 0, s2, 0);
    }

    // 定义：将 s1[i..] 和 s2[j..] 删除成相同字符串，
    // 最小的 ASCII 码之和为 dp(s1, i, s2, j)。
    int dp(String s1, int i, String s2, int j) {
        int res = 0;
        // base case
        if (i == s1.length()) {
            // 如果 s1 到头了，那么 s2 剩下的都得删除
            for (; j < s2.length(); j++)
                res += s2.charAt(j);
            return res;
        }
        if (j == s2.length()) {
            // 如果 s2 到头了，那么 s1 剩下的都得删除
            for (; i < s1.length(); i++)
                res += s1.charAt(i);
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            // s1[i] 和 s2[j] 都是在 lcs 中的，不用删除
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        } else {
            // s1[i] 和 s2[j] 至少有一个不在 lcs 中，删一个
            memo[i][j] = Math.min(s1.charAt(i) + dp(s1, i + 1, s2, j), s2.charAt(j) + dp(s1, i, s2, j + 1));
        }
        return memo[i][j];
    }
}
