package cn.leetcode.problem101_200.problem111_120;

import java.util.*;

import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *    路径总和 II
 *    给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

 说明: 叶子节点是指没有子节点的节点。

 示例:
 给定如下二叉树，以及目标和 sum = 22，
       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1


 返回:
 [
    [5,4,11,2],
    [5,8,4,5]
 ]
 * </pre>
 * 
 * Created by leslie on 2020/5/24.
 */
public class Problem113 {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.left = node9;
        node6.right = node10;
        System.out.println(pathSum(root, 22));
    }

    /**
     * <pre>
     *     递归, 前序遍历.  记录当前的路径.
     * </pre>
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> curPath = new LinkedList<>();
        doPathSum(root, sum, curPath, ans);
        return ans;
    }

    private void doPathSum(TreeNode node, int sumExcludeCur, Deque<TreeNode> curPath, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        sumExcludeCur -= node.val;
        curPath.add(node);
        // 叶子节点.
        if (node.left == null && node.right == null) {
            if (sumExcludeCur == 0) {
                List<Integer> a = new ArrayList<>();
                Iterator<TreeNode> iter = curPath.iterator();
                while (iter.hasNext()) {
                    a.add(iter.next().val);
                }
                ans.add(a);
            }
            // pop出叶子节点.
            curPath.pollLast();
            return;
        }
        doPathSum(curPath.peekLast().left, sumExcludeCur, curPath, ans);
        doPathSum(curPath.peekLast().right, sumExcludeCur, curPath, ans);
        // 处理完右节点后, pop出.
        curPath.pollLast();
    }
}
