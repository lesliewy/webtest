package cn.leetcode.problem101_200.problem151_160;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 输入: "the sky is blue" 输出: "blue is sky the"
 * 输入: "  hello world!  " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 输入: "a good   example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * </pre>
 * 
 * Created by leslie on 2019/11/28.
 */
public class Problem151 {

    @Test
    public void test1() {
        Assert.assertEquals("blue is sky the", reverseWords("the sky is blue"));
        Assert.assertEquals("world! hello", reverseWords("  hello world!  "));
        Assert.assertEquals("example good a", reverseWords("a good   example"));
    }

    /**
     * 方法一: 常规方法. 先切割，再倒序.
     * 
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s.trim().equals("")) {
            return "";
        }
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder("");
        int i = words.length;
        while (i-- > 0) {
            if (words[i].trim().length() != 0) {
                sb.append(words[i].trim()).append(" ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

}
