package cn.leetcode.problem901_1000.problem991_1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     找到小镇的法官
 *     在一个小镇里，按从 1 到 n 标记了 n 个人。传言称，这些人中有一个是小镇上的秘密法官。
 如果小镇的法官真的存在，那么：
 小镇的法官不相信任何人。
 每个人（除了小镇法官外）都信任小镇的法官。
 只有一个人同时满足属性 1 和属性 2 。
 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
  
 示例 1：
 输入：n = 2, trust = [[1,2]]
 输出：2

 示例 2：
 输入：n = 3, trust = [[1,3],[2,3]]
 输出：3

 示例 3：
 输入：n = 3, trust = [[1,3],[2,3],[3,1]]
 输出：-1

 示例 4：
 输入：n = 3, trust = [[1,2],[2,3]]
 输出：-1

 示例 5：
 输入：n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 输出：3
  
 提示：
 1 <= n <= 1000
 trust.length <= 10000
 trust[i] 是完全不同的
 trust[i][0] != trust[i][1]
 1 <= trust[i][0], trust[i][1] <= n
 * </pre>
 * 
 * Created by leslie on 2020/6/17.
 */
public class Problem997 {

    @Test
    public void testxxx() {
        Assert.assertEquals(2, findJudge3(2, new int[][] { { 1, 2 } }));
        Assert.assertEquals(3, findJudge3(3, new int[][] { { 1, 3 }, { 2, 3 } }));
        Assert.assertEquals(-1, findJudge3(3, new int[][] { { 1, 3 }, { 2, 3 }, { 3, 1 } }));
        Assert.assertEquals(-1, findJudge3(3, new int[][] { { 1, 2 }, { 2, 3 } }));
        Assert.assertEquals(3, findJudge3(4, new int[][] { { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 4, 3 } }));
        Assert.assertEquals(3, findJudge3(4, new int[][] { { 1, 2 }, { 1, 3 }, { 2, 1 }, { 2, 3 }, { 1, 4 }, { 4, 3 },
                                                           { 4, 1 } }));
    }

    /**
     * <pre>
     *     方法一: 使用数组，利用index作为人的标号.
     *             a[n] = {0, 0, ....0} , trust[i][j] 即第i个人不是法官，将 a[i] = -1; a[j]++
     *             判断a[n]中只有一个数是N-1, 其余都是-1
     *             注意: 如果已经是-1, 不能再做 ++
     * </pre>
     * 
     * @param n
     * @param trust
     * @return
     */
    public int findJudge1(int n, int[][] trust) {
        int[] a = new int[n + 1];
        int size = trust.length;
        for (int i = 0; i < size; i++) {
            int[] item = trust[i];
            a[item[0]] = -1;
            if (a[item[1]] != -1) {
                a[item[1]]++;
            }
        }
        int noJudgeNum = 0;
        int judge = -1;
        for (int i = 1; i < n + 1; i++) {
            if (a[i] == -1) {
                noJudgeNum++;
            } else {
                judge = i;
            }
        }
        return noJudgeNum == n - 1 && a[judge] == n - 1 ? judge : -1;
    }

    /**
     * <pre>
     *     方法二: 有向图.  入度、出度.  最终的节点入度为n-1, 出度为0.
     * </pre>
     * 
     * @param n
     * @param trust
     * @return
     */
    public int findJudge2(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1], outDegrees = new int[n + 1];
        int size = trust.length;
        for (int i = 0; i < size; i++) {
            int[] item = trust[i];
            outDegrees[item[0]]++;
            inDegrees[item[1]]++;
        }
        for (int i = 1; i < n + 1; i++) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <pre>
     *     方法三: 方法二中最终找的节点是入度为n-1, 出度为0， 可以换一种思维，找差值, (入度 - 出度 = n-1)的. 这样，可以把数组减为1个，出入度差值数组.
     * </pre>
     * 
     * @param n
     * @param trust
     * @return
     */
    public int findJudge3(int n, int[][] trust) {
        int[] diffDegress = new int[n + 1];
        int size = trust.length;
        for (int i = 0; i < size; i++) {
            int[] item = trust[i];
            diffDegress[item[0]]--;
            diffDegress[item[1]]++;
        }
        for (int i = 1; i < n + 1; i++) {
            if (diffDegress[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

}
