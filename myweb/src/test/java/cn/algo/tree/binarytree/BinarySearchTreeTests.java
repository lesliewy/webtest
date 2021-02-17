package cn.algo.tree.binarytree;

import org.junit.Assert;
import org.junit.Test;

import cn.algo.tree.TreeNode;

/**
 * Created by leslie on 2020/10/29.
 */
public class BinarySearchTreeTests {

    @Test
    public void testIsInBST() {
        TreeNode<Integer> root = new TreeNode<>(4);
        BinaryTree.insert(root, 2);
        BinaryTree.insert(root, 7);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 3);
        BinaryTree.insert(root, 5);
        Assert.assertFalse(BinarySearchTree.isInBST(root, 8));
        Assert.assertTrue(BinarySearchTree.isInBST(root, 2));
    }

    @Test
    public void testIsInBST2() {
        TreeNode<Integer> root = new TreeNode<>(4);
        BinaryTree.insert(root, 2);
        BinaryTree.insert(root, 7);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 3);
        BinaryTree.insert(root, 5);
        Assert.assertFalse(BinarySearchTree.isInBST2(root, 8));
        Assert.assertTrue(BinarySearchTree.isInBST2(root, 2));
    }

    @Test
    public void testIsValidBST() {
        TreeNode<Integer> root = new TreeNode<>(4);
        BinaryTree.insert(root, 2);
        BinaryTree.insert(root, 7);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 3);
        BinaryTree.insert(root, 5);
        Assert.assertTrue(BinarySearchTree.isValidBST(root));

        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node7 = new TreeNode<>(7);
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        node4.leftNode = node2;
        node4.rightNode = node7;
        node2.leftNode = node1;
        node2.rightNode = node5;
        Assert.assertFalse(BinarySearchTree.isValidBST(node4));
    }

    @Test
    public void testInsertIntoBST() {
        TreeNode<Integer> root = new TreeNode<>(4);
        BinaryTree.insert(root, 2);
        BinaryTree.insert(root, 7);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 3);
        BinaryTree.insert(root, 5);
        BinarySearchTree.insertIntoBST(root, 6);
    }

    @Test
    public void testDeleteNode() {
        TreeNode<Integer> root = new TreeNode<>(4);
        BinaryTree.insert(root, 2);
        BinaryTree.insert(root, 7);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 3);
        BinaryTree.insert(root, 5);
        BinarySearchTree.deleteNode(root, 5);
    }
}
