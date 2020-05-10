package cn.algo.sort.quicksort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class QuickSortTest {
    @Test
    public void testSort1() {
        QuickSort.sort1(null, 0, 0);
        int[] empty = {};
        QuickSort.sort1(empty, 0, empty.length - 1);

        int[] onlyOne = {3};
        QuickSort.sort1(onlyOne, 0, onlyOne.length - 1);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        QuickSort.sort1(normal, 0, normal.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, normal);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        QuickSort.sort1(dup, 0, dup.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, dup);
    }

    @Test
    public void testSort2() {
        QuickSort.sort2(null, 0, 0);
        int[] empty = {};
        QuickSort.sort2(empty, 0, empty.length - 1);

        int[] onlyOne = {3};
        QuickSort.sort2(onlyOne, 0, onlyOne.length - 1);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        QuickSort.sort2(normal, 0, normal.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, normal);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        QuickSort.sort2(dup, 0, dup.length - 1);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, dup);
    }
}
