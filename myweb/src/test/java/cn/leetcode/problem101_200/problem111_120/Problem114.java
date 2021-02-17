package cn.leetcode.problem101_200.problem111_120;

import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     二叉树展开为链表
 *     给定一个二叉树，原地将它展开为一个单链表。

 例如，给定二叉树
        1
       / \
      2   5
     / \   \
    3   4   6
 将其展开为：

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 * </pre>
 * 
 * Created by leslie on 2020/11/3.
 */
public class Problem114 {

    @Test
    public void test1() {

    }

    /**
     * <pre>
     *     后序遍历.
     *     右子树接到左子树，左子树再作为当前节点的右子树.
     * </pre>
     * 
     * @param root
     */
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}
