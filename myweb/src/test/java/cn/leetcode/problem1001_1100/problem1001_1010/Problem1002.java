package cn.leetcode.problem1001_1100.problem1001_1010;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     查找常用字符
 *     给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 你可以按任意顺序返回答案。

 示例 1：
 输入：["bella","label","roller"]
 输出：["e","l","l"]

 示例 2：
 输入：["cool","lock","cook"]
 输出：["c","o"]
  

 提示：
 1 <= A.length <= 100
 1 <= A[i].length <= 100
 A[i][j] 是小写字母
 * </pre>
 * 
 * Created by leslie on 2020/6/18.
 */
public class Problem1002 {

    @Test
    public void testxxx() {
        Assert.assertArrayEquals(new String[] { "e", "l", "l" },
                                 commonChars1(new String[] { "bella", "label", "roller" }).toArray());
        Assert.assertArrayEquals(new String[] { "c", "o" },
                                 commonChars1(new String[] { "cool", "lock", "cook" }).toArray());
    }

    /**
     * <pre>
     *     方法一: 使用数组。 0-25 标识26位小写字母. 每个字符串一个数组，记录该字符串内字符出现的频率.
     * </pre>
     * 
     * @param a
     * @return
     */
    public List<String> commonChars1(String[] a) {
        int size = a.length;
        int[][] ansArr = new int[size][26];
        for (int i = 0; i < size; i++) {
            String s = a[i];
            for (char c : s.toCharArray()) {
                ansArr[i][c - 'a']++;
            }
        }

        List<String> ans = new ArrayList<>();
        // 先遍历第一个数组找到非0的
        for (int i = 0; i < 26; i++) {
            if (ansArr[0][i] == 0) {
                continue;
            }
            int min = ansArr[0][i];
            for (int j = 1; j < size; j++) {
                min = Math.min(min, ansArr[j][i]);
            }
            if (min > 0) {
                for (int j = 0; j < min; j++) {
                    ans.add(String.valueOf((char) ('a' + i)));
                }
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     方法一优化，
     * </pre>
     * 
     * @param a
     * @return
     */
    public List<String> commonChars2(String[] a) {
        return null;
    }
}
