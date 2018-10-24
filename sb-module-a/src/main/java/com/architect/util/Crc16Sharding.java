package com.architect.util;

import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/9/13
 */
public class Crc16Sharding {

    private List<RedisClusterClient> pool;

    public Crc16Sharding(List<RedisClusterClient> pool) {
        this.pool = pool;
    }

    /**
     * 通过key定位到一个节点
     */
    public RedisClusterClient crc16(String key) {
        int num = Math.abs(key.hashCode() % pool.size());
        return pool.get(num);
    }
}
