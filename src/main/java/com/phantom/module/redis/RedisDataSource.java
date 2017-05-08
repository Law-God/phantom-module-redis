package com.phantom.module.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-module-redis
 * phantom-module-redis
 * com.phantom.module.redis.RedisDataSource
 * 2017-01-10 14:54
 */
public interface RedisDataSource {
    ShardedJedis getRedisClient();

    void returnResource(ShardedJedis shardedJedis);
    void returnResource(ShardedJedis shardedJedis,boolean broken);
}
