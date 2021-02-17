package cn.leetcode.problem1_100.problem51_60;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * <pre>
 *   N 皇后
 *   n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 输入：n = 4
 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 解释：如上图所示，4 皇后问题存在两个不同的解法。

 示例 2：
 输入：n = 1
 输出：[["Q"]]

 提示：
 1 <= n <= 9
 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * </pre>
 * 
 * Created by leslie on 2021/1/20.
 */
public class Problem51 {

    @Test
    public void test1() {
        System.out.println(solveNQueens(8));
    }

    List<List<String>> res = new ArrayList<>();

    /**
     * @param n
     * @return
     */
    List<List<String>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        String[][] board = initBoard(n);
        backtrack(board, 0);
        return res;
    }

    /**
     * <pre>
     *    路径：board 中小于 row 的那些行都已经成功放置了皇后
     *    选择列表：第 row 行的所有列都是放置皇后的选择
     *    结束条件：row 超过 board 的最后一行
     * </pre>
     * 
     * @param board
     * @param row
     */
    void backtrack(String[][] board, int row) {
        // 触发结束条件
        if (row == board.length) {
            res.add(transBoard(board));
            return;
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = "Q";
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = ".";
        }
    }

    /**
     * <pre>
     *    是否可以在 board[row][col] 放置皇后？
     * </pre>
     * 
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(String[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == "Q") return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == "Q") return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == "Q") return false;
        }
        return true;
    }

    private String[][] initBoard(int n) {
        String[][] res = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = ".";
            }
        }
        return res;
    }

    private List<String> transBoard(String[][] board) {
        List<String> res = new ArrayList<>();
        for (String[] row : board) {
            StringBuilder sb = new StringBuilder();
            for (String a : row) {
                sb.append(a);
            }
            res.add(sb.toString());
        }
        return res;
    }
}
