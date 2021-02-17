package cn.algo.tree.binarytree;

import cn.algo.tree.TreeNode;

/**
 * <pre>
 *     二叉搜索树
 *     一个二叉树中，任意节点的值要大于等于左子树所有节点的值，且要小于等于右边子树的所有节点的值。
 * </pre>
 * 
 * Created by leslie on 2020/10/27.
 */
public class BinarySearchTree {

    /**
     * <pre>
     *    二叉搜索树解题框架:
     *    void traverse(TreeNode root) {
            // 当前节点(root) 需要做什么？在这做。
            // 其他的不用 root 操心，抛给框架   递归调用.
            traverse(root.left);
            traverse(root.right);
          }
     * </pre>
     *
     * @param root
     * @return
     */
    public static void plusOne(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        root.data += 1;

        plusOne(root.leftNode);
        plusOne(root.rightNode);
    }

    /**
     * <pre>
     *     二叉树的节点数.
     * </pre>
     * 
     * @param root
     * @return
     */
    public static int count(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        // 自己加上子树的节点数就是整棵树的节点数
        return 1 + count(root.leftNode) + count(root.rightNode);
    }

    /**
     * <pre>
     *     利用前序遍历，一个一个节点比较.
     * </pre>
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isSameTree1(TreeNode root1, TreeNode root2) {
        // 都为空的话，显然相同
        if (root1 == null && root2 == null) {
            return true;
        }
        // 一个为空，一个非空，显然不同
        if (root1 == null || root2 == null) {
            return false;
        }
        // 两个都非空，但 val 不一样也不行
        if (root1.data != root2.data) {
            return false;
        }
        // 不能只比较 root1.data == root2.data ， 就 return true

        // root1 和 root2 该比的都比完了
        return isSameTree1(root1.leftNode, root2.leftNode) && isSameTree1(root1.rightNode, root2.rightNode);
    }

    /**
     * 判断两颗二叉树是否相同
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean sameTree2(TreeNode root1, TreeNode root2) {
        // 树的结构不一样
        if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
            return false;
        }

        // 两棵树最终递归到终点时
        if (root1 == null && root2 == null) {
            return true;
        }
        if (Integer.valueOf((Integer) root1.data).compareTo(Integer.valueOf((Integer) root2.data)) != 0) {
            return false;
        } else {
            return sameTree2(root1.leftNode, root2.leftNode) && sameTree2(root1.rightNode, root2.rightNode);
        }
    }

    /**
     * <pre>
     * 判断是否是合法的二叉搜索树
     * </pre>
     * 
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode<Integer> root) {
        return isValidBST(root, null, null);
    }

    /**
     * <pre>
     *     左节点树上的所有节点值 <= 节点值 <= 右节点树上的所有节点值.
     *     如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息
     * </pre>
     * 
     * @param root
     * @param min
     * @param max
     * @return
     */
    private static boolean isValidBST(TreeNode<Integer> root, TreeNode<Integer> min, TreeNode<Integer> max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.data <= min.data) {
            return false;
        }
        if (max != null && root.data >= max.data) {
            return false;
        }
        return isValidBST(root.leftNode, min, root) && isValidBST(root.rightNode, root, max);
    }

    /**
     * <pre>
     * 会遍历每一个节点.类似前序遍历
     * </pre>
     * 
     * @param root
     * @param target
     * @return
     */
    public static boolean isInBST(TreeNode<Integer> root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }

        // 短路 找到为true时，不会再找后面的节点.
        return isInBST(root.leftNode, target) || isInBST(root.rightNode, target);
    }

    /**
     * <pre>
     *     利用二叉搜索树的左小右大特性. 二分查找.
     * </pre>
     * 
     * @param root
     * @param target
     * @return
     */
    public static boolean isInBST2(TreeNode<Integer> root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }
        if (root.data < target) {
            return isInBST2(root.rightNode, target);
        }
        if (root.data > target) {
            return isInBST2(root.leftNode, target);
        }
        // root 该做的事做完了，顺带把框架也完成了，妙
        return false;
    }

    /**
     * <pre>
     *     二叉搜索树遍历框架.
     * </pre>
     * 
     * @param root
     * @param target
     */
    public static void BST(TreeNode<Integer> root, int target) {
        if (root.data == target) {
            // 找到目标，做点什么
        }
        if (root.data < target) {
            BST(root.rightNode, target);
        }
        if (root.data > target) {
            BST(root.leftNode, target);
        }
    }

    /**
     * <pre>
     *     二叉搜索树插入框架.
     *     二分查找;
     *     接收返回值，相当于原路返回, 直至root
     * </pre>
     * 
     * @param root
     * @param val
     * @return
     */
    public static TreeNode insertIntoBST(TreeNode<Integer> root, int val) {
        // 找到空位置插入新节点
        if (root == null) {
            return new TreeNode(val);
        }
        // if (root.val == val)
        // BST 中一般不会插入已存在元素
        if (root.data < val) {
            root.rightNode = insertIntoBST(root.rightNode, val);
        }
        if (root.data > val) {
            root.leftNode = insertIntoBST(root.leftNode, val);
        }
        return root;
    }

    /**
     * <pre>
     *     二叉搜索树删除节点.
     *     root.val == key:
     *        1, 节点没有左、右节点:  直接删除;
     *        2, 节点只有左节点或者只有右节点: 用该节点替换被删除节点;
     *        3, 节点两个子节点都有: 左子树最大节点 或者 右子树最小节点替换被删除节点;
     *     root.val > key:
     *        处理左子节点.
     *     root.val < key:
     *        处理右子节点.
     * </pre>
     * 
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode(TreeNode<Integer> root, int key) {
        if (root == null) {
            return null;
        }
        if (root.data == key) {
            // 这两个 if 把情况 1 和 2 都正确处理了
            if (root.leftNode == null) {
                return root.rightNode;
            }
            if (root.rightNode == null) {
                return root.leftNode;
            }
            // 处理情况 3
            TreeNode<Integer> minNode = getMin(root.rightNode);
            root.data = minNode.data;
            root.rightNode = deleteNode(root.rightNode, minNode.data);
        } else if (root.data > key) {
            root.leftNode = deleteNode(root.leftNode, key);
        } else if (root.data < key) {
            root.rightNode = deleteNode(root.rightNode, key);
        }
        return root;
    }

    private static TreeNode getMin(TreeNode<Integer> node) {
        // BST 最左边的就是最小的
        while (node.leftNode != null) {
            node = node.leftNode;
        }
        return node;
    }
}
