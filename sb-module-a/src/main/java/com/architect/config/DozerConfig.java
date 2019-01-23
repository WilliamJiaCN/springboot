package com.architect.config;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author wenxiong.jia
 * @since 2019/1/4
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapperFactoryBean dozerMapper(
            ResourcePatternResolver resourcePatternResolver) throws IOException {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        factoryBean.setMappingFiles(
                resourcePatternResolver.getResources("classpath*:/*mapping.xml"));
        return factoryBean;
    }
}
