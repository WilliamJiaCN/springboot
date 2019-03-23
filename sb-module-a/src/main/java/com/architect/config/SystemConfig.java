package com.architect.config;

import com.architect.bean.InitSequenceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiawx
 * @date 2019/3/23
 */
@Configuration
public class SystemConfig {

    @Bean(initMethod = "initMethod", name = "initSequenceBean")
    public InitSequenceBean initSequenceBean() {
        return new InitSequenceBean();
    }
}
