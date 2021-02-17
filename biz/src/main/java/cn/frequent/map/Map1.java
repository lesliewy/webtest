package cn.frequent.map;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by leslie on 2020/11/13.
 */
public class Map1 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * <pre>
     *     范围
     *     [0-501):  A
     *     [501, 901): B
     *     [901, 1056): C
     *     [1056, xxx): D
     * </pre>
     */
    public static void test1() {
        System.out.println("======test1======");
        NavigableMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(0, "A");
        map.put(501, "B");
        map.put(901, "C");
        map.put(1056, "D");

        System.out.println(map.floorEntry(1).getValue());
        System.out.println(map.floorEntry(999).getValue());
        System.out.println(map.floorEntry(3).getValue());
        System.out.println(map.floorEntry(898).getValue());
        System.out.println(map.floorEntry(501).getValue());
        System.out.println(map.floorEntry(2232).getValue());

        // 增加一维.
        Map<String, NavigableMap<Long, String>> map2 = new HashMap<String, NavigableMap<Long, String>>() {

            {
                put("spark.driver.memory", new TreeMap<Long, String>() {

                    {
                        put(0L, "4g");
                        put(20_0000L, "8g");
                        put(200_0000L, "12g");
                        put(2000_0000L, "16g");
                    }
                });
                put("spark.executor.memory", new TreeMap<Long, String>() {

                    {
                        put(0L, "4g");
                        put(20_0000L, "8g");
                        put(200_0000L, "12g");
                        put(2000_0000L, "16g");
                    }
                });
                put("spark.rpc.message.maxSize", new TreeMap<Long, String>() {

                    {
                        put(0L, "256");
                        put(20_0000L, "512");
                        put(200_0000L, "1024");
                        put(2000_0000L, "2048");
                    }
                });
                put("spark.kryoserializer.buffer.max", new TreeMap<Long, String>() {

                    {
                        put(0L, "256m");
                        put(20_0000L, "512m");
                        put(200_0000L, "1g");
                        put(2000_0000L, "2g");
                    }
                });
            }
        };

        System.out.println(map2.get("spark.kryoserializer.buffer.max").floorEntry(9999L).getValue());
        System.out.println(map2.get("spark.kryoserializer.buffer.max").floorEntry(9999_9999L).getValue());
        System.out.println(map2.get("spark.executor.memory").floorEntry(300L).getValue());
        System.out.println(map2.get("spark.executor.memory").floorEntry(300_0000L).getValue());
    }

    /**
     * <pre>
     *     行、列矩阵式.
     * </pre>
     */
    public static void test2() {
        System.out.println("======test2======");
        NavigableMap<Long, NavigableMap<Long, Integer>> map = new TreeMap<Long, NavigableMap<Long, Integer>>() {

            {
                put(0L, new TreeMap<Long, Integer>() {

                    {
                        put(0L, 50_0000);
                    }
                    {
                        put(10L, 40_0000);
                    }
                    {
                        put(20L, 30_0000);
                    }
                    {
                        put(30L, 20_0000);
                    }
                    {
                        put(40L, 10_0000);
                    }
                    {
                        put(50L, 50000);
                    }
                });
                put(1000_0000L, new TreeMap<Long, Integer>() {

                    {
                        put(0L, 50_0000);
                    }
                    {
                        put(10L, 40_0000);
                    }
                    {
                        put(20L, 30_0000);
                    }
                    {
                        put(30L, 20_0000);
                    }
                    {
                        put(40L, 10_0000);
                    }
                    {
                        put(50L, 50000);
                    }
                });
                put(10000_0000L, new TreeMap<Long, Integer>() {

                    {
                        put(0L, 50_0000);
                    }
                    {
                        put(10L, 40_0000);
                    }
                    {
                        put(20L, 30_0000);
                    }
                    {
                        put(30L, 20_0000);
                    }
                    {
                        put(40L, 10_0000);
                    }
                    {
                        put(50L, 50000);
                    }
                });
                put(50000_0000L, new TreeMap<Long, Integer>() {

                    {
                        put(0L, 50_0000);
                    }
                    {
                        put(10L, 40_0000);
                    }
                    {
                        put(20L, 30_0000);
                    }
                    {
                        put(30L, 20_0000);
                    }
                    {
                        put(40L, 10_0000);
                    }
                    {
                        put(50L, 50000);
                    }
                });
            }
        };
        long a = 500L, b = 5L;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
        a = 499_2323;
        b = 25;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
        a = 2348_2763;
        b = 42;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
        a = 1_7663_9876;
        b = 5;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
        a = 8_8866_9876;
        b = 87;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
        a = 986_9876;
        b = 34;
        System.out.println(a + "-" + b + ":" + map.get(map.floorKey(a)).floorEntry(b).getValue());
    }
}
