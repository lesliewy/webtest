package cn.algo.sort.directinsert;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class DirectInsertTest {
    @Test
    public void testSort1() {
        DirectInsert.sort1(null);
        int[] empty = {};
        DirectInsert.sort1(empty);

        int[] onlyOne = {3};
        DirectInsert.sort1(onlyOne);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        DirectInsert.sort1(normal);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, normal);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        DirectInsert.sort1(dup);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, dup);
    }

    @Test
    public void testSort2() {
        DirectInsert.sort2(null);
        int[] empty = {};
        DirectInsert.sort2(empty);

        int[] onlyOne = {3};
        DirectInsert.sort2(onlyOne);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        DirectInsert.sort2(normal);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, normal);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        DirectInsert.sort2(dup);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, dup);
    }
}
