package cn.leetcode.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by leslie on 2019/11/28.
 */
public class TreeUtils {

    public static TreeNode createBst(int[] a) {
        return null;
    }

    /**
     * <pre>
     *     层序遍历构造二叉树.  -1 表示节点不存在.
     * </pre>
     *
     * @param str
     * @return
     */
    public static TreeNode buildTreeByLevelOrder(String str) {
        String[] nodeArr = str.split(",");
        List<TreeNode> nodelist = new LinkedList<>();
        for (String s : nodeArr) {
            nodelist.add(new TreeNode(Integer.valueOf(s)));
        }
        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
            // 编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
            // 这里父节点有1（2,3）,2（4,5）,3（6,7）,4（8,9） 但是最后一个父节点有可能没有右子节点 需要单独处理
            int left = nodelist.get(index * 2 + 1).val;
            int right = nodelist.get(index * 2 + 2).val;
            if (left != -1) {
                nodelist.get(index).left = (nodelist.get(index * 2 + 1));
            }
            if (right != -1) {
                nodelist.get(index).right = (nodelist.get(index * 2 + 2));
            }
        }
        // 单独处理最后一个父节点 因为它有可能没有右子节点
        int index = nodelist.size() / 2 - 1;
        nodelist.get(index).left = (nodelist.get(index * 2 + 1)); // 先设置左子节点
        if (nodelist.size() % 2 == 1) { // 如果有奇数个节点，最后一个父节点才有右子节点
            nodelist.get(index).right = (nodelist.get(index * 2 + 2));
        }
        return nodelist.get(0);
    }
}
