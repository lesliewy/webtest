package cn.algo.search.binarysearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class BinarySearchTest {
    @Test
    public void testSearch1() {
        int result = BinarySearch.search1(null, 0);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search1(new int[]{}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search1(new int[]{2, 5, 9}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search1(new int[]{2, 5, 9}, 9);
        Assert.assertEquals(2, result);

        result = BinarySearch.search1(new int[]{1, 2, 5, 9, 13, 26, 382}, 9);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSearch2() {
        int result = BinarySearch.search2(null, 0);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search2(new int[]{}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search2(new int[]{2, 5, 9}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search2(new int[]{2, 5, 9}, 9);
        Assert.assertEquals(2, result);

        result = BinarySearch.search2(new int[]{1, 2, 5, 9, 13, 26, 382}, 9);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSearch3() {
        int result = BinarySearch.search3(null, 0, 0);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search3(new int[]{}, 0, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search3(new int[]{2, 5, 9}, 3, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search3(new int[]{2, 5, 9}, 3, 9);
        Assert.assertEquals(2, result);

        result = BinarySearch.search3(new int[]{1, 2, 5, 9, 13, 26, 382}, 7, 9);
        Assert.assertEquals(3, result);

        result = BinarySearch.search3(new int[]{1, 2, 5, 9, 13, 26, 382}, 7, 10);
        Assert.assertEquals(-5, result);
        result = BinarySearch.search3(new int[]{1, 2, 5, 9, 13, 26, 382}, 7, 20);
        Assert.assertEquals(-6, result);
    }

    @Test
    public void testSearch4() {
        int result = BinarySearch.search4(null, 0);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search4(new int[]{}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search4(new int[]{2, 5, 9}, 1);
        Assert.assertEquals(-1, result);

        result = BinarySearch.search4(new int[]{2, 5, 9}, 9);
        Assert.assertEquals(2, result);

        result = BinarySearch.search4(new int[]{1, 2, 5, 9, 13, 26, 382}, 9);
        Assert.assertEquals(3, result);

        result = BinarySearch.search4(new int[]{1, 2, 5, 9, 13, 26, 382}, 10);
        Assert.assertEquals(-1, result);
        result = BinarySearch.search4(new int[]{1, 2, 5, 9, 13, 26, 382}, 20);
        Assert.assertEquals(-1, result);
    }
}
