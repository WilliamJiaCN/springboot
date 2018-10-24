package com.architect.test;

import com.architect.startup.SpringBootStart;
import com.architect.util.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisClient redisClient;

    @Test
    public void testRedis() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "william");
        System.out.println(valueOperations.get("name"));
    }

    @Test
    public void testRedisCluster() {
        redisClient.set("k2", "2");
        System.out.println(redisClient.get("k2"));
    }
}
