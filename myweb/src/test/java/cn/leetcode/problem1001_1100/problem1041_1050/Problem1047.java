package cn.leetcode.problem1001_1100.problem1041_1050;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     删除字符串中的所有相邻重复项
 *     给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。

 在 S 上反复执行重复项删除操作，直到无法继续删除。

 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

 输入："abbaca"
 输出："ca"
 解释：
 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。

 提示：
 1 <= S.length <= 20000
 S 仅由小写英文字母组成。
 * </pre>
 * 
 * Created by leslie on 2019/12/18.
 */
public class Problem1047 {

    @Test
    public void test1() {
        Assert.assertEquals("ca", removeDuplicates2("abbaca"));
    }

    /**
     * <pre>
     *     方法一: 栈.
     * </pre>
     * 
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        Stack<Character> st = new Stack<>();
        for (char c : S.toCharArray()) {
            if (st.isEmpty() || !st.peek().equals(c)) {
                st.push(c);
            } else if (st.peek().equals(c)) {
                st.pop();
            }
        }

        // 整理结果. 利用 Stack extends Vector
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < st.size(); i++) {
            sb.append(st.get(i));
        }
        return sb.toString();
    }

    /**
     * <pre>
     *     方法二: 使用StringBuilder 代替栈.
     *     时间复杂度: O(N) 但是比栈性能更高.
     * </pre>
     * 
     * @param S
     * @return
     */
    public String removeDuplicates2(String S) {
        StringBuilder sb = new StringBuilder("0");
        for (char c : S.toCharArray()) {
            if (sb.charAt(sb.length() - 1) != c) {
                sb.append(c);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.deleteCharAt(0).toString();
    }

}
