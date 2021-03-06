package cn.leetcode.problem101_200.problem191_200;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 岛屿数量.
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围
 * 输入: 11110 11010 11000 00000 输出: 1
 * 输入: 11000 11000 00100 00011 输出: 3
 * </pre>
 * 
 * Created by leslie on 2019/11/29.
 */
public class Problem200 {

    @Test
    public void test1() {
        Assert.assertEquals(1, numIslands2(new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
                                                          { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } }));
        Assert.assertEquals(3, numIslands2(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
                                                          { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } }));
        Assert.assertEquals(0, numIslands2(new char[][] { {} }));
        Assert.assertEquals(1, numIslands2(new char[][] { { '1', '1', '1' }, { '0', '1', '0' }, { '1', '1', '1' } }));

    }

    /**
     * <pre>
     *     方法一: 遍历图, 遇到1时,深度优先算法, 上下左右递归将1标识.
     *     时间复杂度: O(M*N): M,N 分别是行数、列数. 空间复杂度: O(M*N)
     * </pre>
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int numOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    markIslandDFS(grid, i, j, rows, cols);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }

    /**
     * <pre>
     * 深度优先:
     *    递归实现
     *    1, 每一次遍历将当前点的四周(上、下、左、右)递归调用一次;  本题就是这样
     *    2, graph中，每一次遍历都有一个for循环，针对每个可访问的节点再递归调用;
     * </pre>
     * 
     * @param grid
     * @param i
     * @param j
     * @param rows
     * @param cols
     */
    private void markIslandDFS(char[][] grid, int i, int j, int rows, int cols) {
        if (i < 0 || j < 0 || i == rows || j == cols) {
            return;
        }
        if (grid[i][j] == '0' || grid[i][j] == 'a') {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = 'a';
        }
        markIslandDFS(grid, i + 1, j, rows, cols);
        markIslandDFS(grid, i - 1, j, rows, cols);
        markIslandDFS(grid, i, j + 1, rows, cols);
        markIslandDFS(grid, i, j - 1, rows, cols);
        return;
    }

    /**
     * <pre>
     *   方法二: 同方法一, 不同的是，遇到1时，广度优先算法
     * </pre>
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int numOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    markIslandBFS(grid, i, j, rows, cols);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }

    /**
     * <pre>
     *    广度优先:
     *       非递归实现;
     *       通常while循环, 条件是collection 非空
     *       1, 每一次遍历将四周(上、下、左、右)都处理一遍, 符合条件的加到collection中,  本题就是这样;
     *       2, 在graph中, 每一次遍历有一个for循环可连接的其他节点, 处理后，加入collection中;
     * </pre>
     *
     * @param grid
     * @param r
     * @param c
     * @param nr
     * @param nc
     */
    private void markIslandBFS(char[][] grid, int r, int c, int nr, int nc) {
        grid[r][c] = 'a'; // mark as visited
        Queue<Integer> neighbors = new LinkedList<>();
        neighbors.add(r * nc + c);
        while (!neighbors.isEmpty()) {
            int id = neighbors.remove();
            int row = id / nc;
            int col = id % nc;
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                neighbors.add((row - 1) * nc + col);
                grid[row - 1][col] = 'a';
            }
            if (row + 1 < nr && grid[row + 1][col] == '1') {
                neighbors.add((row + 1) * nc + col);
                grid[row + 1][col] = 'a';
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                neighbors.add(row * nc + col - 1);
                grid[row][col - 1] = 'a';
            }
            if (col + 1 < nc && grid[row][col + 1] == '1') {
                neighbors.add(row * nc + col + 1);
                grid[row][col + 1] = 'a';
            }
        }
    }
}
