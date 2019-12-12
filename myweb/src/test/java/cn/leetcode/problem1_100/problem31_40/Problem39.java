package cn.leetcode.problem1_100.problem31_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/**
 * <pre>
 *   组合总和.
 *   给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 candidates 中的数字可以无限制重复被选取。
 *   说明：
        所有数字（包括 target）都是正整数。
        解集不能包含重复的组合。 

      输入: candidates = [2,3,6,7], target = 7,
      所求解集为:
      [
        [7],
        [2,2,3]
      ]

      输入: candidates = [2,3,5], target = 8,
      所求解集为:
      [
        [2,2,2,2],
        [2,3,3],
        [3,5]
      ]
 * </pre>
 *
 * Created by leslie on 2019/12/1.
 */
public class Problem39 {

    private List<List<Integer>> res = new ArrayList<>();
    private int[]               candidates;
    private int                 len;

    @Test
    public void test1() {
        // Assert.assertThat(combinationSum(new int[] { 2, 3, 6, 7 }, 7),
        // CoreMatchers.is(new int[][] { { 7 }, { 2, 2, 3 } }));
    }

    /**
     * <pre>
     *    方法一: 回溯法, 剪枝.
     *    做搜索、回溯问题的套路是画图，代码其实就是根据画出的树形图写出来的。
     *    https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * </pre>
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum(residue - candidates[i], i, pre);
            pre.pop();

        }
    }
}
