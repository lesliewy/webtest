package cn.leetcode.problem501_600.problem541_550;

import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <pre>
 *     给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 *     你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 *     当你将所有盒子都去掉之后，求你能获得的最大积分和。

 *     示例 1：
 *     输入: [1, 3, 2, 2, 2, 3, 4, 3, 1]
 *     输出: 23
 *     解释:
 *     [1, 3, 2, 2, 2, 3, 4, 3, 1]
 *     ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 *     ----> [1, 3, 3, 3, 1] (1*1=1 分)
 *     ----> [1, 1] (3*3=9 分)
 ----> [] (2*2=4 分)
 * </pre>
 * 
 * Created by leslie on 2019/12/8.
 */
public class Problem546 {

    private int maxScore;

    private int maxColorNum;

    @Test
    public void test1() {
        // Assert.assertEquals(23, removeBoxes(new int[] { 1, 3, 2, 2, 2, 3, 4, 3, 1 }));
    }

    @Test
    public void test2() {
        // Assert.assertEquals(1, removeBoxes(new int[] { 1 }));
    }

    @Test
    public void test3() {
        // Assert.assertEquals(2, removeBoxes(new int[] { 1, 2 }));
    }

    @Test
    public void test4() {
        Assert.assertEquals(5, removeBoxes(new int[] { 1, 1, 2, 1 }));
    }

    @Before
    public void reset() {
        maxColorNum = 0;
        maxScore = 0;
    }

    /**
     * @todo 未解决.
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int len = boxes.length;
        // 将boxes 转为 LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i : boxes) {
            if (i > maxColorNum) {
                maxColorNum = i;
            }
            linkedList.add(i);
        }
        backtrack(linkedList, 0);
        return maxScore;
    }

    private void backtrack(LinkedList<Integer> list, int score) {
        if (list.isEmpty()) {
            if (score > maxScore) {
                maxScore = score;
            }
            return;
        }

        Set<Integer> allColorNum = getAllColorNum(list);
        for (int colorNum : allColorNum) {
            List<Integer> numOfSame = getSameNumIndex(list, colorNum);
            removeIdx(list, numOfSame);
            backtrack(list, score + numOfSame.size() * numOfSame.size());
        }
    }

    private List<Integer> getSameNumIndex(LinkedList<Integer> list, int colorNum) {
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        for (Integer i : list) {
            if (i == colorNum) {
                result.add(idx);
            } else if (result.size() > 0) {
                break;
            }
            idx++;
        }
        return result;
    }

    private void removeIdx(List<Integer> list, List<Integer> ids) {
        for (Integer idx : ids) {
            list.remove(idx);
        }
        return;
    }

    private Set<Integer> getAllColorNum(LinkedList<Integer> list) {
        Set<Integer> s = new HashSet<>();
        for (Integer i : list) {
            s.add(i);
        }
        return s;
    }
}
