package cn.leetcode.problem601_700.problem641_650;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     回文子串
 *     给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *     具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 *     输入: "abc"
 输出: 3
 解释: 三个回文子串: "a", "b", "c".

 输入: "aaa"
 输出: 6
 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa"

 输入的字符串长度不会超过1000。
 * </pre>
 * 
 * Created by leslie on 2020/1/10.
 */
public class Problem647 {

    @Test
    public void testxxx() {
        Assert.assertEquals(3, countSubstrings3("abc"));
        Assert.assertEquals(6, countSubstrings3("aaa"));
    }

    /**
     * <pre>
     *     方法一: 穷举.  两层循环遍历所有子字符串.
     * </pre>
     * 
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindromeStr2(s.substring(i, j + 1))) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * <pre>
     * 方法一的优化
     * </pre>
     * 
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int len = s.length();
        int ans = 0;
        // 只有当当前字符和首字符相同时，才可能是回文字符串.
        for (int i = 0; i < len; i++) {
            char first = s.charAt(i);
            for (int j = i; j < len; j++) {
                if (first == s.charAt(j)) {
                    if (isPalindromeStr2(s.substring(i, j + 1))) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     方法二: 动态数组. dp[i][j] 标识从位置i到位置j的字符是否是回文字符串. dp[]是一个bolean[].
     *          dp[i][j] = dp[i+][j-1] && s[i] == s[j]
     *          不去直接判断是否回文
     *      时间复杂度: O(n^2)
     *
     * </pre>
     * 
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int ans = 0;
        // 两层循环。
        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(j) == s.charAt(i) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     回文数判断方法一: 逆序字符串是否相同.
     *     时间复杂度: O(N)
     * </pre>
     * 
     * @param s
     * @return
     */
    private boolean isPalindromeStr(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    /**
     * <pre>
     *     回文数判断方法二: 首字符和尾字符比较，第二个和倒数第二个比较....
     *     时间复杂度: O(N/2)
     * </pre>
     * 
     * @param s
     * @return
     */
    private boolean isPalindromeStr2(String s) {
        int len = s.length();
        int i = -1, j = len;
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            continue;
        }
        return true;
    }
}
