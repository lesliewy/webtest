package cn.leetcode.problem1_100.problem11_20;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     最长公共前缀
 *     编写一个函数来查找字符串数组中的最长公共前缀。
       如果不存在公共前缀，返回空字符串 ""。

 输入: ["flower","flow","flight"]
 输出: "fl"

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。

 说明:

 所有输入只包含小写字母 a-z 。
 * </pre>
 * 
 * Created by leslie on 2019/12/12.
 */
public class Problem14 {

    @Test
    public void test1() {
        Assert.assertEquals("fl", longestCommonPrefix2(new String[] { "flower", "flow", "flight" }));
        Assert.assertEquals("", longestCommonPrefix2(new String[] { "dog", "racecar", "car" }));
    }

    /**
     * <pre>
     *     方法一: 依次判断每个字符串的每个字符, 看是否相同.
     * </pre>
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int i = 0;
        String ans = "", pre, curr = "";
        boolean finish = false;
        while (true) {
            pre = "0";
            for (String str : strs) {
                // 有一个字符串检查完了，就直接退出.
                if (i >= str.length()) {
                    finish = true;
                    break;
                }
                curr = str.substring(i, i + 1);
                // 第一个字符串.
                if ("0".equals(pre)) {
                    pre = curr;
                    continue;
                }
                if (curr.equals(pre)) {
                    continue;
                } else {
                    finish = true;
                    break;
                }
            }
            if (finish) {
                break;
            }
            ans += curr;
            i++;
        }
        return ans;
    }

    /**
     * <pre>
     *     方法二: 先找两个字符串的公共串a1, 再用a1和第三个字符串找公共串a2, 以此类推.
     *     时间复杂度: O(S), S为所有字符串字符个数之和.
     * </pre>
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            // 从后往前找，可以利用indexOf
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

}
