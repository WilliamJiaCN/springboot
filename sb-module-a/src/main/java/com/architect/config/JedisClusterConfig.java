package com.architect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wenxiong.jia
 * @since 2018/9/11
 */
@Configuration
public class JedisClusterConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 注意：
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     */
    @Bean
    public JedisCluster getJedisCluster() {
        //获取配置文件中服务器地址
        String[] serverArray = redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            jedisClusterNodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(jedisClusterNodes);
    }
}
