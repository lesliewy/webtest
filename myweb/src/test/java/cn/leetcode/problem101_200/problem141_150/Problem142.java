package cn.leetcode.problem101_200.problem141_150;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     环形链表 II
 *     给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 说明：不允许修改给定的链表。

 输入：head = [3,2,0,-4], pos = 1
 输出：tail connects to node index 1
 解释：链表中有一个环，其尾部连接到第二个节点

 输入：head = [1,2], pos = 0
 输出：tail connects to node index 0
 解释：链表中有一个环，其尾部连接到第一个节点。

 输入：head = [1], pos = -1
 输出：no cycle
 解释：链表中没有环。

 进阶：
 你是否可以不用额外空间解决此题？
 * </pre>
 * 
 * Created by leslie on 2019/12/18.
 */
public class Problem142 {

    @Test
    public void test1() {
        ListNode node1 = ListNode.createNode(new int[] { 3, 2, 0, -4 }, 1);
        Assert.assertEquals(node1.next, detectCycle(node1));

        ListNode node2 = ListNode.createNode(new int[] { 1, 2 }, 0);
        Assert.assertEquals(node2, detectCycle(node2));

        ListNode node3 = ListNode.createNode(new int[] { 1 }, -1);
        Assert.assertEquals(null, detectCycle(node3));
    }

    /**
     * <pre>
     *     方法一: 遍历, 保存每一个node, 第一个重复的就是答案.
     *     时间复杂度: O(N)  所有的节点只会遍历一次.
     *     空间复杂度: O(N)
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (nodes.contains(node)) {
                return node;
            } else {
                nodes.add(node);
            }
            node = node.next;
        }
        return null;
    }

    /**
     * <pre>
     *     方法二: Floyd算法.
     *     详解参考公众号: 算法与数据结构 2020.02.05
     *     当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        // 找出相遇点.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        // 头指针、相遇点每次一步，相遇点即环入口.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    /**
     * <pre>
     *     快慢指针判断是否有环.
     *
     * </pre>
     * 
     * @param head
     * @return
     */
    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

}
