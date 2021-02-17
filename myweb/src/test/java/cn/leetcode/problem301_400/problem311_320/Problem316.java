package cn.leetcode.problem301_400.problem311_320;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     去除重复字母
 *
 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

 示例 1：

 输入：s = "bcabc"
 输出："abc"
 示例 2：

 输入：s = "cbacdcbc"
 输出："acdb"

 提示：
 1 <= s.length <= 104
 s 由小写英文字母组成
 * </pre>
 * 
 * Created by leslie on 2020/10/27.
 */
public class Problem316 {

    @Test
    public void test1() {
        Assert.assertEquals("abc", removeDuplicateLetters("bcabc"));
        Assert.assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
        Assert.assertEquals("bac", removeDuplicateLetters("bcac"));
    }

    /**
     * <pre>
     *    字符去重: boolean inStack[], 最多256个字符.
     *    保持相对顺序: 栈, 可以操作刚插入的顺序.
     *    字典序最小: 计数器数组 int count[], 最多256个, 记录剩余元素中每个字符的个数.
     * </pre>
     * 
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();
        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) continue;

            // 入栈时，先判断已存在元素的字典序，保证小的在下面.
            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }
}
