package cn.wy.biz.redis.p2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.scripting.support.ResourceScriptSource;

import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import cn.wy.biz.redis.JedisPoolUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by leslie on 2021/1/2.
 */
public class DistributedLock {

    private static final Logger        logger   = LogManager.getLogger(DistributedLock.class);

    public static Jedis                jedis    = JedisPoolUtil.getJedis();

    public static final String         LOCK_KEY = "lock_";

    private DefaultRedisScript<String> lockScript;

    // 在biz module 下不方便使用.
    private static RedisTemplate       redisTemplate;

    // private static FileSystemXmlApplicationContext context = new
    // FileSystemXmlApplicationContext("/Users/leslie/MyProjects/GitHub/webtest/myweb/src/main/resources/redis/redis.xml");

    public static void main(String[] args) {
         testCallLua(jedis);
        // testCallLuaFile(jedis);

        // redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
        /*
        DistributedLock distributedLock = new DistributedLock();
        distributedLock.tryGetDistributedLock(LOCK_KEY, "123", 20);
        distributedLock.releaseLock(LOCK_KEY, "123");
        */
    }

    public boolean tryGetDistributedLock(String key, String requestId, int expiredTimeout) {
        // String publicKey = "name";
        boolean lock = false;
        try {
            lock = tryLock(key, expiredTimeout, requestId);
            if (!lock) {
                // String value = (String) redisTemplate.opsForValue().get(publicKey);
                // logger.warn("key have exist belong to:" + value);
                logger.warn("key have exist ");
            } else {
                // 获取锁成功
                logger.info("start lock success");
                // Thread.sleep(5000);
            }
        } catch (Exception e) {
            logger.error("setNx error!");
            e.printStackTrace();
        } finally {
            // redisTemplate.delete(publicKey);
        }
        return lock;
    }

    /**
     * 获取lua结果
     * 
     * @param key
     * @param value
     * @return
     */
    public Boolean luaExpress(String key, int time, String value) {
        lockScript = new DefaultRedisScript<String>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("add_lock.lua")));
        lockScript.setResultType(String.class);
        // 封装参数
        List<Object> keyList = new ArrayList<Object>();
        keyList.add(key);
        keyList.add(time);
        keyList.add(value);
        String result = (String) redisTemplate.execute(lockScript, keyList);
        System.out.println(result);
        logger.info("redis set result：" + result);
        if (!"ok".equals(result.toLowerCase())) {
            return false;
        }
        return true;
    }

    public Boolean tryLock(String key, int time, String value) {
        try {
            Reader r = new InputStreamReader(new ClassPathResource("redis/add_lock.lua").getInputStream());
            String luaStr = CharStreams.toString(r);
            /*
             * Object result = jedis.eval(luaStr, Lists.newArrayList(key), Lists.newArrayList(String.valueOf(time),
             * value));
             */
            // KEYS[] 获取, ARGS[]获取.
            Object result = jedis.eval(luaStr, Lists.newArrayList(new String[] { key, String.valueOf(time), value }),
                                       Lists.newArrayList());
            logger.info("redis set result：" + result);
            if (!"OK".equals(result.toString())) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean releaseLock(String lockName, String requestId) {
        try {
            Reader r = new InputStreamReader(new ClassPathResource("redis/release_lock.lua").getInputStream());
            String luaStr = CharStreams.toString(r);
            // KEYS[] 获取, ARGS[]获取.
            Object result = jedis.eval(luaStr, Lists.newArrayList(new String[] { lockName }),
                                       Lists.newArrayList(requestId));
            logger.info("redis release result：" + result);
            if (!"1".equals(result.toString())) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean luaShaExpress(String key, int time, String value) {
        try {
            Reader r = new InputStreamReader(new ClassPathResource("redis/add_lock.lua").getInputStream());
            String luaStr = CharStreams.toString(r);
            String lua_sha1 = DigestUtils.sha1DigestAsHex(luaStr);

            // KEYS[] 获取, ARGS[]获取.
            Object result = jedis.eval(luaStr, Lists.newArrayList(new String[] { key, String.valueOf(time), value }),
                                       Lists.newArrayList());
            logger.info("redis set result：" + result);
            if (!"ok".equals(result.toString())) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param jedis
     */
    public static void testCallLua(Jedis jedis) {
        Object result = null;
        String luaStr = "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}";
        result = jedis.eval(luaStr, Lists.newArrayList("userName", "age"), Lists.newArrayList("Jack", "20"));
        System.out.println(result);

        String luaSha1 = DigestUtils.sha1DigestAsHex(luaStr);
        result = jedis.evalsha(luaSha1, Lists.newArrayList("a", "b"), Lists.newArrayList("1", "2"));
        System.out.println(result);
    }

    public static void testCallLuaFile(Jedis jedis) {
        String luaStr = null;

        try {
            Reader r = new InputStreamReader(new ClassPathResource("redis/test1.lua").getInputStream());
            luaStr = CharStreams.toString(r);
            Object result = jedis.eval(luaStr, Lists.newArrayList("userName"), Lists.newArrayList("20", "Tom"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
