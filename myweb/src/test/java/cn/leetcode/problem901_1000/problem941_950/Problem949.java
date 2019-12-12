package cn.leetcode.problem901_1000.problem941_950;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *      给定数字能组成的最大时间
 *      给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
 以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。

 示例 1：
 输入：[1,2,3,4]
 输出："23:41"

 输入：[5,5,5,5]
 输出：""

 提示：

 A.length == 4
 0 <= A[i] <= 9
 * </pre>
 * 
 * Created by leslie on 2019/12/11.
 */
public class Problem949 {

    @Test
    public void test1() {
        Assert.assertEquals("23:41", largestTimeFromDigits(new int[] { 1, 2, 3, 4 }));
        Assert.assertEquals("00:00", largestTimeFromDigits(new int[] { 0, 0, 0, 0 }));
        Assert.assertEquals("", largestTimeFromDigits(new int[] { 5, 5, 5, 5 }));
        Assert.assertEquals("19:06", largestTimeFromDigits(new int[] { 1, 9, 6, 0 }));
        Assert.assertEquals("06:20", largestTimeFromDigits(new int[] { 2, 0, 6, 6 }));
    }

    /**
     * <pre>
     *     方法一: 2个数字一组， 找到0-23间最大的数，剩下两个组合个0-59间的大数.
     *     &#64;todo: 未完成.
     * </pre>
     * 
     * @param a
     * @return
     */
    public String largestTimeFromDigits(int[] a) {
        int hour = -1, min = -1, hourIdx1 = -1, hourIdx2 = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int ta = a[i] * 10 + a[j];
                int tb = a[j] * 10 + a[i];
                if (ta < 24) {
                    if (ta > hour) {
                        hour = ta;
                        hourIdx1 = i;
                        hourIdx2 = j;
                    }
                }
                if (tb < 24) {
                    if (tb > hour) {
                        hour = tb;
                        hourIdx1 = i;
                        hourIdx2 = j;
                    }
                }
            }
        }
        if (hour == -1) {
            return "";
        }
        List<Integer> all = Arrays.stream(a).boxed().collect(Collectors.toList());
        // 这里不能使用removeAll(Collection), 因为有相同值的元素.
        // all.removeAll(Arrays.stream(new int[] { a[hourIdx1], a[hourIdx2] }).boxed().collect(Collectors.toList()));
        // 注意: arrayList.remove(index) 删除一个后，index已经发生了变化;
        all.remove(Math.max(hourIdx1, hourIdx2));
        all.remove(Math.min(hourIdx1, hourIdx2));
        int mina = all.get(0) * 10 + all.get(1);
        int minb = all.get(1) * 10 + all.get(0);
        if (mina < 60 && minb < 60) {
            min = Math.max(mina, minb);
        } else if (mina < 60 && minb >= 60) {
            min = mina;
        } else if (minb < 60 && mina >= 60) {
            min = minb;
        }
        if (min == -1) {
            return "";
        }
        return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
    }

    /**
     * <pre>
     *     方法二: 穷举.
     * </pre>
     * 
     * @param A
     * @return
     */
    public String largestTimeFromDigits2(int[] A) {
        int ans = -1;

        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j)
                if (j != i) for (int k = 0; k < 4; ++k)
                    if (k != i && k != j) {
                        // 下标总和为6.
                        int l = 6 - i - j - k;

                        // For each permutation of A[i], read out the time and
                        // record the largest legal time.
                        int hours = 10 * A[i] + A[j];
                        int mins = 10 * A[k] + A[l];
                        // 转化为分钟.
                        if (hours < 24 && mins < 60) ans = Math.max(ans, hours * 60 + mins);
                    }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }
}
