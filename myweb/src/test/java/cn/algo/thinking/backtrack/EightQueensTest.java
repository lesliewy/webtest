package cn.algo.thinking.backtrack;

import org.junit.Test;

/**
 * Created by leslie on 2019/12/3.
 */
public class EightQueensTest {
    @Test
    public void test1() {
        EightQueens eightQueens = new EightQueens();
        long begin = System.currentTimeMillis();
        eightQueens.cal8queens(0);
        System.out.println("eclipsed: " + (System.currentTimeMillis() - begin) + "ms.");
    }
}
