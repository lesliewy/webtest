package cn.algo.search.sequence;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class SequenceSearchTest {
    @Test
    public void testSearch() {
        int result = SequenceSearch.search(null, 0);
        Assert.assertEquals(-1, result);

        result = SequenceSearch.search(new int[]{}, 1);
        Assert.assertEquals(-1, result);

        result = SequenceSearch.search(new int[]{2, 9, 5}, 1);
        Assert.assertEquals(-1, result);

        result = SequenceSearch.search(new int[]{2, 9, 5}, 9);
        Assert.assertEquals(1, result);

        result = SequenceSearch.search(new int[]{2, 9, 1, 13, 26, 382, 5}, 9);
        Assert.assertEquals(1, result);
    }
}
