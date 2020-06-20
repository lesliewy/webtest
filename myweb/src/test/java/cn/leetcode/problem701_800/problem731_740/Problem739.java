package cn.leetcode.problem701_800.problem731_740;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *   每日温度
 *   请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * </pre>
 * 
 * Created by leslie on 2020/6/16.
 */
public class Problem739 {

    @Test
    public void testxxx() {
        int[] temperatures1 = new int[] { 73, 74, 75, 71, 69, 72, 76, 73 };
        Assert.assertArrayEquals(new int[] { 1, 1, 4, 2, 1, 1, 0, 0 }, dailyTemperatures3(temperatures1));

        temperatures1 = new int[] { 73, 31, 75, 71, 100, 72, 99, 73 };
        Assert.assertArrayEquals(new int[] { 2, 1, 2, 1, 0, 1, 0, 0 }, dailyTemperatures3(temperatures1));

        temperatures1 = new int[] { 73 };
        Assert.assertArrayEquals(new int[] { 0 }, dailyTemperatures3(temperatures1));

        temperatures1 = new int[] { 73, 74 };
        Assert.assertArrayEquals(new int[] { 1, 0 }, dailyTemperatures3(temperatures1));
        temperatures1 = new int[] { 73, 72 };
        Assert.assertArrayEquals(new int[] { 0, 0 }, dailyTemperatures3(temperatures1));
    }

    /**
     * <pre>
     *     方法一: 使用栈.  依次遍历，小于等于栈顶则入栈。 大于栈顶，依次将栈中小于该temperature的值出栈. 同时计算差距天数.
     *     时间复杂度: O(N).  每个元素最多只有一次进栈、出栈，是常量时间。
     * </pre>
     * 
     * @param t
     * @return
     */
    public int[] dailyTemperatures1(int[] t) {
        int[] ans = new int[t.length];
        Stack<Map.Entry<Integer, Integer>> s = new Stack<>();
        int size = t.length;
        for (int idx = 0; idx < size; idx++) {
            int temperature = t[idx];
            if (s.isEmpty() || temperature <= s.peek().getKey()) {
                s.push(new AbstractMap.SimpleEntry<Integer, Integer>(temperature, idx));
            } else {
                while (s.size() > 0 && s.peek().getKey() < temperature) {
                    Map.Entry<Integer, Integer> e = s.pop();
                    ans[e.getValue()] = idx - e.getValue();
                }
                s.push(new AbstractMap.SimpleEntry<Integer, Integer>(temperature, idx));
            }
        }
        return ans;
    }

    /**
     * <pre>
     *     leetcode 不支持AbstractMap.SimpleEntry用法。 重新改写成直接用map
     * </pre>
     * 
     * @param t
     * @return
     */
    public int[] dailyTemperatures2(int[] t) {
        int size = t.length;
        int[] ans = new int[size];
        Stack<Map<Integer, Integer>> s = new Stack<>();
        for (int idx = 0; idx < size; idx++) {
            int temperature = t[idx];
            if (s.isEmpty() || temperature <= s.peek().entrySet().iterator().next().getKey()) {
                Map<Integer, Integer> m = new HashMap<>();
                m.put(t[idx], idx);
                s.push(m);
            } else {
                while (s.size() > 0 && s.peek().entrySet().iterator().next().getKey() < temperature) {
                    Map<Integer, Integer> e = s.pop();
                    int value = e.entrySet().iterator().next().getValue();
                    ans[value] = idx - value;
                }
                Map<Integer, Integer> m = new HashMap<>();
                m.put(t[idx], idx);
                s.push(m);
            }
        }
        return ans;
    }

    /**
     * <pre>
     *    同方法一, 不用map, 内存消耗太大.
     * </pre>
     * @param t
     * @return
     */
    public int[] dailyTemperatures3(int[] t) {
        int size = t.length;
        int[] ans = new int[size];
        Stack<Integer> s = new Stack<>();
        for (int idx = 0; idx < size; idx++) {
            int temperature = t[idx];
            if (s.isEmpty() || temperature <= s.peek()) {
                s.push(temperature);
            } else {
                while (s.size() > 0 && s.peek() < temperature) {
                    s.pop();
                    int value = getDay(ans, idx - 1);
                    ans[value] = idx - value;
                }
                s.push(temperature);
            }
        }
        return ans;
    }

    // 找最后一个0
    private int getDay(int[] a, int to){
        for(int i = to; i >= 0; i--){
            if(a[i] == 0){
                return i;
            }
        }
        return 0;
    }

    /**
     * <pre>
     *    基本同方法一, 但stack中存的是idx, 而不是temperature, 这样就不需要计算temperature对应的index, 相反，根据idx还可以得到temperature.
     * </pre>
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
