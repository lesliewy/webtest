package cn.wy.biz.redis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by leslie on 2020/6/23.
 */
public class JedisPoolUtil {

    private static JedisPool pool = null;
    static {
        // 加载配置文件
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/leslie/MyProjects/GitHub/webtest/myweb/src/main/resources/properties/redis.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("redis/redis.properties");
        Properties pro = new Properties();
        try {
            pro.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("加载文件失败");
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));
        // 最大空闲连接数
        poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
        // 最小空闲连接数
        poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));
        pool = new JedisPool(poolConfig, pro.get("redis.host").toString(),
                             Integer.parseInt(pro.get("redis.port").toString()));
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
