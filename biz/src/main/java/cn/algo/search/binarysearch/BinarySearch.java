package cn.algo.search.binarysearch;

/**
 * Created by leslie on 2019/8/28.
 */
public class BinarySearch {

    public static int search1(int[] array, int value) {
        if (array == null) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) >> 1;
            if (value == array[middle]) {
                return middle;
            }
            if (value > array[middle]) {
                low = middle + 1;
            }
            if (value < array[middle]) {
                high = middle - 1;
            }
        }
        return -1;
    }


    /**
     * <pre>
     *    search1()中当数据比较大并且要查找的值在后面的时候，求middle可能会出现溢出，改为如下。
     * </pre>
     *
     * @param array
     * @param value
     * @return
     */
    public static int search2(int[] array, int value) {
        if (array == null) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (value == array[middle]) {
                return middle;
            }
            if (value > array[middle]) {
                low = middle + 1;
            }
            if (value < array[middle]) {
                high = middle - 1;
            }
        }
        return -1;
    }

    /**
     * <pre>
     *    返回的不是-1而是~lo，这里的~lo肯定是个负数，也就是说如果返回的是一个负数就表示没有找到，
     *    如果返回的是一个非负数就表示查找到了。
     *    如果是负数我们可以对他取反，他表示的就是要查找的数如果放到数组中应该存放到的位置。
     *
     *    比如数组[2,5,6,8,9]，如果我们查找的是6就会返回6的下标2，如果查找的是4，就返回-2，为啥是-2，因为-2取反就是1，
     *    也就是如果把4存放到数组中应该存放到数组下标为1的位置
     * </pre>
     *
     * @param array
     * @param size
     * @param value
     * @return
     */
    public static int search3(int[] array, int size, int value) {
        if (array == null) {
            return -1;
        }
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }

    /**
     * 递归
     *
     * @param array
     * @param value
     * @return
     */
    public static int search4(int[] array, int value) {
        if (array == null) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        return searchmy(array, low, high, value);
    }

    private static int searchmy(int array[], int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (value == array[mid]) {
            return mid;
        }
        if (value < array[mid]) {
            return searchmy(array, low, mid - 1, value);
        }
        return searchmy(array, mid + 1, high, value);
    }
}