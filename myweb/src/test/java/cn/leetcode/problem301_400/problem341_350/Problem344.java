package cn.leetcode.problem301_400.problem341_350;

import org.junit.Assert;
import org.junit.Test;

/**
 * 反转字符串.
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 你可以假设数组中的所有字符都是
 * ASCII 码表中的可打印字符。
 * </p>
 * <p>
 * 输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"]
 * </p>
 * Created by leslie on 2019/11/28.
 */
public class Problem344 {

    @Test
    public void test1() {

        Assert.assertArrayEquals(new char[] { 'o', 'l', 'l', 'e', 'h' },
                                 reverseString(new char[] { 'h', 'e', 'l', 'l', 'o' }));
        Assert.assertArrayEquals(new char[] { 'o', 'l', 'l', 'e' }, reverseString(new char[] { 'e', 'l', 'l', 'o' }));
        Assert.assertArrayEquals(new char[] { 'o' }, reverseString(new char[] { 'o' }));
    }

    /**
     * 方法一: 从数组头尾向中间遍历. 依次交换字符.
     * <p>
     * 时间复杂度: O(N) 空间复杂度: O(1)
     * </p>
     * 
     * @param s
     * @return
     */
    public char[] reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return s;
        }
        int len = s.length;
        int low = 0, high = len - 1;
        char tmp;
        while (low < high) {
            tmp = s[low];
            s[low++] = s[high];
            s[high--] = tmp;
        }
        return s;
    }
}
