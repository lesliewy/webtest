package cn.leetcode.problem1_100.problem1_10;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/11/15.
 */
public class Problem7 {

    @Test
    public void test1() {
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-8789, reverse(-9878));
        Assert.assertEquals(0, reverse(-1234567899));
    }

    public int reverse(int x) {
        String reverseStr = new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString();
        String signReverseStr = x >= 0 ? reverseStr : "-" + reverseStr;
        int result = 0;
        try {
            result = Integer.parseInt(signReverseStr);
        } catch (Exception e) {
            System.out.println("out of bounds.");
        }

        return result;
    }
}
