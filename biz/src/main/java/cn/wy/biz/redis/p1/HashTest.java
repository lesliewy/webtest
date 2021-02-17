package cn.wy.biz.redis.p1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.wy.biz.redis.JedisPoolUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by leslie on 2020/6/23.
 */
public class HashTest {

    public Jedis jedis = JedisPoolUtil.getJedis();

    public static void main(String[] args) {
        HashTest h = new HashTest();
        h.fun();
        h.fun1();
        h.fun2();
        h.fun3();
        h.fun4();
        h.fun5();
        h.fun6();
        h.fun7();
        h.fun8();
    }

    // hash 操作的是map对象
    // 适合存储键值对象的信息
    // 存值 参数第一个变量的名称， map键名(key)， map键值(value)
    // 调用hset
    public void fun() {
        System.out.println("=====fun====");
        Long num = jedis.hset("hash1", "username", "caopengfei");
        System.out.println(num);
        String hget = jedis.hget("hash1", "username");
        System.out.println(hget);
    }

    // 也可以存多个key
    // 调用hmset
    public void fun1() {
        System.out.println("=====fun1====");
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "caopengfei");
        map.put("age", "25");
        map.put("sex", "男");
        String res = jedis.hmset("hash2", map);
        System.out.println(res);// ok
    }

    // 获取hash中所有的值
    public void fun2() {
        System.out.println("=====fun2====");
        Map<String, String> map2 = new HashMap<String, String>();
        map2 = jedis.hgetAll("hash2");
        System.out.println(map2);
    }

    // 删除hash中的键 可以删除一个也可以删除多个，返回的是删除的个数
    public void fun3() {
        System.out.println("=====fun3====");
        Long num = jedis.hdel("hash2", "username", "age");
        System.out.println(num);
        Map<String, String> map2 = new HashMap<String, String>();
        map2 = jedis.hgetAll("hash2");
        System.out.println(map2);
    }

    // 增加hash中的键值对
    public void fun4() {
        System.out.println("=====fun4====");
        Map<String, String> map2 = new HashMap<String, String>();
        map2 = jedis.hgetAll("hash2");
        System.out.println(map2);
        jedis.hincrBy("hash2", "age", 10);
        map2 = jedis.hgetAll("hash2");
        System.out.println(map2);
    }

    // 判断hash是否存在某个值
    public void fun5() {
        System.out.println("=====fun5====");
        System.out.println(jedis.hexists("hash2", "username"));
        System.out.println(jedis.hexists("hash2", "age"));
    }

    // 获取hash中键值对的个数
    public void fun6() {
        System.out.println("=====fun6====");
        System.out.println(jedis.hlen("hash2"));
    }

    // 获取一个hash中所有的key值
    public void fun7() {
        System.out.println("=====fun7====");
        Set<String> hash2 = jedis.hkeys("hash2");
        System.out.println(hash2);
    }

    // 获取所有的value值
    public void fun8() {
        System.out.println("=====fun8====");
        List<String> hash2 = jedis.hvals("hash2");
        System.out.println(hash2);
    }
}
