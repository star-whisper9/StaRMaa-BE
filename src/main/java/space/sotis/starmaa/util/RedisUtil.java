package space.sotis.starmaa.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author x1ngyu
 * @since 2024/10/10
 * <p>
 * 静态Redis连接池封装，全局单例连接池，记得在程序销毁调用close方法
 */

public class RedisUtil {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(-1);
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }

    public static void close() {
        jedisPool.close();
    }
}
