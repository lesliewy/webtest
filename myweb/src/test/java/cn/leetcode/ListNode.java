package cn.leetcode;

/**
 * Created by leslie on 2019/11/26.
 */
public class ListNode {

    public int      val;
    public ListNode next;

    public ListNode(int x){
        val = x;
    }

    /**
     * <p>
     * 创建链表
     * </p>
     * <p>
     * pos用来创建循环链表. 2: 尾节点指向第3个节点(从1开始); -1: 没有环.
     * </p>
     * 
     * @param vals
     * @param pos
     * @return
     */
    public static ListNode createNode(int[] vals, int pos) {
        ListNode result = null;
        ListNode pre = null;
        boolean first = true;
        int index = 0;
        ListNode curr = null;
        ListNode cycle = null;
        for (int i : vals) {
            curr = new ListNode(i);
            if (first) {
                result = curr;
            } else {
                pre.next = curr;
            }
            first = false;
            pre = curr;
            if (index++ == pos) {
                cycle = curr;
            }
        }
        curr.next = cycle;
        return result;
    }

}
