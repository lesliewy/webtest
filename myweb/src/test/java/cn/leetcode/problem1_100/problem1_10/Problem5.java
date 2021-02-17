package cn.leetcode.problem1_100.problem1_10;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     最长回文子串
 *     给你一个字符串 s，找到 s 中最长的回文子串。

 示例 1：
 输入：s = "babad"
 输出："bab"
 解释："aba" 同样是符合题意的答案。

 示例 2：
 输入：s = "cbbd"
 输出："bb"

 示例 3：
 输入：s = "a"
 输出："a"

 示例 4：
 输入：s = "ac"
 输出："a"
  
 提示：
 1 <= s.length <= 1000
 s 仅由数字和英文字母（大写和/或小写）组成
 * </pre>
 * 
 * Created by leslie on 2021/2/15.
 */
public class Problem5 {

    @Test
    public void testxxx() {
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
        Assert.assertEquals("a", longestPalindrome("a"));
    }

    /**
     * <pre>
     *     状态: dp[i][j]:  s[i..j] 中的最长回文子串.
     *     最终的是dp[0][n]
     * </pre>
     * 
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        String ans = "";
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    ans = s.substring(i, j + 1);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return ans;
    }
}
