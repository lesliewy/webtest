package cn.algo.sort.radixsort;

import org.junit.Assert;
import org.junit.Test;

import cn.algo.sort.redixsort.RadixSort;

/**
 * Created by leslie on 2019/8/28.
 */
public class RadixSortTest {

    @Test
    public void testSort() {
        RadixSort.sort(null);
        int[] empty = {};
        RadixSort.sort(empty);

        int[] onlyOne = { 3 };
        RadixSort.sort(onlyOne);
        Assert.assertArrayEquals(new int[] { 3 }, onlyOne);

        int[] normal = { 1, 8, 9, 7, 2, 12, 15, 23 };
        RadixSort.sort(normal);
        Assert.assertArrayEquals(new int[] { 1, 2, 7, 8, 9, 12, 15, 23 }, normal);
        // System.out.println(Ints.asList(input));

        int[] dup = { 3, 78, 982, 4, 1, 2, 78, 3 };
        RadixSort.sort(dup);
        Assert.assertArrayEquals(new int[] { 1, 2, 3, 3, 4, 78, 78, 982 }, dup);
    }
}
