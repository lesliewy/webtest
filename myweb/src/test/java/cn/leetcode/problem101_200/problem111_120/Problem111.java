package cn.leetcode.problem101_200.problem111_120;

import java.util.LinkedList;
import java.util.Queue;

import cn.algo.tree.TreeNode;

/**
 * <pre>
 *     二叉树的最小深度
 *     给定一个二叉树，找出其最小深度。
 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 说明：叶子节点是指没有子节点的节点。

 输入：root = [3,9,20,null,null,15,7]
 输出：2

 示例 2：
 输入：root = [2,null,3,null,4,null,5,null,6]
 输出：5

 提示：
 树中节点数的范围在 [0, 105] 内
 -1000 <= Node.val <= 1000
 * </pre>
 * 
 * Created by leslie on 2021/1/21.
 */
public class Problem111 {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.leftNode == null && cur.rightNode == null) return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.leftNode != null) q.offer(cur.leftNode);
                if (cur.rightNode != null) q.offer(cur.rightNode);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }
}
