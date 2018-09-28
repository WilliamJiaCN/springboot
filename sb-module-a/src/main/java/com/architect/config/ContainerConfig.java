//package com.architect.config;
//
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
///**
// * @author wenxiong.jia
// * @since 2018/7/20
// */
//@Configuration
//public class ContainerConfig {
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return container -> container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
//    }
//}
