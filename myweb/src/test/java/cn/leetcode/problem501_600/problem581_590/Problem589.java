package cn.leetcode.problem501_600.problem581_590;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import cn.leetcode.util.Node;

/**
 * <pre>
 *     N叉树的前序遍历
 *     给定一个 N 叉树，返回其节点值的前序遍历。
 例如，给定一个 3叉树 :
             1
         3   2   4
       5  6

 返回其前序遍历: [1,3,5,6,2,4]。

 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * </pre>
 * 
 * Created by leslie on 2021/2/14.
 */
public class Problem589 {

    @Test
    public void testxxx() {
        Node root = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list1 = new ArrayList<>();
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        root.children = list1;
        List<Node> list2 = new ArrayList<>();
        list2.add(node5);
        list2.add(node6);
        node2.children = list2;
        System.out.println(preorder(root));
    }

    /**
     * <pre>
     *     递归.
     * </pre>
     * 
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }

    /**
     * <pre>
     * 递归函数: 以node为根的树的前序遍历结果放入l中.
     * 这里保存结果的list作为参数. 也可以作为全局变量.
     * </pre>
     *
     * @param node
     * @param l
     */
    private void preorder(Node node, List<Integer> l) {
        if (node == null) {
            return;
        }
        // 前序: 先访问当前节点.
        l.add(node.val);
        // 终止条件.
        if (node.children == null) {
            return;
        }
        for (Node n : node.children) {
            preorder(n, l);
        }
    }

    /**
     * <pre>
     *     非递归: 前序遍历使用栈, 这里使用linkedlisk 代替栈.
     * </pre>
     * 
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            // 前序遍历，先访问当前节点.
            output.add(node.val);
            // 倒序入栈，正序出栈.
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
}
