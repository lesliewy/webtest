package cn.leetcode.problem1_100.problem1_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *  无重复字符的最长子串.
 *  输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * </pre>
 * 
 * Created by leslie on 2019/11/15.
 */
public class Problem3 {

    @Test
    public void test1() {
        Assert.assertEquals(3, lengthOfLongestSubstring2b("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring2b("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring2b("pwwkew"));
        Assert.assertEquals(1, lengthOfLongestSubstring2b(" "));
        Assert.assertEquals(4, lengthOfLongestSubstring2b("jbpnbwwd"));
        Assert.assertEquals(2, lengthOfLongestSubstring2b("au"));
        Assert.assertEquals(5, lengthOfLongestSubstring2b("qrsvbspk"));
    }

    /**
     * <pre>
     *   方法一: 暴力破解.
     *   O(n^2) 使用hashset存储子串, O(1) 判断是否存在.
     * </pre>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        int charArrayLength = charArray.length;
        Set<Character> passedChars = new HashSet<>();
        int maxSubSequence = 0;
        // 两层循环可以遍历所有子串.
        for (int i = 0; i < charArrayLength; i++) {
            for (int j = i; j < charArrayLength; j++) {
                char c = charArray[j];
                // 只要包含了，清空hashset. 该子串就结束了。
                if (passedChars.contains(c)) {
                    if (passedChars.size() > maxSubSequence) {
                        maxSubSequence = passedChars.size();
                    }
                    passedChars.clear();
                    break;
                } else {
                    passedChars.add(c);
                }
            }
        }
        return Math.max(passedChars.size(), maxSubSequence);
    }

    /**
     * <pre>
     *    方法二: 滑动窗口.
     *    使用hashset保存窗口字符. 不重复时依次向前滑动, 发现有重复字符，窗口的尾部向前移动一格, 前部不动, 再次判断是否有重复.
     * </pre>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 1) {
            return s == null ? 0 : s.length();
        }
        int len = s.length();

        Set<Character> charSet = new HashSet<>(s.length());
        int i = 0, j = 0, maxLength = 0;
        // 变长滑动窗口: 使用两个定位器.
        while (i < len && j < len) {
            Character before = s.charAt(j);
            // 尾部往前滑动一个字符.
            if (charSet.contains(before)) {
                charSet.remove(s.charAt(i++));
            } else {
                charSet.add(before);
                j++;
                maxLength = Math.max(maxLength, j - i);
            }
        }

        return maxLength;
    }

    public int lengthOfLongestSubstring2a(String s) {
        int left = 0, right = 0;
        Set<Character> window = new HashSet<>();
        int ans = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            if (window.contains(c)) {
                Character leftChar = s.charAt(left);
                window.remove(leftChar);
                left++;
            } else {
                right++;
                window.add(c);
                ans = Math.max(ans, window.size());
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring2b(String s) {
        int left = 0, right = 0, windowSize = 0, ans = 0;
        // 使用boolean[]判断字符是否存在.
        boolean[] existed = new boolean[256];
        while (right < s.length()) {
            char c = s.charAt(right);
            if (existed[c]) {
                char leftChar = s.charAt(left);
                existed[leftChar] = false;
                left++;
                windowSize--;
            } else {
                right++;
                existed[c] = true;
                windowSize++;
                ans = Math.max(ans, windowSize);
            }
        }
        return ans;
    }

    /**
     * @todo 方法三: 优化的滑动窗口.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() <= 1) {
            return s == null ? 0 : s.length();
        }
        int len = s.length();

        Map<Character, Integer> charMap = new HashMap<>(s.length());
        int i = 0, j = 0, maxLength = 0;
        while (i < len && j < len) {
            Character before = s.charAt(j);
            Integer existedIndex = charMap.get(before);
            if (existedIndex != null) {
                charMap.put(before, existedIndex);
                i = existedIndex + 1;
            } else {
                charMap.put(before, j);
                j++;
                maxLength = Math.max(maxLength, j - i);
            }
        }

        return maxLength;

    }

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0; // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0).intValue() + 1);
            // 判断左侧窗口是否要收缩
            while (window.get(c).intValue() > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(c, window.get(c).intValue() - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }

}
