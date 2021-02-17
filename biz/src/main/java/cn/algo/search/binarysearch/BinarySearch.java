package cn.algo.search.binarysearch;

/**
 * Created by leslie on 2019/8/28.
 */
public class BinarySearch {

    /**
     * <pre>
     *    常规的二分查找.
     * </pre>
     * 
     * @param array
     * @param value
     * @return
     */
    public static int search1(int[] array, int value) {
        if (array == null) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        // <= 因为high取值 length - 1; 如果直接小于的话，最后一个high 就没有被搜索.
        while (low <= high) {
            int middle = (low + high) >> 1;
            if (value == array[middle]) {
                return middle;
            }
            if (value > array[middle]) {
                // mid + 1, 而不用mid, 因为mid已经被搜索过了.
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
                return mid; // value found
            }
        }
        return ~lo; // value not present
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

    /**
     * <pre>
     *    搜索左侧边界的下标. [1,2,2,2,2,3], 返回1，即第一个2的下标. 即使没有找到，也不会返回-1。 返回的left表示小于target的个数.
     *    左边界的常规写法是right = nums.length
     * </pre>
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int leftBoundSearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    /**
     * <pre>
     *     寻找左侧边界，搜索区间统一成 [Left, right]的方式.
     * </pre>
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int leftBoundSearch2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * <pre>
     *    搜索右侧边界.
     * </pre>
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int rightBoundSearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意
    }

    public static int rightBoundSearch2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}
