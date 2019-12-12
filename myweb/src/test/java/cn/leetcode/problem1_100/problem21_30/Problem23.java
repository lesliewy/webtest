package cn.leetcode.problem1_100.problem21_30;

import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     合并k个排序链表.
 *     合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *     输入:
 *     [
 *        1->4->5,
 *        1->3->4,
 *        2->6
 *     ]
 *     输出: 1->1->2->3->4->4->5->6
 * </pre>
 * 
 * Created by leslie on 2019/12/4.
 */
public class Problem23 {

    @Test
    public void test1() {
        ListNode node1 = ListNode.createNode(new int[] { 1, 4, 5 }, -1);
        ListNode node2 = ListNode.createNode(new int[] { 1, 3, 4 }, -1);
        ListNode node3 = ListNode.createNode(new int[] { 2, 6 }, -1);
        ListNode[] nodes = new ListNode[] { node1, node2, node3 };
        ListNode result = mergeKLists(nodes);
        ListNode.print(result);
    }

    /**
     * <pre>
     *     方法一: 遍历所有链表的第一个元素，找出最小值，然后将该链表向后移动，再次遍历所有链表的第一个元素，找出最小值, 依次循环。
     * </pre>
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return lists[0];
        }
        int min;
        int minNodeIndex = 0;
        ListNode result = null, node, preNode = null;
        boolean isFirst = true;
        int lastListIndex = -1;
        while (true) {
            min = Integer.MAX_VALUE;
            int listFinished = 0;
            lastListIndex = -1;
            for (int i = 0; i < len; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minNodeIndex = i;
                }
                if (lists[i] == null) {
                    listFinished++;
                } else {
                    lastListIndex = i;
                }
            }
            if (listFinished >= len - 1) {
                break;
            }
            lists[minNodeIndex] = lists[minNodeIndex].next;
            node = new ListNode(min);
            if (isFirst) {
                result = node;
                preNode = node;
                isFirst = false;
                continue;
            }
            preNode.next = node;
            preNode = node;
        }

        // 说明数组中都是空链表
        if (lastListIndex == -1) {
            return result;
        }

        // 类似归并一样，将最后剩下的添加到最后.
        ListNode lastList = lists[lastListIndex];
        // 数组中只有一个链表
        if (preNode == null) {
            return lastList;
        }
        while (lastList != null) {
            ListNode tempNode = new ListNode(lastList.val);
            preNode.next = tempNode;
            preNode = tempNode;
            lastList = lastList.next;
        }
        return result;
    }

    /**
     * <pre>
     *     方法二: 遍历所有链表的所有节点，放入数组中，对数组排序, 生成链表.
     * </pre>
     */

    /**
     * <pre>
     *     方法三: 两两合并链表。
     * </pre>
     */
}
