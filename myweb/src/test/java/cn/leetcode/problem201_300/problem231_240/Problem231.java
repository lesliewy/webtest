package cn.leetcode.problem201_300.problem231_240;

/**
 * <pre>
 *     2的幂
 *     给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

 示例 1:
 输入: 1
 输出: true
 解释: 20 = 1

 示例 2:
 输入: 16
 输出: true
 解释: 24 = 16

 示例 3:
 输入: 218
 输出: false
 * </pre>
 * 
 * Created by leslie on 2021/1/25.
 */
public class Problem231 {

    public boolean isPowerOfTwo(int n) {
        return n <= 0 ? false : (n & (n - 1)) == 0;
    }
}
