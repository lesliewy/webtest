package cn.leetcode.problem501_600.problem561_570;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     字符串的排列
 *     给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。 换句话说，第一个字符串的排列之一是第二个字符串的子串。

 输入: s1 = "ab" s2 = "eidbaooo"
 输出: True
 解释: s2 包含 s1 的排列之一 ("ba").

 输入: s1= "ab" s2 = "eidboaoo"
 输出: False

 注意：
 输入的字符串只包含小写字母
 两个字符串的长度都在 [1, 10,000] 之间
 * </pre>
 * 
 * Created by leslie on 2019/12/13.
 */
public class Problem567 {

    @Test
    public void test1() {
        Assert.assertTrue(checkInclusion4("ab", "eidbaooo"));
        Assert.assertFalse(checkInclusion4("ab", "eidboaoo"));
    }

    boolean flag = false;

    /**
     * <pre>
     *  方法一: 穷举.   穷举出 s1 的所有排列，判断是否在 s2 中.
     *  时间复杂度 O(N!)
     *  空间复杂度 O(N^2)
     * </pre>
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        permute(s1, s2, 0);
        return flag;
    }

    public String swap(String s, int i0, int i1) {
        if (i0 == i1) {
            return s;
        }
        String s1 = s.substring(0, i0);
        String s2 = s.substring(i0 + 1, i1);
        String s3 = s.substring(i1 + 1);
        return s1 + s.charAt(i1) + s2 + s.charAt(i0) + s3;
    }

    void permute(String s1, String s2, int l) {
        if (l == s1.length()) {
            if (s2.indexOf(s1) >= 0) {
                flag = true;
            }
        } else {
            for (int i = l; i < s1.length(); i++) {
                s1 = swap(s1, l, i);
                permute(s1, s2, l + 1);
                s1 = swap(s1, l, i);
            }
        }
    }

    /**
     * <pre>
     *     方法二: 全排列转换为字符频率.
     *     只有当两个字符串包含相同次数的相同字符时，一个字符串才是另一个字符串的排列
     *
     *     时间复杂度: O(l1 + 26*(l2-l1)*l1)
     *     空间复杂度：O(1)O(1)。表包含最多 26 个键值对
     * </pre>
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1map = new int[26];
        // 计算s1中每一个字符出现的频率.
        for (int i = 0; i < s1.length(); i++)
            s1map[s1.charAt(i) - 'a']++;
        // 计算s2中与s1等长的子串的频率，如果与s1map相同，则s1的一个排列是s2的子串.
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            // 计算s2中该子串的字符出现频率.
            for (int j = 0; j < s1.length(); j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个数组是否相等.
     * 
     * @param s1map
     * @param s2map
     * @return
     */
    private boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }

    /**
     * <pre>
     *     方法三: 滑动窗口.
     *
     *     时间复杂度: O(l1 + 26*(l2-l1))
     * </pre>
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        // 定长滑动窗口: 使用一个定位器.
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)) {
                return true;
            }
            // 变化窗口的最后一个字符
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            // 变化窗口的第一个字符.
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    /**
     * @todo 待研究.
     * 
     * <pre>
     *     方法四: 优化的滑动窗口.
     * </pre>
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion4(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i]) {
                count++;
            }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26) return true;
            s2map[r]++;
            if (s2map[r] == s1map[r]) count++;
            else if (s2map[r] == s1map[r] + 1) count--;
            s2map[l]--;
            if (s2map[l] == s1map[l]) count++;
            else if (s2map[l] == s1map[l] - 1) count--;
        }
        return count == 26;
    }
}
