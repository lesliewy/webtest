package cn.leetcode.problem601_700.problem671_680;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     二叉树中第二小的节点
 *     给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
       给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

 输入:
   2
  / \
 2   5
    / \
   5   7
 输出: 5
 说明: 最小的值是 2 ，第二小的值是 5 。

 输入:
    2
   / \
  2   2
 输出: -1
 说明: 最小的值是 2, 但是不存在第二小的值。
 * </pre>
 * 
 * Created by leslie on 2020/5/10.
 */
public class Problem671 {

    @Test
    public void test1() {
        TreeNode tree1Root = new TreeNode(2);
        TreeNode tree1Node2 = new TreeNode(2);
        TreeNode tree1Node3 = new TreeNode(5);
        TreeNode tree1Node4 = new TreeNode(5);
        TreeNode tree1Node5 = new TreeNode(7);
        tree1Root.left = tree1Node2;
        tree1Root.right = tree1Node3;
        tree1Node3.left = tree1Node4;
        tree1Node3.right = tree1Node5;
        Assert.assertEquals(5, findSecondMinimumValue(tree1Root));

        TreeNode tree2Root = new TreeNode(2);
        TreeNode tree2Node2 = new TreeNode(2);
        TreeNode tree2Node3 = new TreeNode(2);
        tree2Root.left = tree2Node2;
        tree2Root.right = tree2Node3;
        Assert.assertEquals(-1, findSecondMinimumValue(tree2Root));
    }

    /**
     * <pre>
     *     遍历树获得所有节点，从节点中找出第二小的.
     * </pre>
     * 
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> vals = new HashSet<>();
        midOrder(root, vals);
        if (vals.size() <= 1) {
            return -1;
        }

        return vals.size() <= 1 ? -1 : vals.stream().sorted((a,
                                                             b) -> a.compareTo(b)).collect(Collectors.toList()).get(1);
    }

    private void midOrder(TreeNode node, Set<Integer> vals) {
        if (node != null) {
            midOrder(node.left, vals);
            vals.add(node.val);
            midOrder(node.right, vals);
        }
    }
}
