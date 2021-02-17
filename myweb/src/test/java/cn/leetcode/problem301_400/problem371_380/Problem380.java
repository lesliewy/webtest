package cn.leetcode.problem301_400.problem371_380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/**
 * <pre>
 *     设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

 insert(val)：当元素 val 不存在时，向集合中插入该项。
 remove(val)：元素 val 存在时，从集合中移除该项。
 getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 示例 :
 // 初始化一个空的集合。
 RandomizedSet randomSet = new RandomizedSet();

 // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 randomSet.insert(1);

 // 返回 false ，表示集合中不存在 2 。
 randomSet.remove(2);

 // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 randomSet.insert(2);

 // getRandom 应随机返回 1 或 2 。
 randomSet.getRandom();

 // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 randomSet.remove(1);

 // 2 已在集合中，所以返回 false 。
 randomSet.insert(2);

 // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 randomSet.getRandom();
 * </pre>
 * 
 * Created by leslie on 2020/10/26.
 */
public class Problem380 {

    @Test
    public void test1() {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
    }

    class RandomizedSet {

        ArrayList<Integer>    nums;
        Map<Integer, Integer> valueToIdx;

        /** Initialize your data structure here. */
        public RandomizedSet(){
            nums = new ArrayList<>();
            valueToIdx = new HashMap<>();
        }

        /**
         * <pre>
         *    数组尾部的插入，删除是O(1).
         *    插入在数组尾部，同时用hash表记录 index.
         * </pre>
         */
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (valueToIdx.containsKey(val)) {
                return false;
            }
            nums.add(val);
            valueToIdx.put(val, nums.size() - 1);
            return true;
        }

        /**
         * <pre>
         *   避免删除时数组元素移动，先和最后一个元素交换位置，再删除.
         * </pre>
         */
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!valueToIdx.containsKey(val)) {
                return false;
            }
            int idx = valueToIdx.get(val);
            int lastEle = nums.get(nums.size() - 1);
            nums.set(idx, lastEle);
            valueToIdx.put(lastEle, idx);
            nums.remove(nums.size() - 1);
            valueToIdx.remove(val);
            return true;
        }

        /**
         * <pre>
         *     如果想「等概率」且「在 O(1) 的时间」取出元素，一定要满足：底层用数组实现，且数组必须是紧凑的.
         * </pre>
         */
        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get(Math.abs(new Random().nextInt()) % nums.size());
        }
    }
}
