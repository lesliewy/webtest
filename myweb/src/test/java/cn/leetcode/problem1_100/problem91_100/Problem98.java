package cn.leetcode.problem1_100.problem91_100;

import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     验证二叉搜索树
 *     给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 假设一个二叉搜索树具有如下特征：
 节点的左子树只包含小于当前节点的数。
 节点的右子树只包含大于当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。

 示例 1:
 输入:
   2
  / \
 1   3
 输出: true

 示例 2:
 输入:
     5
    / \
   1   4
      / \
     3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
      根节点的值为 5 ，但是其右子节点值为 4 。
 * </pre>
 * 
 * Created by leslie on 2020/10/28.
 */
public class Problem98 {

    @Test
    public void test1() {

    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}