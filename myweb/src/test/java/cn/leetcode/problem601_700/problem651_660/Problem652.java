package cn.leetcode.problem601_700.problem651_660;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.leetcode.util.TreeNode;

/**
 * <pre>
 *    寻找重复的子树
 *    给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 两棵树重复是指它们具有相同的结构以及相同的结点值。

 示例 1：
        1
       / \
      2   3
     /   / \
    4   2   4
   /
  4
 下面是两个重复的子树：
    2
   /
  4
 和
 4
 因此，你需要以列表的形式返回上述重复子树的根结点。
 * </pre>
 * 
 * Created by leslie on 2020/11/3.
 */
public class Problem652 {

    // 记录所有子树以及出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode>     res  = new LinkedList<>();

    /**
     * <pre>
     *    先序列化当前节点的子树，再和map中其他序列化结果比较.
     *    使用后序序列化。
     * </pre>
     * 
     * @TODO 前序、中序不知道可不可以.
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    /* 辅助函数 */
    String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }
}
