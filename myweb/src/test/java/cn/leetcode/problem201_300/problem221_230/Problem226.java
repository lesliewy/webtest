package cn.leetcode.problem201_300.problem221_230;

import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     翻转二叉树
 *     翻转一棵二叉树。

 示例：
 输入：
      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 输出：

      4
    /   \
   7     2
  / \   / \
 9   6 3   1
 备注:
 这个问题是受到 Max Howell 的 原问题 启发的 ：

 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * </pre>
 * 
 * Created by leslie on 2020/10/30.
 */
public class Problem226 {

    @Test
    public void test1() {
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        node4.left = node2;
        node4.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;
        TreeNode result = invertTree3(node4);
        System.out.println(result);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }

    /**
     * <pre>
     * 前序遍历, 后序遍历都可以.  因为前序是先交换左右节点，再处理左右节点;  后序是先处理左右节点再交换.
     * 中序遍历不行, 左节点处理完后，右节点还没有处理，就交换了.
     * </pre>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree2(root.left);
        invertTree2(root.right);
        return root;
    }

    /**
     * <pre>
     * 后序遍历
     * </pre>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree3(root.left);
        invertTree3(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }
}
