package cn.leetcode.problem501_600.problem521_530;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

 输入:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 输出:
 "apple"

 输入:
 s = "abpcplea", d = ["a","b","c"]

 输出:
 "a"

 所有输入的字符串只包含小写字母。
 字典的大小不会超过 1000。
 所有输入的字符串长度不会超过 1000。
 * </pre>
 *
 * Created by leslie on 2020/1/18.
 */
public class Problem524 {

    @Test
    public void testxxx() {
        Assert.assertEquals("apple",
                            findLongestWord2("abpcplea",
                                             Arrays.asList(new String[] { "ale", "apple", "monkey", "plea" })));
        Assert.assertEquals("a", findLongestWord2("abpcplea", Arrays.asList(new String[] { "a", "b", "c" })));
    }

    /**
     * <pre>
     *     方法一: 先对字典排序，长度倒序，字符串升序，然后依次判断是否在字符串s中.
     * </pre>
     * 
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        // 先对d排序, 两个维度: 长度倒序, 字符串升序.
        Collections.sort(d, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                } else {
                    return o1.compareTo(o2);
                }

            }
        });
        for (String child : d) {
            if (hasChild(s, child)) {
                return child;
            }
        }
        return "";
    }

    /**
     * <pre>
     *     方法一: 不排序.
     *     时间复杂度: O(N * x)  leetcode 执行时间少于排序版本.
     * </pre>
     * 
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord2(String s, List<String> d) {
        String ans = "";
        for (String child : d) {
            // 继续优化. 先看child
            if (child.length() < ans.length() || (child.length() == ans.length() && child.compareTo(ans) > 0)) {
                continue;
            }
            if (hasChild(s, child)) {
                if (child.length() > ans.length() || (child.length() == ans.length() && child.compareTo(ans) < 0)) {
                    ans = child;
                }
            }
        }
        return ans;
    }

    /**
     * <pre>
     * 判断s能否通过删除字符得到child
     * 时间复杂度: O(N) N为 min(len(s), len(child))
     * </pre>
     * 
     * @param s
     * @param child
     * @return
     */
    private boolean hasChild(String s, String child) {
        int lenOfS = s.length();
        int lenOfChild = child.length();

        int i = 0, j = 0;
        // 两个指针，匹配上都后移.
        while (i < lenOfS && j < lenOfChild) {
            if (s.charAt(i) == child.charAt(j)) {
                i++;
                j++;
                continue;
            }
            i++;
        }
        return j == lenOfChild;
    }
}
