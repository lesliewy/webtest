package cn.leetcode.problem1001_1100.problem1021_1030;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     除数博弈
 *爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：

 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 用 N - x 替换黑板上的数字 N 。
 如果玩家无法执行这些操作，就会输掉游戏。

 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏。

 示例 1：
 输入：2
 输出：true
 解释：爱丽丝选择 1，鲍勃无法进行操作。

 示例 2：
 输入：3
 输出：false
 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。

 提示：
 1 <= N <= 1000
 * </pre>
 * 
 * Created by leslie on 2020/8/24.
 */
public class Problem1025 {

    @Test
    public void testxxx() {
        Assert.assertTrue(divisorGame(2));
        Assert.assertFalse(divisorGame(3));
    }

    /**
     * <pre>
     *     方法一: 猜想.
     *     f(1): 先手 败。
     *     f(2): Alice 选择1, Bob 是f(1)先手, Bob 败, 先手胜;
     *     f(3): Alice 只能1，Bob是(f2)先手, Bob胜, 先手败;
     *     f(4): Alice 可选1，2，选1剩3，根据f(3)先手败，所以f(4)先手胜.
     *     ....
     * </pre>
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }
}
