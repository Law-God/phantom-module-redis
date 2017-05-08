package com.phantom.module.redis;

import com.phantom.util.network.IpConnectUtil;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-module-email
 * phantom-module-redis
 * com.phantom.module.redis.TestJedisTemplate
 * 2017-01-10 15:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class JedisTemplateTest {
    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private Jedis jedis;

    @Before
    public void isConnect(){
        Assert.assertTrue(IpConnectUtil.isHostConnectable("localhost",6379));
    }

    @Test
    public void set(){
        jedisTemplate.set("jedis","jedisvalue");

    }

    @Test
    public void jedis(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("site","www.baidu.com");
        Assert.assertEquals("www.baidu.com",jedis.get("site"));
    }
}
