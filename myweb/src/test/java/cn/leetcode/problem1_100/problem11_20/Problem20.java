package cn.leetcode.problem1_100.problem11_20;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 有效的括号
 * </p>
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 有效字符串需满足： 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 * </p>
 * <p>
 * 输入: "()" 输出: true
 * </p>
 * <p>
 * 输入: "()[]{}" 输出: true
 * </p>
 * <p>
 * 输入: "([)]" 输出: false
 * </p>
 * Created by leslie on 2019/11/26.
 */
public class Problem20 {

    @Test
    public void test1() {
        Assert.assertTrue(isValid2("()"));
        Assert.assertTrue(isValid2("()[]{}"));
        Assert.assertFalse(isValid2("(]"));
        Assert.assertFalse(isValid2("([)]"));
        Assert.assertTrue(isValid2("{[]}"));
        Assert.assertFalse(isValid2("["));
    }

    /**
     * 方法一: 栈.
     * 
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.trim().length() <= 0) {
            return true;
        }
        int len = s.length();
        char[] cs = new char[len];
        int top = -1;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                cs[++top] = c;
                continue;
            }
            if (top < 0) {
                return false;
            }
            switch (c) {
                case ')':
                    if (cs[top] != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (cs[top] != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (cs[top] != '{') {
                        return false;
                    }
                    break;
                default:
                    continue;
            }
            top--;
        }
        return top == -1;
    }

    /**
     * 方法一: 代码简化, 但是性能上还是原始的高.
     * <p>
     * 1.使用map简化switch.
     * </p>
     * <p>
     * 2.使用jdk中stack代替自实现.
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if (s == null || s.trim().length() <= 0) {
            return true;
        }
        HashMap<Character, Character> terminators = new HashMap<Character, Character>() {

            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
                continue;
            }
            if (st.isEmpty()) {
                return false;
            }

            Character t = terminators.get(c);
            if (t != null && t.charValue() != st.pop()) {
                return false;
            }
        }
        return st.isEmpty();
    }
}
