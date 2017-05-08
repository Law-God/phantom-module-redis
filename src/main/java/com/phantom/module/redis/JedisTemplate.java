package com.phantom.module.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-module-redis
 * phantom-module-redis
 * com.phantom.module.redis.JedisTemplate
 * 2017-01-10 15:00
 */
@Repository("jedisTemplate")
public class JedisTemplate {
    private static final Logger log = Logger.getLogger(JedisTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    public void disConnect(){
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    public String set(String key,String value){
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if(shardedJedis == null){
            log.debug("redis缓存key："+key+"；值："+value+"；失败;ShardedJedis=null");
            return result;
        }
        boolean broken = false;
        try{
            result = shardedJedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis缓存key："+key+"；值："+value+"；失败;", e);
            broken = true;
        }finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        log.debug("redis缓存key："+key+"；值："+value+"；成功");
        return result;
    }




    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
}
