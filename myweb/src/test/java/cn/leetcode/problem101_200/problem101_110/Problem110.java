package cn.leetcode.problem101_200.problem101_110;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.TreeNode;
import cn.leetcode.util.TreeUtils;

/**
 * <pre>
 *     平衡二叉树
 *     给定一个二叉树，判断它是否是高度平衡的二叉树。

 本题中，一棵高度平衡二叉树定义为：

 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。

 示例 1：
 输入：root = [3,9,20,null,null,15,7]
 输出：true

 示例 2：
 输入：root = [1,2,2,3,3,null,null,4,4]
 输出：false

 示例 3：
 输入：root = []
 输出：true

 提示：
 树中的节点数在范围 [0, 5000] 内
 -104 <= Node.val <= 104
 * </pre>
 * 
 * Created by leslie on 2021/2/13.
 */
public class Problem110 {

    @Test
    public void testxxx() {
        Assert.assertTrue(isBalanced2(TreeUtils.buildTreeByLevelOrder("3,9,20,-1,-1,15,7")));
        Assert.assertFalse(isBalanced2(TreeUtils.buildTreeByLevelOrder("1,2,2,3,3,-1,-1,4,4")));
        /**
         * <pre>
         *              1
         *           2    2
         *         3 -1 -1  3
         *        4 -1    4
         * </pre>
         */
        Assert.assertFalse(isBalanced2(TreeUtils.buildTreeByLevelOrder("1,2,2,3,-1,-1,3,4,-1,-1,4")));
        Assert.assertTrue(isBalanced2(null));
    }

    /**
     * <pre>
     *     自顶向下递归.
     *     时间复杂度：O(n^2)，其中 n 是二叉树中的节点个数。
     最坏情况下，二叉树是满二叉树，需要遍历二叉树中的所有节点，时间复杂度是 O(n).
     对于节点 p，如果它的高度是 d，则 height3(p) 最多会被调用 d 次（即遍历到它的每一个祖先节点时）。对于平均的情况，一棵树的高度 h 满足 O(h)=O(logn)，因为d≤h，所以总时间复杂度为 O(nlogn)。
     对于最坏的情况，二叉树形成链式结构，高度为 O(n)，此时总时间复杂度为 O(n^2)。
     空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n
     * </pre>
     * 
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int depthLeft = depth(root.left);
        int depthRight = depth(root.right);
        // 每个节点都要保证是平衡二叉树.
        return Math.abs(depthLeft - depthRight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * <pre>
     *     求最大深度: 返回以node为根的二叉树的高度.
     * </pre>
     * 
     * @param node
     * @return
     */
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    /**
     * <pre>
     *     自顶向下递归: 带备忘录.
     * </pre>
     * 
     * @param root
     * @return
     */
    Map<TreeNode, Integer> memo = new HashMap<>();

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        int depthLeft = depth2(root.left, memo);
        int depthRight = depth2(root.right, memo);
        // 每个节点都要保证是平衡二叉树.
        return Math.abs(depthLeft - depthRight) <= 1 && isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int depth2(TreeNode node, Map<TreeNode, Integer> memo) {
        if (node == null) {
            return 0;
        }
        Integer ans = memo.get(node);
        if (ans != null) {
            return ans;
        }
        ans = 1 + Math.max(depth2(node.left, memo), depth2(node.right, memo));
        memo.put(node, ans);
        return ans;
    }

    /**
     * <pre>
     *     自底向上递归.
     *    时间复杂度：O(n)，其中 n 是二叉树中的节点个数。
     *    使用自底向上的递归，每个节点的计算高度和判断是否平衡都只需要处理一次，最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。
          空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n

            自顶向下的递归是先算出高度, 再来判断; 每个节点都是如此。
            自底向上的递归 计算高度和判断平衡放在一起。只要有一个节点判为不平衡，就直接返回，不需要再计算了。
     * </pre>
     * 
     * @param root
     * @return
     */
    public boolean isBalanced3(TreeNode root) {
        return height3(root) >= 0;
    }

    public int height3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height3(root.left);
        int rightHeight = height3(root.right);
        // 先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
