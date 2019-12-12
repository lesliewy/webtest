package cn.leetcode.problem401_500.problem401_410;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     根据身高重建队列
 *     假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *     注意： 总人数少于1100人。
 *      示例
 *      输入:
 *         [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *      输出:
 *         [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * </pre>
 * 
 * Created by leslie on 2019/11/30.
 */
public class Problem406 {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } },
                                 reconstructQueue(new int[][] { { 5, 0 }, { 7, 0 }, { 5, 2 }, { 6, 1 }, { 4, 4 },
                                                                { 7, 1 } }));
    }

    public int[][] reconstructQueue(int[][] people) {
        return null;
    }
}
