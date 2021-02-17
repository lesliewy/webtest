package cn.leetcode.problem1_100.problem71_80;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     编辑距离
 *     给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 你可以对一个单词进行如下三种操作：

 插入一个字符
 删除一个字符
 替换一个字符

 示例 1：
 输入：word1 = "horse", word2 = "ros"
 输出：3
 解释：
 horse -> rorse (将 'h' 替换为 'r')
 rorse -> rose (删除 'r')
 rose -> ros (删除 'e')

 示例 2：
 输入：word1 = "intention", word2 = "execution"
 输出：5
 解释：
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')
  
 提示：
 0 <= word1.length, word2.length <= 500
 word1 和 word2 由小写英文字母组成
 * </pre>
 * 
 * Created by leslie on 2020/11/21.
 */
public class Problem72 {

    private String word1;
    private String word2;

    /**
     * <pre>
     *     递归解法.
     * </pre>
     * 
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        return minDistance1(word1.length() - 1, word2.length() - 1);
    }

    private int minDistance1(int i, int j) {
        // base case
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            return minDistance1(i - 1, j - 1); // 啥都不做
        } else {
            return Math.min(Math.min(minDistance1(i, j - 1) + 1, // 插入
                                     minDistance1(i - 1, j) + 1 // 删除
            ), minDistance1(i - 1, j - 1) + 1 // 替换
            );
        }
    }

    /**
     * <pre>
     *     带备忘录的递归.
     * </pre>
     * 
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        return minDistance2(word1.length() - 1, word2.length() - 1);
    }

    private Map<String, Integer> memo = new HashMap<>();

    private int minDistance2(int i, int j) {
        String key = i + "_" + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // base case
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            int res = minDistance2(i - 1, j - 1); // 啥都不做
            memo.put(key, res);
            return res;
        } else {
            int res = Math.min(Math.min(minDistance2(i, j - 1) + 1, // 插入
                                        minDistance2(i - 1, j) + 1 // 删除
            ), minDistance2(i - 1, j - 1) + 1 // 替换
            );
            memo.put(key, res);
            return res;
        }
    }

    /**
     * <pre>
     *   动态规划.
     * </pre>
     * 
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
