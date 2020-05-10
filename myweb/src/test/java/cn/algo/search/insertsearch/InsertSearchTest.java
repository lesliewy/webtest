package cn.algo.search.insertsearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/29.
 */
public class InsertSearchTest {
    @Test
    public void testSearch() {
        int result = InsertSearch.search(null, 00);
        Assert.assertEquals(-1, result);

        result = InsertSearch.search(new int[]{}, 0);
        Assert.assertEquals(-1, result);

        result = InsertSearch.search(new int[]{2, 5, 9}, 3);
        Assert.assertEquals(-1, result);

        result = InsertSearch.search(new int[]{2, 5, 9}, 9);
        Assert.assertEquals(2, result);

        result = InsertSearch.search(new int[]{1, 2, 5, 9, 13, 26, 382}, 9);
        Assert.assertEquals(3, result);

        result = InsertSearch.search(new int[]{1, 2, 5, 9, 13, 26, 382}, 10);
        Assert.assertEquals(-1, result);
        result = InsertSearch.search(new int[]{1, 2, 5, 9, 13, 26, 382}, 20);
        Assert.assertEquals(-1, result);
    }
}
