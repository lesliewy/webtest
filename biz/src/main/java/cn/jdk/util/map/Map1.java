package cn.jdk.util.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leslie on 2021/1/6.
 */
public class Map1 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * <pre>
     *     如果hashmap中实际插入了null,  map.put("a", null),  那么map.getOrDefault("a", "1") 并不会返回1， 而是null。
     *     如果没有实际插入null, map.getOrDefault("a", "1"), 可以正常返回1.
     *
     *     在mybatis中默认情况，如果mysql中是null, select 出来的map中相当于实际插入了null.
     * </pre>
     */
    private static void test1() {
        Map<String, String> map = new HashMap<>();
        String result = map.getOrDefault("a", "1");
        System.out.println(result);

        map.put("b", null);
        result = map.getOrDefault("b", "1");
        System.out.println("result == null: " + (result == null));
        System.out.println(result);

        // concurrentMap 不能插入空值.
        // Map<String, String> concurrMap = new ConcurrentHashMap<>();
        // concurrMap.put("c", null);
        // String concurrResult = concurrMap.getOrDefault("c", "3");
        // System.out.println("concurrResult: " + concurrResult);
    }
}
