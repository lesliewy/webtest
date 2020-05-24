package cn.algo.tree.binarytree;

import java.util.*;

import cn.algo.tree.TreeNode;

/**
 * Created by leslie on 2019/8/29.
 */
public class BinaryTree {

    /**
     * 插入值到二叉树中
     * 左节点 < 根 < 右节点.
     *
     * @param value
     */
    public static TreeNode insert(TreeNode<Integer> treeNode, int value) {
        TreeNode node = new TreeNode(value);
        TreeNode curr = treeNode;
        TreeNode parent;
        if (treeNode == null) {
            treeNode = new TreeNode(value);
            return treeNode;
        }
        while (true) {
            parent = curr;
            if ((Integer)curr.data > value) {
                curr = curr.leftNode;
                if (curr == null) {
                    parent.leftNode = node;
                    return treeNode;
                }
            } else {
                curr = curr.rightNode;
                if (curr == null) {
                    parent.rightNode = node;
                    return treeNode;
                }
            }
        }
    }

    /**
     * 查找指定值的树节点
     *
     * @param value
     * @return
     */

    public static TreeNode findNode(TreeNode<Integer> root, int value) {

        TreeNode curr = root;
        while (curr != null && (Integer) curr.data != value) {
            if ((Integer) curr.data > value) {
                curr = curr.leftNode;
            } else {
                curr = curr.rightNode;
            }
        }
        if (curr == null) {
            return null;
        }
        return new TreeNode(curr.data);
    }

    /**
     * 前序遍历（递归）
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.leftNode);
            preOrder(node.rightNode);
        }
    }

    /**
     * 前序遍历（非递归）
     *
     * @param node
     */
    public static Integer[] preOrder1(TreeNode node) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                System.out.println(node.data);
                resultList.add((Integer) node.data);
                stack.push(node);
                node = node.leftNode;
            }
            if (!stack.empty()) {
                node = stack.pop();
                node = node.rightNode;
            }
        }
        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 中序遍历（递归）
     *
     * @param node
     */
    public static void midOrder(TreeNode node) {
        if (node != null) {
            midOrder(node.leftNode);
            System.out.println(node.data);
            midOrder(node.rightNode);
        }
    }

    /**
     * 树的深度遍历: 前序、中序、后序.
     * 时间复杂度是:O(N), N是节点数，因为每个节点只访问了一次.
     *
     * 中序遍历（非递归）
     *
     * @param node
     */
    public static Integer[] midOrder1(TreeNode node) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftNode;
            }
            if (!stack.empty()) {
                node = stack.pop();
                System.out.println(node.data);
                resultList.add((Integer) node.data);
                node = node.rightNode;
            }
        }
        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void posOrder(TreeNode node) {
        if (node != null) {
            posOrder(node.leftNode);
            posOrder(node.rightNode);
            System.out.println(node.data);
        }
    }

    /**
     * 后序遍历（非递归）
     *
     * @param node
     */
    public static Integer[] posOrder1(TreeNode node) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (node != null || !stack1.empty()) {
            while (node != null) {
                stack1.push(node);
                stack2.push(0);
                node = node.leftNode;
            }
            while (!stack1.empty() && stack2.peek() == 1) {
                stack2.pop();
                int val = (Integer) stack1.pop().data;
                System.out.println(val);
                resultList.add(val);
            }
            if (!stack1.empty()) {
                stack2.pop();
                stack2.push(1);
                node = stack1.peek();
                node = node.rightNode;
            }
        }
        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 后序遍历（非递归）
     * 前序遍历  根--左--右
     * 后序遍历  左--右--根
     * 借用前序遍历算法思想 修改成 根--右--左，然后反转得到  左--右--根
     *
     * @param node
     * @return
     */
    public static ArrayList<Integer> posOrder2(TreeNode node) {
        ArrayList<Integer> list = new ArrayList<>();
        if (node != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.empty()) {
                TreeNode node1 = stack.pop();
                list.add((Integer) node1.data);
                if (node1.leftNode != null) {
                    stack.push(node1.leftNode);
                }
                if (node1.rightNode != null) {
                    stack.push(node1.rightNode);
                }
            }
            Collections.reverse(list);
        }
        return list;
    }

    /**
     * 层序遍历（递归）
     *
     * @param node
     */
    public static void levelOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        //计算深度
        int depth = depth(node);
        for (int i = 0; i < depth; i++) {
            //根据第几层得到所处第几层的所有元素
            leveOrder(node, i);
        }
    }

    /**
     * 层序遍历（非递归）
     *
     * @param node
     */
    public static Integer[] levelOrder1(TreeNode node) {
        List<Integer> resultList = new ArrayList<>();
        if (node == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode node1;
        while (!queue.isEmpty()) {
            node1 = queue.poll();
            System.out.println(node1.data);
            resultList.add((Integer) node1.data);
            if (node1.leftNode != null) {
                queue.offer(node1.leftNode);
            }
            if (node1.rightNode != null) {
                queue.offer(node1.rightNode);
            }
        }
        return resultList.toArray(new Integer[resultList.size()]);
    }

    /**
     * 得到第几层的所有元素
     *
     * @param node
     * @param level
     */
    public static void leveOrder(TreeNode node, int level) {

        if (node == null || level < 1) {
            return;
        }
        if (level == 1) {
            System.out.println(node.data);
            return;
        }
        leveOrder(node.leftNode, level - 1);
        leveOrder(node.rightNode, level - 1);
    }

    /**
     * 二叉树的深度
     *
     * @param node
     * @return
     */
    public static int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = depth(node.leftNode);
        int r = depth(node.rightNode);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    /**
     * 二叉树最小深度.
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    private static int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.leftNode == null && root.rightNode == null) {
            return 1;
        }
        return Math.min(getMin(root.leftNode), getMin(root.rightNode)) + 1;
    }

    /**
     * 节点个数.
     *
     * @param root
     * @return
     */
    public static int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfTreeNode(root.leftNode);
        int right = numOfTreeNode(root.rightNode);
        return left + right + 1;
    }

    /**
     * 叶子节点个数.
     *
     * @param root
     * @return
     */
    public static int numOfNoChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.leftNode == null && root.rightNode == null) {
            return 1;
        }
        return numOfNoChildNode(root.leftNode) + numOfNoChildNode(root.rightNode);
    }

    /**
     * 二叉树中第k层节点的个数
     *
     * @param root
     * @param k
     * @return
     */
    public static int numOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numLeft = numOfkLevelTreeNode(root.leftNode, k - 1);
        int numRight = numOfkLevelTreeNode(root.rightNode, k - 1);
        return numLeft + numRight;
    }

    /**
     * 是否是平衡二叉树
     * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
     *
     * @param node
     * @return
     */
    public static boolean isBalancedBinaryTree(TreeNode node) {
        return maxDeath2(node) != -1;
    }

    private static int maxDeath2(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDeath2(node.leftNode);
        int right = maxDeath2(node.rightNode);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 是否是完全二叉树
     * 若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
     *
     * @param root
     * @return
     */
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean result = true;
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (hasNoChild) {
                if (current.leftNode != null || current.rightNode != null) {
                    result = false;
                    break;
                }
            } else {
                if (current.leftNode != null && current.rightNode != null) {
                    queue.add(current.leftNode);
                    queue.add(current.rightNode);
                } else if (current.leftNode != null && current.rightNode == null) {
                    queue.add(current.leftNode);
                    hasNoChild = true;
                } else if (current.leftNode == null && current.rightNode != null) {
                    result = false;
                    break;
                } else {
                    hasNoChild = true;
                }
            }
        }
        return result;
    }

    /**
     * 通过层序遍历构建二叉树
     *
     * @param str 层序遍历的任意参数
     * @return 二叉树
     */
    public static TreeNode buildTreeByLevelOrder(String str) {
        String[] nodeArr = str.split(",");
        List<TreeNode> nodelist = new LinkedList<>();
        for (String s : nodeArr) {
            nodelist.add(new TreeNode(Integer.valueOf(s)));
        }
        System.out.println(nodelist.size());
        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
            //编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
            //这里父节点有1（2,3）,2（4,5）,3（6,7）,4（8,9） 但是最后一个父节点有可能没有右子节点 需要单独处理
            nodelist.get(index).leftNode = (nodelist.get(index * 2 + 1));
            nodelist.get(index).rightNode = (nodelist.get(index * 2 + 2));
        }
        //单独处理最后一个父节点 因为它有可能没有右子节点
        int index = nodelist.size() / 2 - 1;
        nodelist.get(index).leftNode = (nodelist.get(index * 2 + 1)); //先设置左子节点
        if (nodelist.size() % 2 == 1) { //如果有奇数个节点，最后一个父节点才有右子节点
            nodelist.get(index).rightNode = (nodelist.get(index * 2 + 2));
        }
        return nodelist.get(0);
    }

    /**
     * 根据前序遍历还原二叉树
     * (在前序序列化的时候，如果遇到为null的节点，则在字符串后面添加“#，”
     * 当反序列化时，会进行判断，当前的位置是否为"#"，如果为#则不会创建子节点)
     */
    int index = -1;

    TreeNode Deserialize(String str) {
        if (str.length() == 0) {
            return null;
        }

        index++;
        int len = str.length();
        if (index >= len) {
            return null;
        }
        String[] strr = str.split(",");
        TreeNode node = null;
        if (!strr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.leftNode = Deserialize(str);
            node.rightNode = Deserialize(str);
        }

        return node;
    }

    /**
     * 反转二叉树
     * 或者
     * 建立一颗二叉树的镜像（如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。）
     *
     * @param root
     */
    public static void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if ((root.leftNode == null) && (root.rightNode == null)) {
            return;
        }
        TreeNode temp = root.leftNode;
        root.leftNode = root.rightNode;
        root.rightNode = temp;
        Mirror(root.leftNode);
        Mirror(root.rightNode);
    }

    /**
     * 克隆一颗二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode cloneTree(TreeNode root) {
        TreeNode node = null;
        if (root == null) {
            return null;
        }
        node = new TreeNode(root.data);
        node.leftNode = cloneTree(root.leftNode);
        node.rightNode = cloneTree(root.rightNode);
        return node;
    }

    /**
     * 判断两颗二叉树是否相同
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean sameTree2(TreeNode root1, TreeNode root2) {
        //树的结构不一样
        if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
            return false;
        }

        //两棵树最终递归到终点时
        if (root1 == null && root2 == null) {
            return true;
        }
        if (Integer.valueOf((Integer) root1.data).compareTo(Integer.valueOf((Integer) root2.data)) != 0) {
            return false;
        } else {
            return sameTree2(root1.leftNode, root2.leftNode) && sameTree2(root1.rightNode, root2.rightNode);
        }
    }
}
