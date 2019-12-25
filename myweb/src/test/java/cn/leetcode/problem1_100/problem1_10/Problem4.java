package cn.leetcode.problem1_100.problem1_10;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     寻找两个有序数组的中位数
 *     给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 你可以假设 nums1 和 nums2 不会同时为空。

 nums1 = [1, 3]
 nums2 = [2]
 则中位数是 2.0

 nums1 = [1, 2]
 nums2 = [3, 4]
 则中位数是 (2 + 3)/2 = 2.5
 * </pre>
 * 
 * Created by leslie on 2019/12/19.
 */
public class Problem4 {

    @Test
    public void test1() {
        Assert.assertEquals(2.0, findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }), 0.5);
        Assert.assertEquals(2.5, findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }), 0.2);
    }

    /**
     * <pre>
     *     方法一: 归并排序，先合并, 再找中位数.
     *     时间复杂度: O(M + N)
     *     空间复杂度: O(M + N)
     * </pre>
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0) {
            return getMedianInArr(nums2);
        }
        if (len2 == 0) {
            return getMedianInArr(nums1);
        }

        int i = 0, j = 0, k = 0;
        int[] newArr = new int[len1 + len2];
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                newArr[k++] = nums1[i];
                i++;
            } else {
                newArr[k++] = nums2[j];
                j++;
            }
        }
        while (i < len1) {
            newArr[k++] = nums1[i++];
        }
        while (j < len2) {
            newArr[k++] = nums2[j++];
        }

        return getMedianInArr(newArr);
    }

    private double getMedianInArr(int[] a) {
        int len = a.length;
        if (len % 2 == 0) {
            return (a[len / 2] + a[len / 2 - 1]) / 2.0;
        } else {
            return a[len / 2];
        }
    }

    /**
     * <pre>
     *     方法一: 优化。
     *     不需要真的合并一个新数组， 根据奇数、偶数算出序号。
     *     空间复杂度可以降到 O(1).
     * </pre>
     */

    /**
     * <pre>
     *     方法二: 二分.
     *     第k小, 每个数组找k/2位，较小的排除掉, 以此类推.
     * </pre>
     */
}
