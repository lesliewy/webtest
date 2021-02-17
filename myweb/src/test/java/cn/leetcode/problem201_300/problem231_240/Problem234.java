package cn.leetcode.problem201_300.problem231_240;

import cn.leetcode.util.ListNode;

/**
 * <pre>
 *     回文链表
 *     请判断一个链表是否为回文链表。

 示例 1:
 输入: 1->2
 输出: false

 示例 2:
 输入: 1->2->2->1
 输出: true
 进阶：
 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * </pre>
 * 
 * Created by leslie on 2020/11/17.
 */
public class Problem234 {

    // 左侧指针
    ListNode left;

    /**
     * <pre>
     *     方法一:  模仿左右指针判断回文数
     *     链表也存在前序、后序.
     *     利用递归的栈，拿出来是反的原理.
     *     时间复杂度: O(N)
     *     空间复杂度: O(N)
     * </pre>
     * 
     * @param head
     * @return
     */
    boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    /**
     * <pre>
     *     方法二: 快慢指针找到中点, 然后反转其中一半.
     *     时间复杂度: O(N)
     *     空间复杂度: O(1)
     * </pre>
     * 
     * @param head
     * @return
     */
    boolean isPalindrome2(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 奇偶判断. slow 指针现在指向链表中点
        if (fast != null) {
            slow = slow.next;
        }
        ListNode left = head;
        ListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
