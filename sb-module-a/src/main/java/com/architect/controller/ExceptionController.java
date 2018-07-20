package com.architect.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenxiong.jia
 * @since 2018/7/20
 */
@RestController
public class ExceptionController {

    @RequestMapping("/helloExp")
    public String helloExp() throws Exception {
        throw new Exception("统一异常处理");
    }

    @RequestMapping("/helloErrorCode")
    public String helloErrorCode() {
        int num = 1 / 0;
        return null;
    }

//    @RequestMapping("/error/500")
//    public String handleSpecialExp() {
//        return "Special Exception Message";
//    }
}
