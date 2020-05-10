package cn.algo.thinking.divideconquer;

import java.util.Arrays;

/**
 * <pre>
 *     逆序对个数.
 *     输入: [2,4,3,1,5,6] 输出: 4   逆序对为: (2,1), (4, 3), (4, 1), (3, 1).
 * </pre>
 * Created by leslie on 2019/12/2.
 */
public class BackwardPair {

    /**
     * <pre>
     *     方法一: 暴力破解.
     *     时间复杂度: O(n^2)
     * </pre>
     * @param backward
     * @return
     */
    public static int backwardPair(int[] backward) {
        int len = backward.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (backward[i] > backward[j]) {
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * <pre>
     *     方法二: 分治法.
     *
     * </pre>
     * @param backward
     * @return
     */
    static int num = 0;

    public static int backwardPair2(int[] backward) {
        num = 0;
        int len = backward.length;
        mergeSortCounting(backward, 0, len - 1);
        return num;
    }

    private static void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (q - i + 1); // 统计p-q之间，比a[j]大的元素个数
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) { // 处理剩下的
            tmp[k++] = a[i++];
        }
        while (j <= r) { // 处理剩下的
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) { // 从tmp拷贝回a
            a[p + i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] backwardPair1 = new int[]{2, 4, 3, 1, 5, 6}; // 4
        Arrays.stream(backwardPair1).forEach(s -> System.out.print(s + ", "));
        System.out.println();
        System.out.println("backwardPair: " + backwardPair(backwardPair1));
        backwardPair2(backwardPair1);
        System.out.println("backwardPair2: " + num);

        int[] backwardPair2 = new int[]{2, 4, 5, 6}; // 0
        Arrays.stream(backwardPair2).forEach(s -> System.out.print(s + ", "));
        System.out.println();
        System.out.println(backwardPair(backwardPair2));
        backwardPair2(backwardPair2);
        System.out.println("backwardPair2: " + num);



    }
}
