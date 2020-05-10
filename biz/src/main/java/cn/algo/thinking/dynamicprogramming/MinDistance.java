package cn.algo.thinking.dynamicprogramming;

/**
 * <pre>
 *     假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。棋子起始位置在左上角，终止位置在右下角。
 *     我们将棋子从左上角移动到右下角。每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。
 *     我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
 * </pre>
 *
 * Created by leslie on 2019/12/4.
 */
public class MinDistance {

    private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量

    private int[][] mem = new int[4][4];

    /**
     * <pre>
     *     回溯法，穷举所有.
     *     另外也可以回溯法 + 备忘录, 减少重复计算.
     *    调用方式：minDistBacktracing(0, 0, 0, w, n);
     * </pre>
     **/
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了n-1, n-1这个位置了，这里看着有点奇怪哈，你自己举个例子看下
        if (i == n && j == n) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        if (i < n) { // 往下走，更新i=i+1, j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) { // 往右走，更新i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    /**
     * 动态规划: 状态转移表.
     * @param matrix
     * @param n
     * @return
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }

    public int getMinDist() {
        return minDist;
    }


    /**
     * <pre>
     *     动态规划: 状态转移方程.
     *         min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     *     两种实现: 递归 + 备忘录;
     *              迭代递推,类似minDistDP().
     * </pre>
     * @param i
     * @param j
     * @param matrix
     * @param n
     * @return
     */
    public int minDist(int i, int j, int[][] matrix, int n) { // 调用minDist(n-1, n-1);
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1, matrix, n);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j, matrix, n);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }
}
