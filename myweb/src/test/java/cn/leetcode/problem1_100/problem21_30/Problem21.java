package cn.leetcode.problem1_100.problem21_30;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     合并两个有序链表
 *     将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

 示例：
 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 * </pre>
 * 
 * Created by leslie on 2019/12/15.
 */
public class Problem21 {

    /**
     * <pre>
     *     方法一: 递归.
     *     两种情况。
     *     l1 + merge(l1[1:], l2)
     *     l2 + merge(l1, l2[1:])
     *     时间复杂度: O(n+m)
     *     空间复杂度: O(n+m)  每个元素都会被遍历，形成n+m个栈.
     * </pre>
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * <pre>
     *     方法二: 迭代.
     *     类似于归并排序合并两个数组.
     * </pre>
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        // 哨兵处理第一个节点。 否则需要判断l1, l2 的第一个节点, 比较麻烦。
        ListNode prehead = new ListNode(-1);

        // 链表最终要返回头节点，通常头节点需要2个，一个用于返回，一个用于向下处理。
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // 对于链表直接修改指针指向就行，不需要新建节点. 因为每个节点都是独立对象, 可以有多个来源.
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        // 合并剩余的。
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
