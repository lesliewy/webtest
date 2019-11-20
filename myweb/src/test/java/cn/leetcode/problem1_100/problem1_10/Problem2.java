package cn.leetcode.problem1_100.problem1_10;

import org.junit.Test;

/**
 * Created by leslie on 2019/11/14.
 */
public class Problem2 {

    @Test
    public void testBackwardNum() {
        ListNode t1 = createNode(new int[] { 1, 2, 3 });

        t1 = createNode(new int[] { 1, 2, 3, 8, 9, 1 });

    }

    @Test
    public void test1() {
        ListNode t1 = createNode(new int[] { 3, 5, 9 });
        ListNode t2 = createNode(new int[] { 3, 5, 9 });
        System.out.println(forwardString(addTwoNumbers(t1, t2)));

        t1 = createNode(new int[] { 9 });
        t2 = createNode(new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9 });
        System.out.println(forwardString(addTwoNumbers(t1, t2)));
    }

    public String forwardString(ListNode t) {
        StringBuilder sb = new StringBuilder(String.valueOf(t.val));
        while (t.next != null) {
            sb.append(t.next.val);
            t.next = t.next.next;
        }
        return sb.toString();
    }

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

    public ListNode genNodeFromStr(String a) {
        String backwardStr = reverse(a);
        // List<Integer> integerList = Arrays.asList(backwardStr).stream().map(c ->
        // Integer.valueOf(c)).collect(Collectors.toList());
        int length = backwardStr.length();
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.valueOf(backwardStr.substring(i, i + 1));
        }
        return createNode(result);
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
