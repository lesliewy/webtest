package cn.leetcode.problem101_200.problem111_120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 三角形最小路径之和.
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 * [ [2],
 *  [3,4],
 * [6,5,7],
 * [4,1,8,3] ] 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）
 * </pre>
 * 
 * Created by leslie on 2019/11/29.
 */
public class Problem120 {

    @Test
    public void test1() {
        List<Integer> a1 = Arrays.asList(new Integer[] { 2 });
        List<Integer> a2 = Arrays.asList(new Integer[] { 3, 4 });
        List<Integer> a3 = Arrays.asList(new Integer[] { 6, 5, 7 });
        List<Integer> a4 = Arrays.asList(new Integer[] { 4, 1, 8, 3 });
        List<List<Integer>> a = new ArrayList<>();
        a.add(a1);
        a.add(a2);
        a.add(a3);
        a.add(a4);
        Assert.assertEquals(11, minimumTotal2(a));
    }

    /**
     * <pre>
     * 方法一: 动态规划.
     * DP 重复性(分治)
     *    problem(i,j) = min(sub(i+1,j) , sub(i+1,j+1)) + a[i,j]
     *    problem(i,j)：当前行当前列（二维数组）的向下面走的路径总数
     *    sub(i+1,j)：下一行当前列(即向下并向左边走)的路径总数
     *    sub(i+1,j+1)：下一行下一列(即向下并向右边走)的路径总数 路径总数包括自己所在位置a[i,j]，即到达当前位置所需的步数
     * 定义状态数组
     *    dp[i,j]
     * DP方程
     *    dp[i,j] = min(dp[i+1,j],dp[i+1][j+1])+dp[i,j]
     * 初始化数据
     *     一般是第一行n列和第一列n行或者最后一行n列最后一列n行
     *     但题中本意就是为了比较相邻数字和的大小，直接用原题的数据，最后一行n列即可对推到起点。
     * </pre>
     * 
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1))
                                       + triangle.get(i).get(j));
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * 方法二: 方法一一样，自底向上, 降维.
     * 
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        List<Integer> dp = new ArrayList<>(triangle.size() + 1);
        for (int i = 0; i < triangle.size() + 1; i++) {
            dp.add(0);
        }
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp.set(j, Math.min(dp.get(j), dp.get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return dp.get(0);
    }
}
