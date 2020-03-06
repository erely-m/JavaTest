package com.erely.redis;

import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisHelper implements Cache {
    private static volatile RedisHelper helper = null;
    private String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static RedisHelper getInstance() {
        if (helper == null) {
            synchronized (RedisHelper.class) {
                if (helper == null) {
                    helper = new RedisHelper();
                }
            }
        }
        return helper;
    }

    private RedisHelper() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisClientFactory factory = RedisClientFactory.getInstance();
        Jedis jedis = null;
        try {
            jedis = factory.getResource();
            jedis.set(key.toString().getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(" ERROR !!!切换主从");
            switch2Follower(factory);
            try {
                jedis = factory.getResource();
                jedis.set(key.toString().getBytes(), SerializeUtil.serialize(value));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            if ((factory != null) && (jedis != null))
                factory.releaseResource(jedis);
        }
    }

    @Override
    public Object getObject(Object o) {
        RedisClientFactory factory = RedisClientFactory.getInstance();
        Jedis shardedJedis = null;
        try {
            shardedJedis = factory.getResource();
            byte[] b = shardedJedis.get(o.toString().getBytes());
            Object obj = SerializeUtil.deserialize(b);
            return obj;
        } catch (Exception localException) {
        } finally {
            factory.releaseResource(shardedJedis);
        }
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        RedisClientFactory factory = RedisClientFactory.getInstance();
        Jedis jedis = null;
        try {
            jedis = factory.getResource();
            return jedis.del(o.toString().getBytes());
        } catch (Exception e) {
            System.out.println("ERROR!!!切换主从(remove)");
            switch2Follower(factory);
            try {
                jedis = factory.getResource();
                return jedis.del(o.toString().getBytes());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            if ((factory != null) && (jedis != null)) {
                factory.releaseResource(jedis);
            }
        }
        return null;
    }

    @Override
    public void clear() {
        RedisClientFactory factory = RedisClientFactory.getInstance();
        Jedis jedis = null;
        try {
            jedis = factory.getResource();
            jedis.flushDB();
        } catch (Exception localException) {
        } finally {
            if ((factory != null) && (jedis != null)) {
                factory.releaseResource(jedis);
            }
        }
    }

    @Override
    public int getSize() {
        RedisClientFactory factory = RedisClientFactory.getInstance();
        Jedis jedis = null;
        try {
            jedis = factory.getResource();
            return jedis.dbSize().intValue();
        } catch (Exception localException) {
        } finally {
            if ((factory != null) && (jedis != null)) {
                factory.releaseResource(jedis);
            }
        }
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private void switch2Follower(RedisClientFactory factory) {
        if (factory.getIsFollower().get()) {
            factory.getIsFollower().compareAndSet(true, false);
        } else {
            factory.getIsFollower().compareAndSet(false, true);
        }
    }

    public static void main(String[] args) {

        RedisHelper rh = new RedisHelper();
        System.out.println(rh.getObject("test") + "\n执行获取参数完成");
        System.out.println(rh.getSize() + "\n执行获取SIZE完成");
        rh.clear();
        System.out.println("执行清除完成");
        rh.removeObject("test");
        System.out.println("执行移除完成");
        rh.putObject("test", "test");
        System.out.println("执行插入完成");
    }
}
