package cn.leetcode.problem401_500.problem401_410;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * <pre>
 *     给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *     注意:
 *        num 的长度小于 10002 且 ≥ k。
 *        num 不会包含任何前导零。
 *
 *      输入: num = "1432219", k = 3
 *      输出: "1219"
 *      解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 *      输入: num = "10200", k = 1
 *      输出: "200"
 *      解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 *      输入: num = "10", k = 2
 *      输出: "0"
 *      解释: 从原数字移除所有的数字，剩余为空就是0。
 * </pre>
 *
 * Created by leslie on 2019/12/2.
 */
public class Problem402 {

    @Test
    public void test1() {
        Assert.assertEquals("1219", removeKdigits3("1432219", 3));
        Assert.assertEquals("200", removeKdigits3("10200", 1));
        Assert.assertEquals("0", removeKdigits3("10", 2));
        Assert.assertEquals("0", removeKdigits3("9", 1));
        Assert.assertEquals("11", removeKdigits3("112", 1));
        Assert.assertEquals("222222222222222222", removeKdigits3("22222222222222222222222222222222222222", 20));
        Assert.assertEquals("0", removeKdigits3("", 0));
    }

    /**
     * <pre>
     *    方法一: 贪心 + 单调栈.
     *    从最高位开始, 如果大于等于栈顶元素则将该元素入栈，如果小于栈顶元素则将栈顶元素出栈.
     *    也就是从高位起移除掉大于后面一位的数.
     * </pre>
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return "0";
        }
        if (k == 0) {
            return num;
        }
        int len = num.length();
        // 这种情况不能通过后面的程序处理，需要提前判断>
        if (len == k) {
            return "0";
        }
        int numOfRemoved = 0;
        StringBuilder sb = new StringBuilder("");
        Stack<Character> s = new Stack<>();
        int idx = 0;
        for (idx = 0; idx < len; idx++) {
            char c = num.charAt(idx);
            // 栈空时，直接入栈.
            if (s.isEmpty()) {
                s.add(c);
                continue;
            }
            char top = s.peek();
            // 大于等于栈顶则入栈;
            if (c >= top) {
                s.add(c);
            } else {
                s.pop();
                if (++numOfRemoved == k) {
                    break;
                }
                // i 复位, 仍然用该元素与栈顶比较.
                idx--;
            }
        }
        /*
         * 剩余元素 = 栈中字符(栈底开始) + 剩余字符. 还有一种情况是移除的元素不够，需要继续从栈顶移除.
         */
        while (!s.isEmpty()) {
            if (numOfRemoved++ < k) {
                s.pop();
                continue;
            }
            sb.insert(0, s.pop());
        }
        sb.append(num.substring(idx));
        if (sb.toString().length() == 0) {
            return "0";
        }
        // 删除前导0
        len = sb.length();
        int numOfLeadingZero = 0;
        for (numOfLeadingZero = 0; numOfLeadingZero < len; numOfLeadingZero++) {
            if (sb.charAt(numOfLeadingZero) != '0') {
                break;
            }
        }
        sb.delete(0, numOfLeadingZero);
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    /**
     * <pre>
     *    方法一的优化.
     * </pre>
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits2(String num, int k) {
        if (num == null || num.length() == 0) {
            return "0";
        }
        int len = num.length();
        int numOfRemoved = 0;
        StringBuilder sb = new StringBuilder("");
        Stack<Character> s = new Stack<>();
        int idx = 0;
        for (idx = 0; idx < len; idx++) {
            char c = num.charAt(idx);

            // 使用循环，代替之前的idx复位, 逻辑更清晰.
            while (k > 0 && !s.isEmpty() && c < s.peek()) {
                s.pop();
                k--;
                numOfRemoved++;
            }
            // 全部入栈，即使已经移除了k个元素.
            s.add(c);
        }
        while (!s.isEmpty()) {
            if (numOfRemoved++ < k) {
                s.pop();
                continue;
            }
            sb.insert(0, s.pop());
        }

        // 删除前导0
        len = sb.length();
        int numOfLeadingZero = 0;
        for (numOfLeadingZero = 0; numOfLeadingZero < len; numOfLeadingZero++) {
            if (sb.charAt(numOfLeadingZero) != '0') {
                break;
            }
        }
        sb.delete(0, numOfLeadingZero);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * <pre>
     *     方法一的继续优化.
     * </pre>
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits3(String num, int k) {
        int len = num.length();
        // 使用StringBuilder代替单调栈. 也可以直接使用String.
        StringBuilder sb = new StringBuilder("");
        int idx = 0;
        for (idx = 0; idx < len; idx++) {
            char c = num.charAt(idx);
            while (k > 0 && sb.length() > 0 && c < sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(c);
        }
        // 如果没有移除完需要继续移除.
        if (k > 0 && sb.length() != 0) {
            sb.delete(sb.length() - k, sb.length());
        }

        // 删除前导0
        len = sb.length();
        int numOfLeadingZero = 0;
        for (numOfLeadingZero = 0; numOfLeadingZero < len; numOfLeadingZero++) {
            if (sb.charAt(numOfLeadingZero) != '0') {
                break;
            }
        }

        sb.delete(0, numOfLeadingZero);
        return sb.length() == 0 ? "0" : sb.toString();
    }

}
