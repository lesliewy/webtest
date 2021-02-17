package cn.leetcode.problem701_800.problem791_800;

/**
 * <pre>
 *     阶乘函数后K个零
 *     f(x) 是 x! 末尾是0的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且0! = 1）
 例如， f(3) = 0 ，因为3! = 6的末尾没有0；而 f(11) = 2 ，因为11!= 39916800末端有2个0。给定 K，找出多少个非负整数x ，有 f(x) = K 的性质。

 示例 1:
 输入:K = 0
 输出:5
 解释: 0!, 1!, 2!, 3!, and 4! 均符合 K = 0 的条件。

 示例 2:
 输入:K = 5
 输出:0
 解释:没有匹配到这样的 x!，符合K = 5 的条件。
 注意：
 K是范围在 [0, 10^9] 的整数。
 * </pre>
 * 
 * Created by leslie on 2021/1/25.
 */
public class Problem793 {

    /* 主函数 */
    int preimageSizeFZF(int K) {
        // 左边界和右边界之差 + 1 就是答案
        return Integer.parseInt(String.valueOf(right_bound(K) - left_bound(K) + 1));
    }

    // 逻辑不变，数据类型全部改成 long
    long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }

    /* 搜索 trailingZeroes(n) == K 的左侧边界 */
    long left_bound(int target) {
        long lo = 0, hi = Long.MAX_VALUE;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    /* 搜索 trailingZeroes(n) == K 的右侧边界 */
    long right_bound(int target) {
        long lo = 0, hi = Long.MAX_VALUE;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo - 1;
    }
}
