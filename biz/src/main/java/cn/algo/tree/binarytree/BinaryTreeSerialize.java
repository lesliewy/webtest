package cn.algo.tree.binarytree;

import cn.algo.tree.TreeNode;

/**
 * <pre>
 * </pre>
 * 
 * Created by leslie on 2020/11/4.
 */
public class BinaryTreeSerialize {

    private String SEP  = ",";
    private String NULL = "#";

    /* 主函数，将二叉树序列化为字符串 */
    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /* 辅助函数，将二叉树存入 StringBuilder */
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        /****** 前序遍历位置 ******/
        sb.append(root.data).append(SEP);
        /***********************/

        serialize(root.leftNode, sb);
        serialize(root.rightNode, sb);
    }
}
