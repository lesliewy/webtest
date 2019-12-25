package cn.leetcode.problem101_200.problem141_150;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 * 环形链表
 * 给定一个链表，判断链表中是否有环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 输入：head = [3,2,0,-4], pos = 1 输出：true 解释：链表中有一个环，其尾部连接到第二个节点。
 * 输入：head = [1,2], pos = 0 输出：true 解释：链表中有一个环，其尾部连接到第一个节点。
 * 输入：head = [1], pos = -1 输出：false 解释：链表中没有环。
 * </pre>
 * 
 * Created by leslie on 2019/11/26.
 */
public class Problem141 {

    @Test
    public void test1() {
        Assert.assertTrue(hasCycle2(ListNode.createNode(new int[] { 3, 2, 0, -4 }, 2)));
        Assert.assertTrue(hasCycle2(ListNode.createNode(new int[] { 1, 2 }, 0)));
        Assert.assertFalse(hasCycle2(ListNode.createNode(new int[] { 1 }, -1)));
        Assert.assertFalse(hasCycle2(ListNode.createNode(new int[] { -21, 10, 17, 8, 4, 26, 5, 35, 33, -7, -16, 27, -12,
                                                                     6, 29, -12, 5, 9, 20, 14, 14, 2, 13, -24, 21, 23,
                                                                     -21, 5 },
                                                         -1)));

    }

    /**
     * <pre>
     *    方法一: 使用node节点的hashcode作为唯一标识.
     *    时间复杂度: O(n), 空间复杂度: O(n)
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode node = head;
        Set<Integer> allNodes = new HashSet<>();
        while (node != null) {
            int val = node.hashCode();
            if (allNodes.contains(val)) {
                return true;
            }
            allNodes.add(val);
            node = node.next;
        }
        return false;
    }

    /**
     * <p>
     * 方法二: 快慢指针. 快指针追上慢指针则存在环; 快指针先到达末尾则不存在环.
     * </p>
     * <p>
     * 时间复杂度: O(n), 空间复杂度: O(1)
     * </p>
     * 
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        // 只有一个节点情况且无环情况.
        if (head == null || head.next == null) {
            return false;
        }
        // 只有两个节点且无环情况.
        if (head.next.next == null) {
            return false;
        }

        ListNode fast = head.next, slow = head;
        while (fast != null && slow != null) {
            // 有null 就说明无环.
            if (fast == null) {
                return false;
            }
            // 快指针追上慢指针.
            if ((fast == slow || fast.next == slow)) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;

        }
        return false;
    }
}
