package com.erely.redis;

import redis.clients.jedis.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class RedisClientFactory {
    private static RedisClientFactory instance = null;

    private JedisPool jedisPool;
    private JedisPool jedisPool4follower;
    private AtomicBoolean isFollower = new AtomicBoolean(false);

    private RedisClientFactory() {
        initialPool();
    }

    public Jedis getResource() {
        if (isFollower.get())
            return jedisPool4follower.getResource();
        return jedisPool.getResource();
    }

    public AtomicBoolean getIsFollower() {
        return isFollower;
    }

    public void setIsFollower(AtomicBoolean isFollower) {
        this.isFollower = isFollower;
    }

    public void releaseResource(Jedis jedis) {
        if (null != jedis) {
            if (isFollower.get())
                jedisPool4follower.returnResource(jedis);
            else jedisPool.returnResource(jedis);
        }
    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        String[] servers = "192.168.225.128:6379,192.168.225.128:6380".split(",");

        jedisPool = new JedisPool(config, servers[0].split(":")[0], Integer.parseInt(servers[0].split(":")[1]), 5000);
        jedisPool4follower = new JedisPool(config, servers[1].split(":")[0], Integer.parseInt(servers[1].split(":")[1]), 5000);
    }


    public static RedisClientFactory getInstance() {
        if (instance == null) {
            synchronized (RedisClientFactory.class) {
                if (instance == null) {
                    instance = new RedisClientFactory();
                }
            }
        }
        return instance;
    }
}
