package cn.algo.tree.binarytree;

import org.junit.Assert;
import org.junit.Test;

import cn.algo.tree.TreeNode;


/**
 * Created by leslie on 2019/8/29.
 */
public class BinaryTreeTest {

    @Test
    public void testInsert() {
        TreeNode root = buildTree1();
        Integer[] mid = BinaryTree.midOrder1(root);
        Assert.assertArrayEquals(new Integer[]{1, 3, 5, 8, 12}, mid);
        System.out.println("=========");

        Integer[] pre = BinaryTree.preOrder1(root);
        Assert.assertArrayEquals(new Integer[]{3, 1, 8, 5, 12}, pre);
        System.out.println("=========");

        Integer[] pos = BinaryTree.posOrder1(root);
        Assert.assertArrayEquals(new Integer[]{1, 5, 12, 8, 3}, pos);
    }

    @Test
    public void testFindNode() {
        TreeNode root = buildTree1();
        TreeNode treeNode = BinaryTree.findNode(root, 22);
        Assert.assertNull(treeNode);

        treeNode = BinaryTree.findNode(root, 8);
        Assert.assertEquals(8, treeNode.getData());
        treeNode = BinaryTree.findNode(root, 12);
        Assert.assertEquals(12, treeNode.getData());
    }

    @Test
    public void testLevelOrder() {
        TreeNode root = buildTree1();
        Integer[] result = BinaryTree.levelOrder1(root);
        Assert.assertArrayEquals(new Integer[]{3, 1, 8, 5, 12}, result);
    }

    @Test
    public void testDepth() {
        TreeNode root = buildTree1();
        int depth = BinaryTree.depth(root);
        Assert.assertEquals(3, depth);
    }

    @Test
    public void testMinDepth() {
        TreeNode root = buildTree1();
        int minDepth = BinaryTree.minDepth(root);
        Assert.assertEquals(2, minDepth);
    }

    @Test
    public void testNumOfTreeNode() {
        TreeNode root = buildTree1();
        int numOfNode = BinaryTree.numOfTreeNode(root);
        Assert.assertEquals(5, numOfNode);
    }

    @Test
    public void testNumOfNoChildNode() {
        TreeNode root = buildTree1();
        int numOfNoChildNode = BinaryTree.numOfNoChildNode(root);
        Assert.assertEquals(3, numOfNoChildNode);
    }

    @Test
    public void testNumOfKLevelTreeNode() {
        TreeNode root = buildTree1();
        int numOfOne = BinaryTree.numOfkLevelTreeNode(root, 1);
        int numOfTwo = BinaryTree.numOfkLevelTreeNode(root, 2);
        int numOfThree = BinaryTree.numOfkLevelTreeNode(root, 3);
        Assert.assertEquals(1, numOfOne);
        Assert.assertEquals(2, numOfTwo);
        Assert.assertEquals(2, numOfThree);
    }

    @Test
    public void testIsBalanced() {
        TreeNode root1 = buildTree1();
        boolean isBalanceRoot1 = BinaryTree.isBalancedBinaryTree(root1);
        Assert.assertTrue(isBalanceRoot1);
        TreeNode root2 = buildTree2();
        boolean isBalanceRoot2 = BinaryTree.isBalancedBinaryTree(root2);
        Assert.assertFalse(isBalanceRoot2);
    }

    @Test
    public void testIsCompleteBinaryTree() {
        TreeNode root1 = buildTree1();
        boolean isCompleteRoot1 = BinaryTree.isCompleteBinaryTree(root1);
        Assert.assertFalse(isCompleteRoot1);

        TreeNode root2 = buildTree3();
        boolean isCompleteRoot2 = BinaryTree.isCompleteBinaryTree(root2);
        Assert.assertTrue(isCompleteRoot2);
    }

    @Test
    public void testBuildTreeByLevelOrder() {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.buildTreeByLevelOrder("3,8,12,5,1");
        Assert.assertArrayEquals(new Integer[]{3, 8, 5, 1, 12}, binaryTree.preOrder1(root));
        Assert.assertArrayEquals(new Integer[]{5, 8, 1, 3, 12}, binaryTree.midOrder1(root));
        Assert.assertArrayEquals(new Integer[]{5, 1, 8, 12, 3}, binaryTree.posOrder1(root));
        Assert.assertArrayEquals(new Integer[]{3, 8, 12, 5, 1}, binaryTree.levelOrder1(root));
    }


    private TreeNode buildTree1() {
        TreeNode root = BinaryTree.insert(null, 3);
        BinaryTree.insert(root, 8);
        BinaryTree.insert(root, 12);
        BinaryTree.insert(root, 5);
        BinaryTree.insert(root, 1);
        return root;
    }

    private TreeNode buildTree2() {
        TreeNode root = BinaryTree.insert(null, 3);
        BinaryTree.insert(root, 8);
        BinaryTree.insert(root, 12);
        BinaryTree.insert(root, 5);
        BinaryTree.insert(root, 1);
        BinaryTree.insert(root, 7);
        return root;
    }

    private TreeNode buildTree3() {
        TreeNode root = BinaryTree.insert(null, 8);
        BinaryTree.insert(root, 6);
        BinaryTree.insert(root, 12);
        BinaryTree.insert(root, 5);
        return root;
    }
}
