package com.architect.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@SpringBootApplication
@ComponentScan(value = "com.architect")
@MapperScan(value = "com.architect.mapper")
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class, args);
    }
}
