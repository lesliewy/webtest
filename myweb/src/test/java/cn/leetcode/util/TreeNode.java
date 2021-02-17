package cn.leetcode.util;

/**
 * Created by leslie on 2019/11/28.
 */

public class TreeNode {

    public int      val;
    // 左孩子
    public TreeNode left;
    // 右孩子
    public TreeNode right;

    public TreeNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return serialize(this);
    }

    /* 主函数，将二叉树序列化为字符串 */
    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /* 辅助函数，将二叉树存入 StringBuilder */
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
//            sb.append("-1").append(",");
            return;
        }

        /****** 前序遍历位置 ******/
        sb.append(root.val).append(",");
        /***********************/

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

}
