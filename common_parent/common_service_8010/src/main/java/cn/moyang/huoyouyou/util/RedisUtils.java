package cn.moyang.huoyouyou.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 */
public enum RedisUtils {
    INSTANCE;
    static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1);//最小连接数
        config.setMaxTotal(20);//最大连接数
        config.setMaxWaitMillis(30 * 1000L);//最长等待时间
        config.setTestOnBorrow(true);//测试连接时是否畅通

        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 2 * 1000, "root");
    }

    //获取连接
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void closeJedis(Jedis jedis){
        if(jedis !=null){
            jedis.close();
        }
    }

    /**
     * 设置字符值
     *
     * @param key
     * @param value
     */
    public void set(String key,String value){
        Jedis jedis = getJedis();
        jedis.set(key,value);
        closeJedis(jedis);
    }

    /**
     * 获取字符值
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = getJedis();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJedis(jedis);
        }
        return null;
    }
}
