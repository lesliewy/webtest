package cn.leetcode.util;

/**
 * Created by leslie on 2019/11/28.
 */
public class ArrayUtils {

    public static int[] genIntRangeInclusive(int begin, int end) {
        int[] result = new int[end - begin + 1];
        int idx = 0;
        for (int i = begin; i <= end; i++) {
            result[idx++] = i;
        }
        return result;
    }
}
