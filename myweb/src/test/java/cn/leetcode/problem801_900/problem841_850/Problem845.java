package cn.leetcode.problem801_900.problem841_850;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     数组中的最长山脉
 *     我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *     B.length >= 3
 *     存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]

 （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 给出一个整数数组 A，返回最长 “山脉” 的长度。
 如果不含有 “山脉” 则返回 0。

 输入：[2,1,4,7,3,2,5]
 输出：5
 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。

 输入：[2,2,2]
 输出：0
 解释：不含 “山脉”。
 * </pre>
 * 
 * Created by leslie on 2019/12/26.
 */
public class Problem845 {

    @Test
    public void test1() {
        Assert.assertEquals(5, longestMountain2(new int[] { 2, 1, 4, 7, 3, 2, 5 }));
        Assert.assertEquals(0, longestMountain2(new int[] { 2, 2, 2 }));
        Assert.assertEquals(4, longestMountain2(new int[] { 2, 1, 4, 4, 5, 3, 2, 6 }));
        Assert.assertEquals(3, longestMountain2(new int[] { 2, 1, 4, 4, 5, 3, 3, 6 }));
        Assert.assertEquals(0, longestMountain2(new int[] { 1, 4, 5 }));
        Assert.assertEquals(3, longestMountain2(new int[] { 0, 1, 0 }));
    }

    /**
     * <pre>
     *     方法一: 峰谷法.   谷 - 峰 - 谷  找到符合这种形式的.
     * </pre>
     * 
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int len = A.length;
        if (len < 3) {
            return 0;
        }
        int i = 1, j = 1;
        int ans = 0;
        boolean curFinish;

        while (true) {
            int curAns = 0;
            // 找到谷
            while (i < len) {
                if (A[i] > A[i - 1]) {
                    break;
                }
                i++;
            }
            j = i - 1;
            curFinish = i >= len - 1;
            if (!curFinish) {
                // 谷后的峰
                while (j++ < len - 1) {
                    if (A[j] < A[j - 1]) {
                        break;
                    } else if (A[j] > A[j - 1]) {
                        curAns++;
                    } else {
                        // 谷后的峰出现等于时，本次失败，重来.
                        curFinish = true;
                        curAns = 0;
                        i = j;
                        break;
                    }
                }
                // 谷后的峰直接到达末尾了.
                if (j > len - 1) {
                    i = j;
                    curAns = 0;
                    curFinish = true;
                }
            }

            if (!curFinish) {
                i = j - 1;
                // 峰后的谷
                while (i++ < len - 1) {
                    // 峰后的谷，出现等于时, 不需要retry.
                    if (A[i] >= A[i - 1]) {
                        break;
                    } else if (A[i] < A[i - 1]) {
                        curAns++;
                    }
                }
            }

            curAns = curAns > 0 ? curAns + 1 : 0;
            ans = Math.max(ans, curAns);
            if (i >= len - 1) {
                break;
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     方法一的优化.
     * </pre>
     * 
     * @param A
     * @return
     */
    public int longestMountain2(int[] A) {
        int len = A.length;
        if (len < 3) {
            return 0;
        }
        // i 从 0 开始，改用当前值 和 后一值比较, 这样的话, i 就是要找的是最小值. 选择标准看要取哪个值.
        // 去掉curFinish，使用i直接控制.
        int i = 0;
        int ans = 0;
        while (i < len - 1) {
            int curAns = 0;
            // 找到谷
            // while 条件和 if 条件合并.
            while (i < len - 1 && A[i] >= A[i + 1]) {
                i++;
            }
            // 谷后的峰
            while (i < len - 1 && A[i] < A[i + 1]) {
                curAns++;
                i++;
            }
            // 谷后的峰直接到达末尾了, 或者出现等于时, 本次结束; "||" 的短路原理.
            if (i >= len - 1 || A[i] == A[i + 1]) {
                curAns = 0;
            }

            // 峰后的谷，出现等于时, 不需要retry.
            while (i < len - 1 && A[i] > A[i + 1]) {
                curAns++;
                i++;
            }

            curAns = curAns > 0 ? curAns + 1 : 0;
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

}
