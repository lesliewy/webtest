package cn.leetcode.problem401_500.problem431_440;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     找到字符串中所有字母异位词
 *     给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

 说明：
 字母异位词指字母相同，但排列不同的字符串。
 不考虑答案输出的顺序。

 示例 1:
 输入:
 s: "cbaebabacd" p: "abc"
 输出:
 [0, 6]

 解释:
 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

  示例 2:
 输入:
 s: "abab" p: "ab"
 输出:
 [0, 1, 2]

 解释:
 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * </pre>
 * 
 * Created by leslie on 2020/11/11.
 */
public class Problem438 {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new Integer[] { 0, 6 },
                                 (Integer[]) findAnagrams3("cbaebabacd", "abc").toArray(new Integer[2]));
        Assert.assertArrayEquals(new Integer[] { 0, 1, 2 },
                                 (Integer[]) findAnagrams3("abab", "ab").toArray(new Integer[3]));
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0).intValue() + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>(); // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0).intValue() + 1);
                if (window.get(c).intValue() == need.get(c).intValue()) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= p.length()) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
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
        return res;
    }

    /**
     * <pre>
     *     方法一优化: 使用数组替换hash表.
     *     最快.
     * </pre>
     * 
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] needs = new int[26]; // 由于都是小写字母，因此直接用26个长度的数组代替原来的HashMap
        int[] window = new int[26];
        int left = 0, right = 0, total = p.length(); // 用total检测窗口中是否已经涵盖了p中的字符
        for (char ch : p.toCharArray()) {
            needs[ch - 'a']++;
        }
        while (right < s.length()) {
            char chr = s.charAt(right);
            if (needs[chr - 'a'] > 0) {
                window[chr - 'a']++;
                if (window[chr - 'a'] <= needs[chr - 'a']) {
                    total--;
                }
            }
            while (total == 0) {
                if (right - left + 1 == p.length()) {
                    res.add(left);
                }
                char chl = s.charAt(left);
                if (needs[chl - 'a'] > 0) {
                    window[chl - 'a']--;
                    if (window[chl - 'a'] < needs[chl - 'a']) {
                        total++;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }

    /**
     * <pre>
     *     定长窗口滑动.
     * </pre>
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams3(String s, String p) {
        if (p.length() > s.length()) {
            return new ArrayList<>();
        }
        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
            windowFreq[s.charAt(i) - 'a']++;
        }
        // 定长滑动窗口: 使用一个定位器.
        for (int i = 0; i < s.length() - p.length(); i++) {
            if (matches(pFreq, windowFreq)) {
                ans.add(i);
            }
            // 变化窗口的最后一个字符
            windowFreq[s.charAt(i + p.length()) - 'a']++;
            // 变化窗口的第一个字符.
            windowFreq[s.charAt(i) - 'a']--;
        }
        // 最后一个.
        if(matches(pFreq, windowFreq)){
            ans.add(s.length()-p.length());
        }
        System.out.println(ans);
        return ans;
    }

    private boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }

}
