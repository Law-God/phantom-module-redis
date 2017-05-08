package com.phantom.module.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-module-redis
 * phantom-module-redis
 * com.phantom.module.redis.RedisDataSourceImpl
 * 2017-01-10 14:54
 */
@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
    private static final Logger log = Logger.getLogger(RedisDataSourceImpl.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {
        try {
           return shardedJedisPool.getResource();
        }catch (Exception e){
            log.error("getRedisClent error",e);
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedisPool.close();
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if(broken){
            shardedJedisPool.close();
        }else{
            shardedJedisPool.close();
        }
    }
}
