package cn.leetcode.problem201_300.problem201_210;

import java.util.Stack;

import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     反转链表
 *     反转一个单链表
 *
 *     输入: 1->2->3->4->5->NULL
 *     输出: 5->4->3->2->1->NULL
 * </pre>
 * 
 * Created by leslie on 2020/1/3.
 */
public class Problem206 {

    @Test
    public void testxxx() {
        ListNode node1 = ListNode.createNode(new int[] { 1, 2, 3, 4, 5 }, -1);
        reverseList2(node1);
    }

    /**
     * <pre>
     *     方法一: 栈.  遍历一次值入栈, 出栈时构建链表.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        Stack<Integer> s = new Stack();
        ListNode node = head;
        while (node != null) {
            s.push(node.val);
            node = node.next;
        }
        if (s.isEmpty()) {
            return null;
        }

        ListNode ans = new ListNode(s.pop());
        ListNode pre = ans;
        while (!s.isEmpty()) {
            ListNode newNode = new ListNode(s.pop());
            if (pre != null) {
                pre.next = newNode;
            }
            pre = newNode;
        }
        return ans;
    }

    /**
     * <pre>
     *     方法二: 一次遍历.  n节点是， (n-1)节点指向(n-2)节点.
     *     时间复杂度: O(N)
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prePre = head, pre = head.next, cur = head.next.next;
        prePre.next = null;

        while (cur != null) {
            pre.next = prePre;
            prePre = pre;
            pre = cur;
            cur = cur.next;
        }
        pre.next = prePre;
        return pre;
    }

    /**
     * <pre>
     *     方法三: 递归.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

}
