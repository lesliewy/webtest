package cn.leetcode.problem1_100.problem91_100;

import org.junit.Test;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *     相同的树
 *     给定两个二叉树，编写一个函数来检验它们是否相同。
       如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

 示例 1:
 输入:       1         1
           / \       / \
          2   3     2   3

 [1,2,3],   [1,2,3]
 输出: true

 示例 2:
 输入:      1          1
           /           \
          2             2

 [1,2],     [1,null,2]
 输出: false

 示例 3:
 输入:       1         1
           / \       / \
          2   1     1   2
 [1,2,1],   [1,1,2]
 输出: false
 * </pre>
 * 
 * Created by leslie on 2020/10/27.
 */
public class Problem100 {

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        s1.left = s2;
        s1.right = s3;
        System.out.println(isSameTree(t1, s1));
    }

    @Test
    public void test2() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t1.left = t2;

        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        s1.right = s2;
        System.out.println(isSameTree(t1, s1));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
