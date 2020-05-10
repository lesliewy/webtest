package cn.algo.thinking.dynamicprogramming;

import org.junit.Test;

/**
 * Created by leslie on 2019/12/4.
 */
public class MinDistanceTest {

    @Test
    public void testMinDist() {
        MinDistance minDistance = new MinDistance();
        long begin = System.currentTimeMillis();
        minDistance.minDistBT(0, 0, 0, getArray1(), 3);
        System.out.println("minDistBT: " + minDistance.getMinDist() + "; eclipsed time: " + (System.currentTimeMillis() - begin) + "ms.");

        int minDist = minDistance.minDistDP(getArray1(), 4);
        System.out.println("minDistance.minDistDP: " + minDist);

        minDist = minDistance.minDist(3, 3, getArray1(), 4);
        System.out.println("minDistance.minDist: " + minDist);
    }

    private int[][] getArray1() {
        return new int[][]{
                {1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}
        };
    }
}
