package com.architect.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.architect")
public class SbModuleAApplication {
    public static void main(String[] args) {
        SpringApplication.run(SbModuleAApplication.class, args);
    }
}
