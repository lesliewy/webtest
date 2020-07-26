package cn.wy.biz.redis.p1;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Created by leslie on 2020/6/23.
 */
public class SetTest {

    public Jedis jedis = JedisPoolUtil.getJedis();

    public static void main(String[] args) {
        SetTest s = new SetTest();
        s.fun();
        s.fun1();
        s.fun2();
        s.fun3();
        s.fun4();
        s.fun5();
        s.fun6();
        s.fun7();
        s.fun8();
        s.fun9();
    }

    /* 添加元素删除元素 */
    public void fun() {
        System.out.println("======fun=======");
        Long num = jedis.sadd("myset", "a", "a", "b", "abc");
        System.out.println(num);

    }

    /* 获得元素 */
    public void fun1() {
        System.out.println("======fun1=======");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
    }

    /* 移除元素 */
    public void fun2() {
        System.out.println("======fun2=======");
        jedis.srem("myset", "a", "b");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
    }

    // 判断是否这个set中存在某个值
    public void fun3() {
        System.out.println("======fun3=======");
        Boolean sismember = jedis.sismember("myset", "a");
        System.out.println(sismember);
    }

    // 获得A-B 获得差集合
    public void fun4() {
        System.out.println("======fun4=======");
        jedis.sadd("myset1", "123", "32", "abc", "def", "123456", "sdfasd");
        jedis.sadd("myset2", "abc", "345", "123", "fda");
        Set<String> sdiff = jedis.sdiff("myset1", "myset2");
        System.out.println(sdiff);
    }

    // 获得交集
    public void fun5() {
        System.out.println("======fun5=======");
        Set<String> sinter = jedis.sinter("myset1", "myset2");
        System.out.println(sinter);

    }

    // 获得并集合
    public void fun6() {
        System.out.println("======fun6=======");
        Set<String> sunion = jedis.sunion("myset1", "myset2");
        System.out.println(sunion);
    }

    // 成员数量
    public void fun7() {
        System.out.println("======fun7=======");
        System.out.println(jedis.scard("myset1"));
    }

    // 获得随机的一个成员
    public void fun8() {
        System.out.println("======fun8=======");
        System.out.println(jedis.srandmember("myset1"));
    }

    // 将相差的成员放到一个新的set中同理交集和并集都可以后面均加上一个store即可 并返回新的长度
    // myset1, myset2 的差值放入myset3
    public void fun9() {
        System.out.println("======fun9=======");
        System.out.println(jedis.sdiffstore("myset3", "myset1", "myset2"));
        System.out.println(jedis.smembers("myset3"));
    }
}
