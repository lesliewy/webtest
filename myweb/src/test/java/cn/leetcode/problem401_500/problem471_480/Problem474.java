package cn.leetcode.problem401_500.problem471_480;

/**
 * <pre>
 *     一和零
 *
 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

 示例 1：
 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 输出：4
 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。

 示例 2：
 输入：strs = ["10", "0", "1"], m = 1, n = 1
 输出：2
 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。

 提示：
 1 <= strs.length <= 600
 1 <= strs[i].length <= 100
 strs[i] 仅由 '0' 和 '1' 组成
 1 <= m, n <= 100
 * </pre>
 * 
 * Created by leslie on 2021/2/15.
 */
public class Problem474 {

    /**
     * <pre>
     *     尝试题目问啥，就把啥定义成状态。dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量。
     * </pre>
     * 
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] count = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];
                    int zeros = count[0];
                    int ones = count[1];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private int[] countZeroAndOne(String str) {
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }

    /**
     * <pre>
     *     方法一优化.
     *     因为当前行只参考了上一行的值，因此可以使用「滚动数组」，也可以「从后向前赋值」
     *     这里选用「从后向前赋值」
     * </pre>
     * 
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (String s : strs) {
            int[] zeroAndOne = countZeroAndOne(s);
            int zeros = zeroAndOne[0];
            int ones = zeroAndOne[1];
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
