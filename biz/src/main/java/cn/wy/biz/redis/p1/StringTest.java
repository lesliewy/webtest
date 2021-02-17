package cn.wy.biz.redis.p1;

import cn.wy.biz.redis.JedisPoolUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by leslie on 2020/6/23.
 */
public class StringTest {
    public Jedis jedis = JedisPoolUtil.getJedis();
    public static void main(String[] args) {
        StringTest s = new StringTest();
        s.fun();
        s.fun2();
        s.fun3();
        s.fun4();
    }

    // 添加和获取
    public void fun() {
        jedis.set("num", "1");
        System.out.println(jedis.get("num"));
    }

    // 删除值
    public void fun1() {
        jedis.del("num");
        System.out.println(jedis.get("num"));
    }

    // 自减和自减
    public void fun2() {
        jedis.set("num", "1");
        System.out.println(jedis.get("num"));
        jedis.decr("num");
        System.out.println(jedis.get("num"));
        jedis.incr("num");
        jedis.incr("num");
        System.out.println(jedis.get("num"));
    }

    // 加上/减去 一个数
    // incrBy 返回的是修改之后的值如果原值是字符串不是数字，则会抛出异常
    public void fun3() {
        Long num = jedis.incrBy("num", 3);
        System.out.println(num);
        jedis.decrBy("num", 10);

        System.out.println(jedis.get("num"));
        jedis.set("name", "caopengfei");
        // jedis.decrBy("name",1);
    }

    // 字符串拼接
    public void fun4() {
        Long len = jedis.append("name", "123");
        System.out.println(len);
        System.out.println(jedis.get("name"));
    }
}
