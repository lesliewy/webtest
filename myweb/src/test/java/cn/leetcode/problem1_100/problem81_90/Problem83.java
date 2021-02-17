package cn.leetcode.problem1_100.problem81_90;

import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     删除排序链表中的重复元素
 *     给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

 示例 1:
 输入: 1->1->2
 输出: 1->2

 示例 2:
 输入: 1->1->2->3->3
 输出: 1->2->3
 * </pre>
 * 
 * Created by leslie on 2020/10/27.
 */
public class Problem83 {

    @Test
    public void test1() {
        ListNode listNode1 = ListNode.createNode(new int[] { 1, 1, 2 }, -1);
        ListNode.print(deleteDuplicates(listNode1));

        ListNode listNode2 = ListNode.createNode(new int[] { 1, 1, 2, 3, 3 }, -1);
        ListNode.print(deleteDuplicates(listNode2));
    }

    /**
     * 快慢指针.
     * 
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 最后一步.
        slow.next = null;
        return head;
    }
}
