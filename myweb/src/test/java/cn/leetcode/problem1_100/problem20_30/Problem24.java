package cn.leetcode.problem1_100.problem20_30;

import org.junit.Assert;
import org.junit.Test;

/**
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

class ListNode {

    int      val;
    ListNode next;

    ListNode(int x){
        val = x;
    }
}
