package cn.leetcode.problem1_100.problem1_10;

import static cn.leetcode.ListNode.createNode;

import org.junit.Test;

import cn.leetcode.ListNode;

/**
 * Created by leslie on 2019/11/14.
 */
public class Problem2 {

    @Test
    public void testBackwardNum() {
        ListNode t1 = createNode(new int[] { 1, 2, 3 }, -1);

        t1 = createNode(new int[] { 1, 2, 3, 8, 9, 1 }, -1);

    }

    @Test
    public void test1() {
        ListNode t1 = createNode(new int[] { 3, 5, 9 }, -1);
        ListNode t2 = createNode(new int[] { 3, 5, 9 }, -1);
        System.out.println(forwardString(addTwoNumbers3(t1, t2)));

        t1 = createNode(new int[] { 9 }, -1);
        t2 = createNode(new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9 }, -1);
        System.out.println(forwardString(addTwoNumbers3(t1, t2)));
    }

    public String forwardString(ListNode t) {
        StringBuilder sb = new StringBuilder(String.valueOf(t.val));
        while (t.next != null) {
            sb.append(t.next.val);
            t.next = t.next.next;
        }
        return sb.toString();
    }

    /**
     * 方法一: 将每个链表中的数字恢复，然后使用大数相加, 最后再将结果转成链表.
     * 
     * @param t1
     * @param t2
     * @return
     */
    public ListNode addTwoNumbers(ListNode t1, ListNode t2) {
        String n = getSumStr(t1, t2);

        return genNodeFromStr(n);
    }

    public String getSumStr(ListNode t1, ListNode t2) {
        // 需要大数相加， leetcode 中不能使用 BigDecimal.
        /*
         * String backwardStr1 = new StringBuilder(forwardString(t1)).reverse().toString(); String backwardStr2 = new
         * StringBuilder(forwardString(t2)).reverse().toString(); BigDecimal b1 = new BigDecimal(backwardStr1);
         * BigDecimal b2 = new BigDecimal(backwardStr2); String result = new
         * StringBuilder(b1.add(b2).toPlainString()).toString();
         */

        String s1 = reverse(forwardString(t1));
        String s2 = reverse(forwardString(t2));
        String result = add(s1, s2);
        return result;

    }

    /**
     * 大数相加 思路： 1.反转两个字符串，便于从低位到高位相加和最高位的进位导致和的位数增加； 2.对齐两个字符串，即短字符串的高位用‘0’补齐，便于后面的相加； 3.把两个正整数相加，一位一位的加并加上进位；
     * 4.最高位有进位则补上。
     * 
     * @param n1
     * @param n2
     * @return
     */
    public static String add(String n1, String n2) {

        StringBuffer result = new StringBuffer();

        // 反转字符串
        n1 = new StringBuffer(n1).reverse().toString();
        n2 = new StringBuffer(n2).reverse().toString();

        int len1 = n1.length();
        int len2 = n2.length();
        int maxLen = len1 > len2 ? len1 : len2;

        int c = 0;// 进位
        if (len1 < len2) {
            for (int i = len1; i < len2; i++) {
                n1 += "0";
            }
        } else if (len1 > len2) {
            for (int i = len2; i < len1; i++) {
                n2 += "0";
            }
        }

        // System.out.println(n1);
        // System.out.println(n2);

        for (int i = 0; i < maxLen; i++) {
            int nSum = Integer.parseInt(n1.charAt(i) + "") + Integer.parseInt(n2.charAt(i) + "") + c;
            int ap = nSum % 10;
            result.append(ap);
            c = nSum / 10;
        }
        // 最高位进位
        if (c > 0) {
            result.append(c);
        }

        return result.reverse().toString();

    }

    public String reverse(String str) {
        return str == null ? null : (new StringBuilder(str)).reverse().toString();
    }

    private ListNode genNodeFromStr(String a) {
        String backwardStr = reverse(a);
        // List<Integer> integerList = Arrays.asList(backwardStr).stream().map(c ->
        // Integer.valueOf(c)).collect(Collectors.toList());
        int length = backwardStr.length();
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.valueOf(backwardStr.substring(i, i + 1));
        }
        return createNode(result, -1);
    }

    /**
     * 方法二: 直接对链表中的数进行大数相加, 稍微改造下下面的大数相加算法.
     *
     * @param t1
     * @param t2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode t1, ListNode t2) {
        int len1 = getListSize(t1);
        int len2 = getListSize(t2);
        int maxLen = len1 > len2 ? len1 : len2;
        // 进位
        int c = 0;
        int n1, n2;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < maxLen; i++) {
            n1 = t1 == null ? 0 : t1.val;
            n2 = t2 == null ? 0 : t2.val;
            int nSum = n1 + n2 + c;
            int ap = nSum % 10;
            sb.append(ap);
            c = nSum / 10;

            t1 = t1 == null ? null : t1.next;
            t2 = t2 == null ? null : t2.next;
        }

        // 最高位进位
        if (c > 0) {
            sb.append(c);
        }
        return createNode2(sb.toString());
    }

    /**
     * 方法二优化版: 直接在一次循环中构造list; 省掉通过字符串的转换过程;
     * 
     * @param t1
     * @param t2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode t1, ListNode t2) {
        // 进位
        int c = 0;
        int n1, n2;
        int index = 0;
        ListNode head = null, pre = null;
        while (true) {
            if (t1 == null && t2 == null) {
                break;
            }
            n1 = t1 == null ? 0 : t1.val;
            n2 = t2 == null ? 0 : t2.val;
            int nSum = n1 + n2 + c;
            int ap = nSum % 10;
            c = nSum / 10;

            ListNode t = new ListNode(ap);
            if (index == 0) {
                head = t;
                pre = t;
            } else {
                pre.next = t;
                pre = t;
            }

            t1 = t1 == null ? null : t1.next;
            t2 = t2 == null ? null : t2.next;
            index++;
        }

        // 最高位进位
        if (c > 0) {
            ListNode t = new ListNode(c);
            pre.next = t;
        }
        return head;
    }

    private int getListSize(ListNode t) {
        if (t == null) {
            return 0;
        }
        int result = 1;
        while ((t = t.next) != null) {
            result++;
        }
        return result;
    }

    private ListNode createNode2(String str) {
        ListNode head = null, pre = null;
        for (int i = 0; i < str.length(); i++) {
            ListNode t = new ListNode(Integer.parseInt(str.charAt(i) + ""));
            if (i == 0) {
                head = t;
                pre = head;
            } else {
                pre.next = t;
                pre = t;
            }
        }
        return head;
    }

}
