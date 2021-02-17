package cn.algo.tree.binarytree;

import java.util.*;

import cn.algo.tree.TreeNode;

/**
 * Created by leslie on 2019/8/29.
 */
public class BinaryTree {

    /**
     * <pre>
     *     二叉树遍历框架.
     * </pre>
     * 
     * @param root
     */
    void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.leftNode);
        // 中序遍历
        traverse(root.rightNode);
        // 后序遍历
    }

    /**
     * 插入值到二叉树中 左节点 < 根 < 右节点.
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
            if ((Integer) curr.data > value) {
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
     * 前序遍历: 递归
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
     * <pre>
     *    前序遍历:  迭代.
     *    使用栈 该方法并不适用于中序、后序遍历. 因为前序遍历的访问节点顺序与处理节点顺序是一致的.
     *    后序遍历还好，稍微变换即可.
     * </pre>
     */
    public List<Integer> preorder1(TreeNode<Integer> root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        st.push(root);
        while (!st.empty()) {
            TreeNode<Integer> node = st.pop(); // 中
            // 前序遍历先访问当前节点.
            result.add(node.data);

            // 入栈顺序倒过来.
            if (node.rightNode != null) {
                st.push(node.rightNode); // 右（空节点不入栈）
            }
            if (node.leftNode != null) {
                st.push(node.leftNode); // 左（空节点不入栈）
            }
        }
        return result;
    }

    /**
     * 中序遍历: 递归
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.leftNode);
            System.out.println(node.data);
            inOrder(node.rightNode);
        }
    }

    /**
     * <pre>
     *     中序遍历: 迭代.
     *     跟preOrder1不同, 访问节点的顺序和处理的顺序不一致. 这里用指针的遍历来帮助访问节点，栈则用来处理节点上的元素。
     * </pre>
     * @param root
     * @return
     */
    public List<Integer> inorder1(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode<Integer> cur = root;
        while (cur != null || !st.empty()) {
            if (cur != null) { // 指针来访问节点，访问到最底层
                st.push(cur); // 将访问的节点放进栈
                cur = cur.leftNode; // 左
            } else {
                cur = st.pop(); // 从栈里弹出的数据，就是要处理的数据（放进result数组里的数据）
                result.add(cur.data); // 中
                cur = cur.rightNode; // 右
            }
        }
        return result;
    }

    /**
     * 后序遍历: 递归
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.leftNode);
            postOrder(node.rightNode);
            System.out.println(node.data);
        }
    }

    /**
     * <pre>
     *     后序遍历: 迭代.
     *     利用根-右-左的倒序. 类似前面的前序遍历, 只不过入栈顺序不同，最后结果倒序.
     *     只要先访问根节点就好办.
     * </pre>
     * 
     * @param root
     * @return
     */
    public List<Integer> postorder1(TreeNode<Integer> root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        st.push(root);
        while (!st.empty()) {
            TreeNode<Integer> node = st.pop();
            result.add(node.data);
            if (node.leftNode != null) {
                st.push(node.leftNode); // 相对于前序遍历，这更改一下入栈顺序 （空节点不入栈）
            }
            if (node.rightNode != null) {
                st.push(node.rightNode); // 空节点不入栈
            }
        }
        Collections.reverse(result); // 将结果反转之后就是左右中的顺序了
        return result;
    }

    /**
     * 后序遍历（非递归）
     *
     * @param node
     */
    public static Integer[] postOrder1(TreeNode node) {
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
     * 后序遍历（非递归） 前序遍历 根--左--右 后序遍历 左--右--根 借用前序遍历算法思想 修改成 根--右--左，然后反转得到 左--右--根
     *
     * @param node
     * @return
     */
    public static ArrayList<Integer> postOrder2(TreeNode node) {
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
        // 计算深度
        int depth = depth(node);
        for (int i = 0; i < depth; i++) {
            // 根据第几层得到所处第几层的所有元素
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
     * 是否是平衡二叉树 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
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
     * 是否是完全二叉树 若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
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
            // 编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
            // 这里父节点有1（2,3）,2（4,5）,3（6,7）,4（8,9） 但是最后一个父节点有可能没有右子节点 需要单独处理
            nodelist.get(index).leftNode = (nodelist.get(index * 2 + 1));
            nodelist.get(index).rightNode = (nodelist.get(index * 2 + 2));
        }
        // 单独处理最后一个父节点 因为它有可能没有右子节点
        int index = nodelist.size() / 2 - 1;
        nodelist.get(index).leftNode = (nodelist.get(index * 2 + 1)); // 先设置左子节点
        if (nodelist.size() % 2 == 1) { // 如果有奇数个节点，最后一个父节点才有右子节点
            nodelist.get(index).rightNode = (nodelist.get(index * 2 + 2));
        }
        return nodelist.get(0);
    }

    /**
     * 根据前序遍历还原二叉树 (在前序序列化的时候，如果遇到为null的节点，则在字符串后面添加“#，” 当反序列化时，会进行判断，当前的位置是否为"#"，如果为#则不会创建子节点)
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
     * 反转二叉树 或者 建立一颗二叉树的镜像（如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。）
     *
     * @param root
     */
    public static void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if ((root.leftNode == null) && (root.rightNode == null)) {
            return;
        }
        TreeNode temp = root.leftNode;
        root.leftNode = root.rightNode;
        root.rightNode = temp;
        mirror(root.leftNode);
        mirror(root.rightNode);
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

}
