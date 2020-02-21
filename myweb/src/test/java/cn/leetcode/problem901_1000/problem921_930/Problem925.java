package cn.leetcode.problem901_1000.problem921_930;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     长按键入
 *     你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *     你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *     输入：name = "alex", typed = "aaleex"
 输出：true
 解释：'alex' 中的 'a' 和 'e' 被长按

 输入：name = "saeed", typed = "ssaaedd"
 输出：false
 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样

 输入：name = "leelee", typed = "lleeelee"
 输出：true

 输入：name = "laiden", typed = "laiden"
 输出：true
 解释：长按名字中的字符并不是必要的

 提示：

 name.length <= 1000
 typed.length <= 1000
 name 和 typed 的字符都是小写字母
 * </pre>
 * 
 * Created by leslie on 2020/1/6.
 */
public class Problem925 {

    @Test
    public void testxxx() {
        Assert.assertTrue(isLongPressedName("alex", "aaleex"));
        Assert.assertFalse(isLongPressedName("saeed", "ssaaedd"));
        Assert.assertTrue(isLongPressedName("leelee", "lleeelee"));
        Assert.assertTrue(isLongPressedName("laiden", "laiden"));
        Assert.assertFalse(isLongPressedName("pyplrz", "ppyypllr"));
    }

    /**
     * <pre>
     *     方法一: 两个指针同时遍历两个字符串.
     * </pre>
     * 
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();

        int i = 0, j = 0;
        char preTyped = '0';
        // 两个指针同时遍历, 适合while。 嵌套遍历，适合for
        while (i < nameChars.length && j < typedChars.length) {
            // 相等, 同时前移.
            if (nameChars[i] == typedChars[j]) {
                preTyped = typedChars[j];
                i++;
                j++;
                continue;
            }
            // 不等, 再判断是否等于前一个.
            if (nameChars[i] != typedChars[j]) {
                if (preTyped == typedChars[j]) {
                    j++;
                    continue;
                } else {
                    return false;
                }
            }
        }
        // 注意, name 必须要遍历完.
        return i == nameChars.length;
    }
}
