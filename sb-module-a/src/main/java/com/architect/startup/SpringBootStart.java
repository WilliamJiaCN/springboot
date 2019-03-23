package com.architect.startup;

import com.architect.config.DefaultThreadPoolConfig;
import com.architect.config.Task1ThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(value = "com.architect")
//@MapperScan(value = "com.architect.dao")
@EnableAsync
@EnableConfigurationProperties(
        {
                Task1ThreadPoolConfig.class,
                DefaultThreadPoolConfig.class
        }
)
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStart.class);
        app.run(args);
    }
}
