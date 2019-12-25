package cn.leetcode.problem201_300.problem211_220;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     数组中的第K个最大元素
 *     在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *     输入: [3,2,1,5,6,4] 和 k = 2
 输出: 5

 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 输出: 4

 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度
 * </pre>
 * 
 * Created by leslie on 2019/12/14.
 */
public class Problem215 {

    @Test
    public void test1() {
        Assert.assertEquals(5, findKthLargest2(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
        Assert.assertEquals(4, findKthLargest2(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));
    }

    /**
     * <pre>
     *     方法零: 对数组排序。
     *          时间复杂度: O(NlogN).
     *
     *     方法一: 大顶堆.  维持个数为k的大顶堆.
     *          时间复杂度: O(NlogK).
     *          空间复杂度: O(k)
     * </pre>
     * 
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.reverseOrder());
        priorityQueue.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int ans = 0;
        while (k-- > 0) {
            ans = priorityQueue.poll();
        }
        return ans;
    }

    /**
     * <pre>
     *     方法二: 快速选择.
     *     第k大，转换为第n-k小.
     *     随机找出一个元素，使用划分算法，放到正确位置，根据此位置递归调用, 与快排不同的是只需要递归调用正确的一边.
     *     时间复杂度: 平均O(N)
     * </pre>
     * 
     * @param nums
     * @param k
     * @return
     */
    int[] nums;

    public int findKthLargest2(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }

    /**
     * <pre>
     * 划分算法
     * 将指定的某个元素放到正确的位置.
     * </pre>
     *
     * @param left
     * @param right
     * @param pivot_index
     * @return
     */
    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public int quickselect(int left, int right, int k_smallest) {
        /*
         * Returns the k-th smallest element of list within left..right.
         */

        if (left == right) // If the list contains only one element,
            return this.nums[left]; // return that element

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index) {
            return this.nums[k_smallest];
        }
        // go left side
        else if (k_smallest < pivot_index) {
            return quickselect(left, pivot_index - 1, k_smallest);
        }
        // go right side
        return quickselect(pivot_index + 1, right, k_smallest);
    }

}
