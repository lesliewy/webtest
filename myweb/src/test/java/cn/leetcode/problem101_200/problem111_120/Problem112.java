package cn.leetcode.problem101_200.problem111_120;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * Created by leslie on 2019/11/28.
 */
public class Problem112 {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(5);
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(8);
        TreeNode t3 = new TreeNode(11);
        TreeNode t4 = new TreeNode(13);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(7);
        TreeNode t7 = new TreeNode(2);
        TreeNode t8 = new TreeNode(1);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t5.right = t8;
        Assert.assertTrue(hasPathSum2(root, 22));

        TreeNode root2 = new TreeNode(-2);
        TreeNode a1 = new TreeNode(-3);
        root2.right = a1;
        Assert.assertFalse(hasPathSum2(root2, -2));

    }

    /**
     * 方法一: 递归。
     * <p>
     * 时间复杂度： O(N) 每个节点访问一次.
     * </p>
     * <p>
     * 空间复杂度: 最坏访问N个节点，调用栈开销为O(N); 最好平衡树情况下O(logN).
     * </p>
     * 
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * 方法二: 递归的迭代版本. 使用栈(LinkedList) 来实现. 维护两个栈: 一个node栈, 一个剩余分数栈; 栈顶表示当前节点还需要多少分数.
     * <p>
     * 因为将LinedList 当做栈使用，只用了其add() pooLast(), 所以也可以使用纯粹的基于数组的Stack类.
     * </p>
     * 
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        /*
         * LinkedList<TreeNode> nodeStack = new LinkedList<>(); LinkedList<Integer> sumStack = new LinkedList<>();
         * nodeStack.add(root); sumStack.add(sum); while (!nodeStack.isEmpty()) { TreeNode node = nodeStack.pollLast();
         * int currSum = sumStack.pollLast() - node.val; if (node.left == null && node.right == null && currSum == 0) {
         * return true; } if (node.left != null) { nodeStack.add(node.left); sumStack.add(currSum); } if (node.right !=
         * null) { nodeStack.add(node.right); sumStack.add(currSum); } }
         */
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.add(root);
        sumStack.add(sum);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currSum = sumStack.pop() - node.val;

            if (node.left == null && node.right == null && currSum == 0) {
                return true;
            }
            if (node.left != null) {
                nodeStack.add(node.left);
                sumStack.add(currSum);
            }
            if (node.right != null) {
                nodeStack.add(node.right);
                sumStack.add(currSum);
            }
        }

        return false;
    }
}
