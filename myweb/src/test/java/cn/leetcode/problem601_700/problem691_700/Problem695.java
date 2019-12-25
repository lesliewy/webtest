package cn.leetcode.problem601_700.problem691_700;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     岛屿的最大面积
 *     给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

 [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

 [[0,0,0,0,0,0,0,0]]
 对于上面这个给定的矩阵, 返回 0
 * </pre>
 * 
 * Created by leslie on 2019/12/13.
 */
public class Problem695 {

    private int areaOfIsland;

    @Test
    public void test1() {
        Assert.assertEquals(6,
                            maxAreaOfIsland(new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                                                          { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                                                          { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                          { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                                                          { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                                                          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                                                          { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                                                          { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } }));

        Assert.assertEquals(0, maxAreaOfIsland(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 } }));
    }

    /**
     * <pre>
     *     方法一: 深度优先.
     * </pre>
     * 
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        boolean[][] visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    areaOfIsland = 0;
                    maxAreaOfIslandDFS(grid, r, c, rows, cols, visited);
                    if (areaOfIsland > max) {
                        max = areaOfIsland;
                    }
                }
            }
        }
        return max;
    }

    private int maxAreaOfIslandDFS(int[][] grid, int r, int c, int rows, int cols, boolean[][] visited) {
        if (r >= rows || r < 0 || c >= cols || c < 0 || grid[r][c] == 0 || visited[r][c]) {
            return areaOfIsland;
        }

        if (grid[r][c] == 1 && !visited[r][c]) {
            // 将 areaOfIsland 作为实例变量.
            areaOfIsland++;
            visited[r][c] = true;
        }

        maxAreaOfIslandDFS(grid, r + 1, c, rows, cols, visited);
        maxAreaOfIslandDFS(grid, r - 1, c, rows, cols, visited);
        maxAreaOfIslandDFS(grid, r, c + 1, rows, cols, visited);
        maxAreaOfIslandDFS(grid, r, c - 1, rows, cols, visited);
        return areaOfIsland;
    }

}
