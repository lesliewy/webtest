package cn.leetcode.problem301_400.problem391_400;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     392. 判断子序列
 *     给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 示例 1:
 s = "abc", t = "ahbgdc"
 返回 true.

 示例 2:
 s = "axc", t = "ahbgdc"
 返回 false.

 后续挑战 :
 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * </pre>
 * 
 * Created by leslie on 2020/7/2.
 */
public class Problem392 {

    @Test
    public void testxxx() {
        Assert.assertTrue(isSubsequence2a("abc", "ahbgdc"));
        Assert.assertFalse(isSubsequence2a("axc", "ahbgdc"));
        Assert.assertFalse(isSubsequence2a("aaaaaa", "bbaaaa"));
    }

    /**
     * <pre>
     *     方法一: 双指针.
     *     复杂度应该是O(s+t), 因为每个字符只需要遍历一次。
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence1(String s, String t) {
        int i = 0, j = 0;
        int sizeOfS = s.toCharArray().length;
        int sizeOfT = t.toCharArray().length;
        for (char c1 : s.toCharArray()) {
            for (int k = j; k < sizeOfT; k++) {
                j = k + 1;
                char c2 = t.charAt(k);
                if (c1 == c2) {
                    i++;
                    break;
                }
            }
            // s 未遍历完，但是t已经遍历完.
            if (i < sizeOfS && j >= sizeOfT) {
                return false;
            }
        }
        return i == sizeOfS;
    }

    /**
     * <pre>
     *     方法一优化.
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence1a(String s, String t) {
        int i = 0, j = 0;
        int sizeOfS = s.toCharArray().length;
        int sizeOfT = t.toCharArray().length;
        for (char c1 : s.toCharArray()) {
            // 方法一中k的作用和j一样，使用while，减少一个变量.
            while (j < sizeOfT) {
                char c2 = t.charAt(j);
                j++;
                if (c1 == c2) {
                    i++;
                    break;
                }
                // s 未遍历完，但是t已经遍历完.
                if (i < sizeOfS && j >= sizeOfT) {
                    return false;
                }
            }
        }
        return i == sizeOfS;
    }

    /**
     * <pre>
     *     方法二: 动态规划.
     *     因为可以删除t中字符，不连续的，所以考虑将dp数组设成二维 dp[i][j],  i为s的下标, j为t的下标.  值为true or false，代表 0-i 是否是 0-j 的子序列.
     *     比较s, t中的每个字符c1, c2:
     *        c1 == c2: dp[i][lenT] = true   dp[i][j] - dp[i][lenT] 此时应该都是true.
     *        c1 != c2: dp[i][j] = dp[i][j-1]
     *     返回dp[s][t]
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int lenS = s.toCharArray().length, lenT = t.toCharArray().length, i = 0, j = 0;
        if (lenS == 0) {
            return true;
        }
        if (lenT == 0) {
            return false;
        }
        boolean[][] dp = new boolean[lenS][lenT];
        while (i < lenS) {
            char c1 = s.charAt(i);
            while (j < lenT) {
                char c2 = t.charAt(j);
                if (c1 == c2) {
                    dp[i][lenT - 1] = true;
                    j++;
                    break;
                } else {
                    if (j != 0) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    j++;
                }
            }
            i++;
        }
        return dp[lenS - 1][lenT - 1];
    }

    /**
     * <pre>
     *     方法二优化。
     *     不需要一个二维数组来维持状态. 可以使用一维数组dp[i]: 表示s 中0-i是否是整个t的子序列。
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2a(String s, String t) {
        int lenS = s.toCharArray().length, lenT = t.toCharArray().length, i = 0, j = 0;
        if (lenS == 0) {
            return true;
        }
        if (lenT == 0) {
            return false;
        }
        // 只需要一个一维数组。
        boolean dp[] = new boolean[lenS];
        while (i < lenS) {
            char c1 = s.charAt(i);
            while (j < lenT) {
                char c2 = t.charAt(j);
                if (c1 == c2) {
                    // 显然，还可以再优化，不需要dp数组。因为dp数组内部元素之间没有关系.
                    dp[i] = true;
                    j++;
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
        return dp[lenS - 1];
    }

}
