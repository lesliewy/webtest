package cn.leetcode.problem1301_1400.problem1361_1370;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     上升下降字符串
 *     给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 重复步骤 2 ，直到你没法从 s 中选择字符。
 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 重复步骤 5 ，直到你没法从 s 中选择字符。
 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 请你返回将 s 中字符重新排序后的 结果字符串 。

 示例 1：
 输入：s = "aaaabbbbcccc"
 输出："abccbaabccba"
 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"

 示例 2：
 输入：s = "rat"
 输出："art"
 解释：单词 "rat" 在上述算法重排序以后变成 "art"

 示例 3：
 输入：s = "leetcode"
 输出："cdelotee"

 示例 4：
 输入：s = "ggggggg"
 输出："ggggggg"

 示例 5：
 输入：s = "spo"
 输出："ops"

 提示：
 1 <= s.length <= 500
 s 只包含小写英文字母。
 * </pre>
 * 
 * Created by leslie on 2021/2/13.
 */
public class Problem1370 {

    @Test
    public void testxxx() {
        Assert.assertEquals("abccbaabccba", sortString("aaaabbbbcccc"));
        Assert.assertEquals("art", sortString("rat"));
        Assert.assertEquals("cdelotee", sortString("leetcode"));
        Assert.assertEquals("ggggggg", sortString("ggggggg"));
        Assert.assertEquals("ops", sortString("spo"));
    }

    /**
     * <pre>
     *     常规思路.  先对字符串排序, 然后遍历找出上升串和下降串，用boolean[]记录访问过的元素.
     * </pre>
     * 
     * @param s
     * @return
     */
    public String sortString(String s) {
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        boolean[] visit = new boolean[ca.length];
        StringBuilder ansBuilder = new StringBuilder("");
        char pre, preDesc;
        while (ansBuilder.toString().length() < ca.length) {
            StringBuilder descSb = new StringBuilder();
            pre = '0';
            preDesc = '0';
            for (int i = 0; i < ca.length; i++) {
                if (visit[i]) {
                    continue;
                }
                if (ca[i] != pre) {
                    ansBuilder.append(ca[i]);
                    visit[i] = true;
                } else if (ca[i] != preDesc) {
                    descSb.append(ca[i]);
                    preDesc = ca[i];
                    visit[i] = true;
                }
                pre = ca[i];
            }
            ansBuilder.append(descSb.reverse());
        }
        return ansBuilder.toString();
    }

    /**
     * <pre>
     *     桶排序:  值域明确且不大的排序（26个字母）
     *     两个for分别遍历正向、反向遍历.
     * </pre>
     * 
     * @param s
     * @return
     */
    public String sortString2(String s) {
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer ret = new StringBuffer();
        while (ret.length() < s.length()) {
            // 上升串.
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            // 下降串.
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return ret.toString();
    }

    /**
     * <pre>
     *   桶排序.
     *   使用flag标识来从左到右，从右到左反复遍历.
     * </pre>
     * 
     * @param s
     * @return
     */
    public String sortString3(String s) {
        int[] hash = new int[27];
        for (char c : s.toCharArray()) {
            hash[c - 'a']++;
        }
        int count = s.length(), i = 0, flag = 1;
        StringBuilder ans = new StringBuilder();
        while (count > 0) {
            if (hash[i] > 0) {
                char tmp = (char) (i + 'a');
                ans.append(tmp);
                count--;
                hash[i]--;
            }
            i += flag;
            if (i == 26 || i == -1) {
                flag *= -1;
                i += flag;
            }
        }
        return ans.toString();
    }

}
