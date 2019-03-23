package com.architect.bean;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author jiawx
 * @date 2019/3/23
 */
public class InitSequenceBean implements InitializingBean {

    public InitSequenceBean() {
        System.out.println("InitSequenceBean: construct");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitSequenceBean: postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitSequenceBean: afterPropertiesSet");
    }

    public void initMethod() {
        System.out.println("InitSequenceBean: initMethod");
    }
}
