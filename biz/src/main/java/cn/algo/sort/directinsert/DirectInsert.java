package cn.algo.sort.directinsert;

import java.util.Arrays;

/**
 * Created by leslie on 2019/8/28.
 */
public class DirectInsert {
    /**
     * 移位法，在插入过程中将有序序列中比待插入数字大的数据向后移动，由于移动时会覆盖待插入数据，所以需要额外的临时变量保存待插入数据
     *
     * @param a
     */
    public static void sort1(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int temp = a[i]; // 先取出待插入数据保存，因为向后移位过程中会把覆盖掉待插入数
            while (j >= 0 && a[j] > temp) { // 如果待是比待插入数据大，就后移
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp; // 找到比待插入数据小的位置，将待插入数据插入
        }
    }

    /**
     * 交换法不 需求 额外的保存待插入数据，通过不停的向前交换带插入数据，类似冒泡法，直到找到比它小的值，也就是待插入数据找到了自己的位置。
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j] + arr[j + 1];      //只要大就交换操作
                arr[j] = arr[j + 1] - arr[j];
                arr[j + 1] = arr[j + 1] - arr[j];
                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }
}
