package cn.leetcode.problem1_100.problem41_50;

import java.util.*;

/**
 * <pre>
 *     全排列
 *     给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 *     输入: [1,2,3]
 输出:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 * </pre>
 * 
 * Created by leslie on 2019/12/14.
 */
public class Problem46 {

    // @Test
    // public void test1() {
    // permute(new int[] { 1, 2, 3 });
    // }

    /**
     * <pre>
     *     方法一: 回溯.
     * </pre>
     * 
     * @param n
     * @param nums
     * @param output
     * @param first
     */
    public void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
        // if all integers are used up
        if (first == n) output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation 将第i个整数放入排列.
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations 从第i个整数开始的所有排列
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }

    /**
     * <pre>
     *     方法二: 回溯。   回溯搜索 = 深度优先 + 状态重置 + 剪枝
     *     解决回溯问题，我的经验是 一定不要偷懒，拿起纸和笔，把这个问题的递归结构画出来，一般而言，是一个树形结构，这样思路和代码就会比较清晰了。而写代码即是将画出的图用代码表现出来。
     *     状态可以用: 数组、哈希表、位图来表示.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        if (len == 0) {
            return res;
        }
        generatePermution(nums, used, 0, len, new Stack<>(), res);
        return res;
    }

    /**
     * curSize 表示当前的路径 path 里面有多少个元素
     * 
     * @param nums
     * @param visited
     * @param curSize
     * @param len
     * @param path
     * @param res
     */
    private void generatePermution(int[] nums, boolean[] visited, int curSize, int len, Stack<Integer> path,
                                   List<List<Integer>> res) {
        if (curSize == len) {
            // 此时 path 已经保存了 nums 中的所有数字，已经成为了一个排列
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                path.push(nums[i]);
                visited[i] = true;
                generatePermution(nums, visited, curSize + 1, len, path, res);
                // 刚开始接触回溯算法的时候常常会忽略状态重置
                // 回溯的时候，一定要记得状态重置
                path.pop();
                visited[i] = false;
            }
        }
    }

    /**
     * <pre>
     *     方法二: 状态: 使用了哈希表.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute3(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 使用哈希表检测一个数字是否使用过
        Set<Integer> used = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        backtrack(nums, 0, len, used, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int depth, int len, Set<Integer> used, Stack<Integer> stack,
                           List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used.contains(i)) {
                used.add(i);
                stack.push(nums[i]);

                backtrack(nums, depth + 1, len, used, stack, res);

                stack.pop();
                used.remove(i);
            }
        }
    }

    /**
     * <pre>
     *     方法二: 状态: 使用位图。
     *     因为一个整数的二进制表示，在每个数位上非 0 即 1，这就可以表示一个布尔型数组。
     它们的区别仅在于
     1、二进制右边是低位，数组左边是索引为 0 的位置；
     2、一个整数的二进制有 32 位，不过回溯搜索的问题复杂度基本上都很高，本题是 O(n!) ，n = 32 的时候已经非常大了，一般来说测试用例都达不到这个级别。
     因此，完全可以用一个整数表示一个布尔型数组。
     我们对布尔型数组的操作不外乎就两个：1、把某个索引位置从 true 变为 false；2、把某个索引位置从 fasle 变为 true。
     *******异或操作，是不进位的加法，***********一个数位异或上 1 以后，它的功效就是使得 1 变 0 ，0 变 1。因此就可以通过对整数进行异或运算达到操作布尔型数组的效果。
     具体说来：
     把数组索引为 i 的位置从 0 变成 1（或者由 1 变成 0）就等价于把一个整数异或上 1 << i。只不过布尔数组中是从左向右第 i 位的的值变化了，在整型变量中，是从右向左第 i 位的值变化了（这里 i 从 0 开始）。
     不知道我说清楚没有，欢迎讨论。
     * </pre>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute4(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 使用位图，适用于数组 nums 的长度不超过 32 位的情况
        int used = 0;
        Stack<Integer> stack = new Stack<>();

        backtrack(nums, 0, len, used, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int depth, int len, int used, Stack<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i++) {
            // used >> i 后, used本身并未变化.
            if (((used >> i) & 1) == 0) {
                // 使得第i位(位图是从右向左)的0变成1.
                used ^= (1 << i);
                stack.push(nums[i]);

                backtrack(nums, depth + 1, len, used, stack, res);

                stack.pop();
                // 同样的操作，使得1变成0
                used ^= (1 << i);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4 };
        Problem46 solution = new Problem46();
        List<List<Integer>> permute = solution.permute2(nums);
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }
}
