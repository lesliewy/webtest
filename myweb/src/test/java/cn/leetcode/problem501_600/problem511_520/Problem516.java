package cn.leetcode.problem501_600.problem511_520;

/**
 * <pre>
 *    最长回文子序列
 *    给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。

 示例 1:
 输入:
 "bbbab"
 输出:
 4
 一个可能的最长回文子序列为 "bbbb"。

 示例 2:
 输入:
 "cbbd"
 输出:
 2
 一个可能的最长回文子序列为 "bb"。

 提示：
 1 <= s.length <= 1000
 s 只包含小写英文字母
 * </pre>
 * 
 * Created by leslie on 2020/11/22.
 */
public class Problem516 {

    /**
     * <pre>
     *     动态规划: 迭代.
     *     dpii,j]: 在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j] 。
     * </pre>
     * 
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp 数组全部初始化为 0
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;
        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }
}
