package cn.wy.biz.guava.cache.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.*;

/**
 * <pre>
 * LoadingCache, build()时需提供load()方法;
 * LocalManualCache: build() 时无需提供参数;
 *
 * Created by leslie on 2020/4/8.
 * </pre>
 */
public class GuavaTest {

    private static LoadingCache<String, Integer> cache = CacheBuilder.newBuilder().maximumSize(10)// 最多存放十个数据
                                                                     .expireAfterWrite(10, TimeUnit.SECONDS)// 缓存10秒，10秒之后进行回收
                                                                     .recordStats()// 开启，记录状态数据功能
                                                                     .build(new CacheLoader<String, Integer>() {

                                                                         // 默认的数据加载实现,当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
                                                                         // 数据加载，默认返回-1，也可以是查询操作，如从DB查询
                                                                         @Override
                                                                         public Integer load(String key) throws Exception {
                                                                             // TODO Auto-generated method stub
                                                                             return -1;
                                                                         }
                                                                     });

    public static void main(String[] args) throws ExecutionException {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        // test6();
        test7();
    }

    public static void test1() {
        try {
            // 只查询缓存，没有命中，即返回null, 和 直接get()是不同的.
            System.out.println(cache.getIfPresent("key1"));
            // put数据，放在缓存中
            cache.put("key1", 1);
            // 再次查询，已经存在缓存中
            System.out.println(cache.getIfPresent("key1"));
            // 直接get(), 查询缓存，未命中，调用load方法，返回-1
            System.out.println(cache.get("key2"));
            // put数据，更新缓存
            cache.put("key2", 2);
            // 查询得到最新的数据
            System.out.println(cache.get("key2"));
            System.out.println("size:" + cache.size());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        try {
            cache.put("key2", 2);
            // 插入十个数据
            for (int i = 3; i < 13; i++) {
                cache.get("key2");
                cache.put("key" + i, i);
            }
            // 超过最大容量， 根据LRU淘汰数据.
            System.out.println("size:" + cache.size());
            System.out.println(cache.getIfPresent("key2"));
            // 等待5秒
            Thread.sleep(5000);
            cache.put("key1", 1);
            cache.put("key2", 2);
            // key5没失效，key3、key4已经失效
            System.out.println(cache.getIfPresent("key5"));
            // 等待5秒
            Thread.sleep(5000);
            // 此时key5-key12已经失效，但是size没有更新
            System.out.println("size :" + cache.size());
            System.out.println(cache.getIfPresent("key1"));
            System.out.println("size :" + cache.size());
            // 获取key5，发现已经失效，然后刷新缓存，遍历数据，去掉失效的所有数据
            System.out.println(cache.getIfPresent("key5"));
            // 此时只有key1，key2没有失效
            System.out.println("size :" + cache.size());
            System.out.println("status, hitCount:" + cache.stats().hitCount() + ", missCount:"
                               + cache.stats().missCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        // 3秒内未读取过，即失效.
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(2).expireAfterAccess(3,
                                                                                                 TimeUnit.SECONDS).build();
        cache.put("key1", "value1");
        int time = 1;
        while (true) {
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("睡眠" + time++ + "秒后取到key1的值为：" + cache.getIfPresent("key1"));
        }
    }

    public static void test4() {
        // 只缓存弱引用, value 指向新对象后，不再有强引用指向原对象;
        Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(2).weakValues().build();
        Object value = new Object();
        cache.put("key1", value);
        System.out.println("value: " + value + ", cache: " + cache.getIfPresent("key1"));

        value = new Object();// 原对象不再有强引用
        System.gc();
        System.out.println("value: " + value + ", cache: " + cache.getIfPresent("key1"));
    }

    public static void test5() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        Object value = new Object();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        List<String> list = new ArrayList<String>();
        list.add("key1");
        list.add("key2");

        // 批量清除list中全部key对应的记录
        cache.invalidateAll(list);
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    public static void test6() {
        // 移除监听器
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {

            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(listener).build();
        // 当达到最大值时，再put()，会移除一个.
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value3");
        cache.put("key5", "value3");
        cache.put("key6", "value3");
        cache.put("key7", "value3");
        cache.put("key8", "value3");
    }

    public static void test7() {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build();

        /**
         * 虽然是两个线程同时调用get方法，但只有一个get方法中的Callable会被执行(没有打印出load2)
         */
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("thread1");
                try {
                    // 如果缓存中不包含key对应的记录，Guava会启动一个线程执行Callable对象中的call方法，call方法的返回值会作为key对应的值被存储到缓存中，并且被get方法返回
                    String value = cache.get("key", new Callable<String>() {

                        @Override
                        public String call() throws Exception {
                            System.out.println("load1"); // 加载数据线程执行标志
                            Thread.sleep(1000); // 模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread1 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("thread2");
                try {
                    String value = cache.get("key", new Callable<String>() {

                        @Override
                        public String call() throws Exception {
                            System.out.println("load2"); // 加载数据线程执行标志
                            Thread.sleep(1000); // 模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread2 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
