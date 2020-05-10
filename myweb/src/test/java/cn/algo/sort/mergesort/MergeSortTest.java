package cn.algo.sort.mergesort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class MergeSortTest {
    @Test
    public void testSort() {
        MergeSort.sort(null);
        int[] empty = {};
        MergeSort.sort(empty);

        int[] onlyOne = {3};
        MergeSort.sort(onlyOne);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        int[] result = MergeSort.sort(normal);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, result);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        result = MergeSort.sort(dup);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, result);
    }
}
