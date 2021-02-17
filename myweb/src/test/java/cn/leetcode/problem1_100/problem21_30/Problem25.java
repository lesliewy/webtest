package cn.leetcode.problem1_100.problem21_30;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     K 个一组翻转链表
 *     给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 k 是一个正整数，它的值小于或等于链表的长度。
 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 示例：
 给你这个链表：1->2->3->4->5
 当 k = 2 时，应当返回: 2->1->4->3->5
 当 k = 3 时，应当返回: 3->2->1->4->5

 说明：
 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * </pre>
 * 
 * Created by leslie on 2020/11/17.
 */
public class Problem25 {

    /**
     * <pre>
     *     具有递归性质, 即存在子问题.
     *   1, 先反转以 head 开头的 k 个元素
     *   2, 将第 k + 1 个元素作为 head 递归调用 reverseKGroup 函数。
     *   3, 将上述两个过程的结果连接起来。
     *   base case: 最后的元素不足 k 个，就保持不变。
     * </pre>
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * <pre>
     *    迭代
     *    反转以 a 为头结点的链表
     * </pre>
     * 
     * @param a
     * @return
     */
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * <pre>
     *     迭代
     *     反转区间 [a, b) 的元素，注意是左闭右开
     * </pre>
     */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }
}
