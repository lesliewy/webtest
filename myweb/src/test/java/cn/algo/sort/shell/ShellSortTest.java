package cn.algo.sort.shell;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/28.
 */
public class ShellSortTest {
    @Test
    public void testSort() {
        ShellSort.sort(null);
        int[] empty = {};
        ShellSort.sort(empty);

        int[] onlyOne = {3};
        ShellSort.sort(onlyOne);
        Assert.assertArrayEquals(new int[]{3}, onlyOne);

        int[] normal = {1, 8, 9, 7, 2, 12, 15, 23};
        ShellSort.sort(normal);
        Assert.assertArrayEquals(new int[]{1, 2, 7, 8, 9, 12, 15, 23}, normal);
//        System.out.println(Ints.asList(input));

        int[] dup = {3, 78, 982, 4, 1, 2, 78, 3};
        ShellSort.sort(dup);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 3, 4, 78, 78, 982}, dup);
    }
}
