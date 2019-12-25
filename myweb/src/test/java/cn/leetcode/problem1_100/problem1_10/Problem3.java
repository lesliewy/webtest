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
        Assert.assertEquals(3, lengthOfLongestSubstring3("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring3("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring3("pwwkew"));
        Assert.assertEquals(1, lengthOfLongestSubstring3(" "));
        Assert.assertEquals(4, lengthOfLongestSubstring3("jbpnbwwd"));
        Assert.assertEquals(2, lengthOfLongestSubstring3("au"));
        Assert.assertEquals(5, lengthOfLongestSubstring3("qrsvbspk"));
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
                // 只要包含了，清空hashset.
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

}
