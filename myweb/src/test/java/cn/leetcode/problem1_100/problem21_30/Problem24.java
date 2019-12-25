package cn.leetcode.problem1_100.problem21_30;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     两两交换链表中的节点
 *     给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * </pre>
 * 
 * Created by leslie on 2019/11/15.
 */
public class Problem24 {

    @Test
    public void test1() {
        ListNode t1 = createNode(new int[] { 1, 2, 3, 4 });
        Assert.assertEquals("1234", forwardString(t1));

        Assert.assertEquals("1", forwardString(swapPairs(createNode(new int[] { 1 }))));
        Assert.assertEquals("2143", forwardString(swapPairs(t1)));
        Assert.assertEquals("213", forwardString(swapPairs(createNode(new int[] { 1, 2, 3 }))));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode nodeHead;
        if (head.next != null) {
            nodeHead = head.next;
        } else {
            nodeHead = head;
        }
        ListNode pairPre = head, pairPost, nextPair;
        while (pairPre != null) {
            pairPost = pairPre.next;
            if (pairPost == null) {
                break;
            }
            nextPair = pairPost.next;
            pairPost.next = pairPre;

            if (nextPair == null) {
                pairPre.next = null;
                break;
            }
            if (nextPair.next == null) {
                pairPre.next = nextPair;
                break;
            }
            pairPre.next = nextPair.next;

            pairPre = nextPair;
        }
        return nodeHead;
    }

    public String forwardString(ListNode t) {
        ListNode t1 = t;
        StringBuilder sb = new StringBuilder("");
        while (t1 != null) {
            sb.append(t1.val);
            t1 = t1.next;
        }
        return sb.toString();
    }

    public ListNode createNode(int[] vals) {
        ListNode result = null;
        ListNode pre = null;
        boolean first = true;
        for (int i : vals) {
            ListNode curr = new ListNode(i);
            if (first) {
                result = curr;
            } else {
                pre.next = curr;
            }
            first = false;
            pre = curr;
        }
        return result;

    }
}
