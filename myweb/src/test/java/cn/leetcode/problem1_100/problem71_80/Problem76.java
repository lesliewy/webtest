package cn.leetcode.problem1_100.problem71_80;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *    最小覆盖子串
 *    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

 示例 1：
 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"

 示例 2：
 输入：s = "a", t = "a"
 输出："a"

 提示：
 1 <= s.length, t.length <= 105
 s 和 t 由英文字母组成
  
 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * </pre>
 * 
 * Created by leslie on 2020/11/9.
 */
public class Problem76 {

    @Test
    public void test1() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * <pre>
     *     方法一: 滑动窗口.
     *      1, 窗口右边前移扩大窗口，直至覆盖了字符串t: 记录此时字符串start, len;
     *      2, 窗口左边前移收缩窗口, 直至没有覆盖字符串t.
     *      3, 重复1.
     *
     *     窗口收缩条件:
     *        valid == need.size() 相当于变长窗口，等到全覆盖了再移动.
     *        (right - left) >= need.size() 相当于定长窗口.  定长窗口更多的是使用 for(int i = 0; i <= s2.length() - s1.length; i++)
     *
     *     字符串覆盖:
     *        bac 覆盖了 abc,   bdac 同样覆盖了 abc;
     *        排列问题是覆盖的特例;
     *        覆盖问题使用这里的valid, need.size() 比较的方式.
     *
     *     字符串全排列:
     *        bac 、abc， 属于全排列,   bdac, abc 不属于;
     *        全排列问题可以转换为字符出现频率问题. 使用数组 int[26],  a['e' - 'a'] = 3;
     *        比较是否是全排列: matches(int[] arr1, int[] arr1)
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.putIfAbsent(c, 0);
            need.put(c, need.get(c).intValue() + 1);
        }

        int left = 0, right = 0;
        // 当valid == need.size()时说明窗口满足了要求.
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新, 只更新需要的那部分字符.
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0).intValue() + 1);
                if (window.get(c).intValue() == need.get(c).intValue()) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩 valid == need.size() 表示覆盖了目标值.
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 窗口左边沿前移.
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).intValue() == need.get(d).intValue()) {
                        valid--;
                    }
                    window.put(d, window.get(d).intValue() - 1);
                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
