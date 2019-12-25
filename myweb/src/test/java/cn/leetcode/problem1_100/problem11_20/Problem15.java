package cn.leetcode.problem1_100.problem11_20;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * <pre>
 * 三数之和.
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]， 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 * </pre>
 * 
 * Created by leslie on 2019/11/26.
 */
public class Problem15 {

    @Test
    public void test1() {
        System.out.println(threeSum2(new int[] { -1, 0, 1, 2, -1, -4 }));
        System.out.println(threeSum2(new int[] { -1, 0, 1, 0 }));
        System.out.println(threeSum2(new int[] { 0, 0, 0 }));
    }

    /**
     * <pre>
     * 方法一: 常规方法O(n^3), 使用哈希表降到O(n^2), 数据可以重复.
     * 最后leetcode上报超时. 非常多0的情况.
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        int len = nums.length;
        Map<Integer, Integer> intMap = genMapFromArr(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> all = new HashSet<>();
        int i = 0, j = 0;
        for (i = 0; i < len; i++) {
            for (j = i + 1; j < len; j++) {
                int target = 0 - (nums[i] + nums[j]);
                Integer times = intMap.get(target);
                if (times != null) {
                    if ((target == nums[i] || target == nums[j]) && times.intValue() == 0) {
                        continue;
                    }
                    if (target == nums[i] && target == nums[j] && times.intValue() < 2) {
                        continue;
                    }
                    List<Integer> a = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(a);
                    String s = a.get(0) + "," + a.get(1) + "," + a.get(2);
                    all.add(s);
                }
            }
        }
        result = recoverFromSet(all);
        return result;
    }

    /**
     * <pre>
     * 方法二: 排序 + 双指针(固定一点)
     * num[k]最小,  num[k] > 0 不用考虑;  小于0时, i, j 双指针指向排序数组的两头, 向中间移动
     * 时间复杂度: O(n^2) 空间复杂度: O(1)
     * </pre>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        int len = nums.length;
        int k, i, j;
        List<List<Integer>> result = new ArrayList<>();
        // nums[k] 为最小值, 每次固定k点, 移动i,j, 分别从两头向中间移动.
        for (k = 0; k < len; k++) {
            // nums[k] 最小，一旦大于0就结束.
            if (nums[k] > 0) {
                break;
            }
            // 显然k不需要重复查找.
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            // 设置i j 为数组的两头.
            i = k + 1;
            j = len - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == 0) {
                    // 找到一个组合, 此时应该同时移动i,j.
                    result.add(Arrays.asList(nums[k], nums[i], nums[j]));

                    // 需要跳过所有重复的
                    i++;
                    while (nums[i] == nums[i - 1] && i < j) {
                        i++;
                    }
                    j--;
                    while (nums[j] == nums[j + 1] && i < j) {
                        j--;
                    }
                } else if (sum > 0) {
                    // 只需将j左移, 同时跳过所有重复数字.
                    j--;
                    while (nums[j] == nums[j + 1] && i < j) {
                        j--;
                    }
                } else if (sum < 0) {
                    // 只需右移i
                    i++;
                    while (nums[i] == nums[i - 1] && i < j) {
                        i++;
                    }
                }
            }
        }
        return result;
    }

    private Map<Integer, Integer> genMapFromArr(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : nums) {
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + 1);
            } else {
                m.put(i, 0);
            }
        }
        return m;
    }

    private List<List<Integer>> recoverFromSet(Set<String> s) {
        List<List<Integer>> result = new ArrayList<>();
        for (String str : s) {
            List<Integer> l = Arrays.stream(str.split(",")).map(a -> Integer.valueOf(a)).collect(Collectors.toList());
            result.add(l);
        }
        return result;
    }
}
