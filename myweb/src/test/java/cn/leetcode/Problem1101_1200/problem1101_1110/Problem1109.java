package cn.leetcode.Problem1101_1200.problem1101_1110;

/**
 * <pre>
 *   航班预订统计
 *   这里有 n 个航班，它们分别从 1 到 n 进行编号。
 我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [j, k, l] 意味着我们在从 j 到 k 的每个航班上预订了 l 个座位。
 请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。

 示例：
 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 输出：[10,55,45,25,25]

 提示：
 1 <= bookings.length <= 20000
 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 1 <= bookings[i][2] <= 10000

 * </pre>
 * Created by leslie on 2021/2/1.
 */
public class Problem1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] nums = new int[n];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] booking : bookings) {
            // 注意转成数组索引要减一哦
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            // 对区间 nums[i..j] 增加 val
            df.increment(i, j, val);
        }
        // 返回最终的结果数组
        return df.result();
    }

    class Difference {
        // 差分数组
        private int[] diff;

        public Difference(int[] nums) {
            assert nums.length > 0;
            diff = new int[nums.length];
            // 构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /* 给闭区间 [i,j] 增加 val（可以是负数）*/
        public void increment(int i, int j, int val) {
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        public int[] result() {
            int[] res = new int[diff.length];
            // 根据差分数组构造结果数组
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }
}
