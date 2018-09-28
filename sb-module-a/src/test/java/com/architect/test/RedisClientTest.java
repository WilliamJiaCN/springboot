package com.architect.test;

import com.architect.utils.Crc16Sharding;
import com.architect.utils.RedisClusterClient;
import org.assertj.core.util.Lists;

import java.io.IOException;
import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/9/13
 */
public class RedisClientTest {
    public static void main(String[] args) throws IOException {
        List<RedisClusterClient> pool = Lists.newArrayList();
        pool.add(new RedisClusterClient("60.205.212.141", 6379));
        pool.add(new RedisClusterClient("60.205.212.141", 6380));
        pool.add(new RedisClusterClient("60.205.212.141", 6381));
        Crc16Sharding crc16Sharding = new Crc16Sharding(pool);
        String key = "k99";
        RedisClusterClient client = crc16Sharding.crc16(key);
        client.set(key, 1 + "");
        System.out.println(client.get(key));
    }
}
