package com.architect.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wenxiong.jia
 * @since 2018/9/11
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String clusterNodes;

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}
