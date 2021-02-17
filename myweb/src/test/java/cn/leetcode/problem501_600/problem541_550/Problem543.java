package cn.leetcode.problem501_600.problem541_550;

import cn.algo.tree.TreeNode;

/**
 * <pre>
 *     二叉树的直径
 *     给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

 示例 :
 给定二叉树

       1
      / \
     2   3
    / \
   4   5
 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * </pre>
 * 
 * Created by leslie on 2021/2/12.
 */
public class Problem543 {

    int ans = 1;

    /**
     * <pre>
     *     找到某个节点的左、右子树深度和的最大值.
     * </pre>
     * 
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans - 1;
    }

    /**
     * <pre>
     *     深度优先.
     *     递归函数定义: 返回 node 节点的最大深度.
     * </pre>
     * @param node
     * @return
     */
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.leftNode);
        int rightDepth = depth(node.rightNode);
        ans = Math.max(ans, leftDepth + rightDepth + 1);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
