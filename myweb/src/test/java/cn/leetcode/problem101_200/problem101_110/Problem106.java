package cn.leetcode.problem101_200.problem101_110;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     从中序与后序遍历序列构造二叉树
 *     根据一棵树的中序遍历与后序遍历构造二叉树。
 注意:
 你可以假设树中没有重复的元素。

 例如，给出
 中序遍历 inorder = [9,3,15,20,7]
 后序遍历 postorder = [9,15,7,20,3]
 返回如下的二叉树：
    3
   / \
  9  20
    /  \
   15   7
 * </pre>
 * 
 * Created by leslie on 2020/11/3.
 */
public class Problem106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart,
                           int postorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        int rootValue = postorder[postorderEnd];
        int index = 0;
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == rootValue) {
                index = i;
                break;
            }
        }
        int leftSize = index - inorderStart;
        TreeNode root = new TreeNode(rootValue);
        root.left = build(inorder, inorderStart, index - 1, postorder, postorderStart, postorderStart + leftSize - 1);
        root.right = build(inorder, index + 1, inorderEnd, postorder, postorderStart + leftSize, postorderEnd - 1);
        return root;
    }
}
