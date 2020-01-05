package cn.leetcode.problem901_1000.problem911_920;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     单词子集
 *     我们给出两个单词数组 A 和 B。每个单词都是一串小写字母。
 现在，如果 b 中的每个字母都出现在 a 中，包括重复出现的字母，那么称单词 b 是单词 a 的子集。 例如，“wrr” 是 “warrior” 的子集，但不是 “world” 的子集。
 如果对 B 中的每一个单词 b，b 都是 a 的子集，那么我们称 A 中的单词 a 是通用的。
 你可以按任意顺序以列表形式返回 A 中所有的通用单词

 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 输出：["facebook","google","leetcode"]

 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 输出：["apple","google","leetcode"]

 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 输出：["facebook","google"]

 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 输出：["google","leetcode"]

 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 输出：["facebook","leetcode"]

 提示：

 1 <= A.length, B.length <= 10000
 1 <= A[i].length, B[i].length <= 10
 A[i] 和 B[i] 只由小写字母组成。
 A[i] 中所有的单词都是独一无二的，也就是说不存在 i != j 使得 A[i] == A[j]
 * </pre>
 * 
 * Created by leslie on 2019/12/28.
 */
public class Problem916 {

    @Test
    public void test1() {
        Assert.assertEquals(new ArrayList<String>(Arrays.asList(new String[] { "facebook", "google", "leetcode" })),
                            wordSubsets3(new String[] { "amazon", "apple", "facebook", "google", "leetcode" },
                                         new String[] { "e", "o" }));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList(new String[] { "apple", "google", "leetcode" })),
                            wordSubsets3(new String[] { "amazon", "apple", "facebook", "google", "leetcode" },
                                         new String[] { "l", "e" }));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList(new String[] { "facebook", "google" })),
                            wordSubsets3(new String[] { "amazon", "apple", "facebook", "google", "leetcode" },
                                         new String[] { "e", "oo" }));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList(new String[] { "google", "leetcode" })),
                            wordSubsets3(new String[] { "amazon", "apple", "facebook", "google", "leetcode" },
                                         new String[] { "lo", "eo" }));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList(new String[] { "facebook", "leetcode" })),
                            wordSubsets3(new String[] { "amazon", "apple", "facebook", "google", "leetcode" },
                                         new String[] { "ec", "oc", "ceo" }));
    }

    /**
     * <pre>
     *     方法一: 全排列，可以从字符出现的频率考虑.
     *     时间复杂度: O(n^3)  超出时间限制.
     * </pre>
     * 
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> result = new ArrayList<>();
        boolean validaA;
        for (String a : A) {
            validaA = true;
            int[] f = new int[26];
            for (char c : a.toCharArray()) {
                f[c - 'a'] += 1;
            }
            for (String b : B) {
                int[] g = Arrays.copyOf(f, f.length);
                for (char d : b.toCharArray()) {
                    if (g[d - 'a'] == 0) {
                        validaA = false;
                        break;
                    }
                    g[d - 'a'] -= 1;
                }
                if (!validaA) {
                    break;
                }
            }
            if (validaA) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * <pre>
     *     方法二: 使用字符频率，先将B合并成一个.然后比较.
     * </pre>
     * 
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets2(String[] A, String[] B) {
        // 合并B
        int[] bMax = new int[26];
        for (String b : B) {
            int[] currB = new int[26];
            for (char c : b.toCharArray()) {
                currB[c - 'a'] += 1;
            }
            for (int i = 0; i < 26; i++) {
                // 只有在有该字符时，才进行判断。否则性能差好多.
                if (currB[i] > 0) {
                    bMax[i] = Math.max(bMax[i], currB[i]);
                }
            }
        }

        boolean validA;
        List<String> ans = new ArrayList<>();
        for (String a : A) {
            validA = true;
            int[] currA = new int[26];
            for (char c : a.toCharArray()) {
                currA[c - 'a'] += 1;
            }
            for (int i = 0; i < 26; i++) {
                if (currA[i] < bMax[i]) {
                    validA = false;
                    break;
                }
            }
            if (validA) {
                ans.add(a);
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     方法三: 方法二的优化.
     * </pre>
     * 
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets3(String[] A, String[] B) {
        // 合并B
        int[] bMax = new int[26];
        for (String b : B) {
            int[] currB = new int[26];
            for (char c : b.toCharArray()) {
                currB[c - 'a'] += 1;
            }
            for (int i = 0; i < 26; i++) {
                if (currB[i] > 0) {
                    bMax[i] = Math.max(bMax[i], currB[i]);
                }
            }
        }

        List<String> ans = new ArrayList<>();
        // 使用标签。 两层循环，内层直接跳出。可以减少一个标志位.
        traversalA: for (String a : A) {
            int[] currA = new int[26];
            for (char c : a.toCharArray()) {
                currA[c - 'a'] += 1;
            }
            for (int i = 0; i < 26; i++) {
                if (currA[i] < bMax[i]) {
                    continue traversalA;
                }
            }
            ans.add(a);
        }
        return ans;
    }
}
