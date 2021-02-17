package cn.leetcode.problem701_800.problem701_710;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/**
 * <pre>
 *     给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。

 对它进行优化使其尽量少调用系统方法 Math.random() 。

 提示:

 1 <= N <= 1000000000
 0 <= B.length < min(100000, N)
 [0, N) 不包含 N，详细参见 interval notation 。

 示例 1:

 输入:
 ["Solution","pick","pick","pick"]
 [[1,[]],[],[],[]]
 输出: [null,0,0,0]
 示例 2:

 输入:
 ["Solution","pick","pick","pick"]
 [[2,[]],[],[],[]]
 输出: [null,1,1,1]
 示例 3:

 输入:
 ["Solution","pick","pick","pick"]
 [[3,[1]],[],[],[]]
 Output: [null,0,0,2]
 示例 4:

 输入:
 ["Solution","pick","pick","pick"]
 [[4,[2]],[],[],[]]
 输出: [null,1,3,1]

 输入语法说明：

 输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，N 和黑名单 B。pick 没有参数，输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 * </pre>
 * 
 * Created by leslie on 2020/10/26.
 */
public class Problem710 {

    @Test
    public void test1() {
        Solution solution = new Solution(5, new int[] { 4, 0, 2, 3 });
        for (int i = 0; i < 5; i++) {
            System.out.println(solution.pick());
        }

    }

    class Solution {

        private int                   sz;
        private Map<Integer, Integer> mapping = new HashMap();

        /**
         * <pre>
         *     hash表 + 数组末尾
         *     将黑名单中的元素映射到数组尾部，尾部如果已经是黑名单元素，则跳过.
         * </pre>
         * 
         * @param N
         * @param blacklist
         */
        public Solution(int N, int[] blacklist){

            sz = N - blacklist.length;
            for (int b : blacklist) {
                mapping.put(b, 666);
            }

            int last = N - 1;
            for (int b : blacklist) {
                // 如果 b 已经在区间 [sz, N)
                // 可以直接忽略
                if (b >= sz) {
                    continue;
                }
                // 找到一个非黑名单元素.
                while (mapping.containsKey(last)) {
                    last--;
                }
                mapping.put(b, last);
                last--;
            }
        }

        public int pick() {
            // 随机选取一个索引
            // int index = Math.abs(new Random().nextInt()) % sz;
            // 获得【0, sz] 的随机数，免去了Math.abs
            int index = new Random().nextInt(sz);
            // 这个索引命中了黑名单，
            // 需要被映射到其他位置
            Integer exists = mapping.get(index);
            if (exists != null) {
                return exists;
            }
            // 若没命中黑名单，则直接返回
            return index;
        }
    }

}
