package cn.leetcode.problem401_500.problem431_440;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 *     无重叠区间
 *     给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 注意:
 可以认为区间的终点总是大于它的起点。
 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

 示例 1:
 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 输出: 1
 解释: 移除 [1,3] 后，剩下的区间没有重叠。

 示例 2:
 输入: [ [1,2], [1,2], [1,2] ]
 输出: 2
 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。

 示例 3:
 输入: [ [1,2], [2,3] ]
 输出: 0
 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * </pre>
 * 
 * Created by leslie on 2021/1/17.
 */
public class Problem435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }

    /**
     * <pre>
     *     计算最多有几个不相交区间.
     * </pre>
     * 
     * @param intvs
     * @return
     */
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) {
            return 0;
        }
        // 按 end 升序排序
        Arrays.sort(intvs, new Comparator<int[]>() {

            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }
}
