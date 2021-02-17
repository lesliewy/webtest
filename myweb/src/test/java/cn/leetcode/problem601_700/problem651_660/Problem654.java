package cn.leetcode.problem601_700.problem651_660;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *    最大二叉树
 *    给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

 二叉树的根是数组中的最大元素。
 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 通过给定的数组构建最大二叉树，并且输出这个树的根节点。

 示例 ：
 输入：[3,2,1,6,0,5]
 输出：返回下面这棵树的根节点：
     6
   /   \
  3     5
  \    /
  2  0
   \
    1
  

 提示：
 给定的数组的大小在 [1, 1000] 之间。
 * </pre>
 * 
 * Created by leslie on 2020/11/3.
 */
public class Problem654 {

    /**
     * <pre>
     *   前序遍历.
     *   当前节点: 数组中找到最大值及下标, 构造节点.  左节点递归调用，右节点递归调用.
     *   用到了辅助函数来构造带参数的递归.
     * </pre>
     * @param nums
     * @return
     */
    TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /* 将 nums[lo..hi] 构造成符合条件的树，返回根节点 */
    TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }

}
