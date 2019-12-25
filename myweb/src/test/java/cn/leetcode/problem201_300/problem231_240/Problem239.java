package cn.leetcode.problem201_300.problem231_240;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 滑动窗口最大值.
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3 输出: [3,3,5,5,6,7]
 * </pre>
 * 
 * Created by leslie on 2019/11/27.
 */
public class Problem239 {

    Deque<Integer> deque = new ArrayDeque();

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[] { 3, 3, 5, 5, 6, 7 },
                                 maxSlidingWindow3(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3));
        Assert.assertArrayEquals(new int[] { 5, 3 }, maxSlidingWindow3(new int[] { 5, 3, 1, 2 }, 3));
        Assert.assertArrayEquals(new int[] { 3, 3, 2, 5 }, maxSlidingWindow3(new int[] { 1, 3, 1, 2, 0, 5 }, 3));
        Assert.assertArrayEquals(new int[] { 3 }, maxSlidingWindow3(new int[] { 3 }, 1));
        Assert.assertArrayEquals(new int[] {}, maxSlidingWindow3(new int[] {}, 0));
    }

    /**
     * <pre>
     *    方法一: 常规方法, 遍历, 窗口内找出最大值.
     *    时间复杂度: O(n*k)
     * </pre>
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] result = new int[len - k + 1];
        int i = 0, j = k - 1;
        while (j < len) {
            int[] slideArr = subArray(nums, i, j);
            int max = Arrays.stream(slideArr).max().getAsInt();
            result[i] = max;
            i++;
            j++;
        }
        return result;
    }

    private int[] subArray(int[] arr, int begin, int end) {
        int[] result = new int[end - begin + 1];
        int index = 0;
        for (int i = begin; i <= end; i++) {
            result[index++] = arr[i];
        }
        return result;
    }

    /**
     * <pre>
     *    方法二: 双端队列.
     *    队列中维护窗口内最大值及其后值的下标. 队头是最大值. 尾部添加值时，判断与队尾的大小关系, 小于新加值的都可以直接删除.
     *    时间复杂度: O(N) 队列内的循环和n没有关系，是每个元素被处理了2次.
     *    空间复杂度: O(N) 双向队列使用了k, 输出使用了O(N-k+1)
     * </pre>
     *
     * @todo 使用自实现的双端队列，提高性能.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null) {
            return new int[0];
        }
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }

        deque.clear();
        int max = nums[0];
        // 先处理第一个窗口。
        for (int i = 0; i < k; i++) {
            clearDeque(nums, i, k);
            deque.addLast(i);
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int[] result = new int[len - k + 1];
        result[0] = max;

        for (int i = k; i < len; i++) {
            clearDeque(nums, i, k);
            deque.addLast(i);
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    private void clearDeque(int[] nums, int i, int k) {
        // 最大值滑出窗口.
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }

        // 新进值大于前面进入的，那就可以把之前的值都删掉了。
        while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
            deque.removeLast();
        }
    }

    /**
     * 构造大小为k的大顶堆. 此方法不行.
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        if (k == len) {
            int max = Arrays.stream(nums).max().getAsInt();
            return new int[] { max };
        }
        int[] result = new int[len - k + 1];
        // 初始化大顶堆.
        int[] initArr = subArray(nums, 0, k - 1);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.stream(initArr).boxed().collect(Collectors.toList()));
        int i = 0, j = k;
        while (j < len) {
            result[i++] = maxHeap.poll();
            maxHeap.add(nums[j]);
            j++;
        }
        return result;
    }

}
