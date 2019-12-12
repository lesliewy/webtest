package cn.leetcode.problem401_500.problem451_460;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     重复的子字符串.
 *     给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *     输入: "abab" 输出: True    解释: 可由子字符串 "ab" 重复两次构成。
 *     输入: "aba" 输出: False
 *     输入: "abcabcabcabc" 输出: True    解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * </pre>
 * 
 * Created by leslie on 2019/12/4.
 */
public class Problem459 {

    @Test
    public void test1() {
        Assert.assertTrue(repeatedSubstringPattern("abab"));
        Assert.assertFalse(repeatedSubstringPattern("aba"));
        Assert.assertTrue(repeatedSubstringPattern("abcabcabcabc"));
    }

    /**
     * <pre>
     *     方法一: 穷举. 能整除的数都作为分割子字符串的长度.
     * </pre>
     * 
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len <= 1) {
            return false;
        }
        int i = 1;
        while (i <= len / 2) {
            if (len % i == 0) {
                String sub = s.substring(0, i);
                int j = 0;
                for (j = 0; j < len; j += i) {
                    String subTemp = s.substring(j, j + i);
                    if (!sub.equals(subTemp)) {
                        break;
                    }
                }
                if (j >= len) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }
}
