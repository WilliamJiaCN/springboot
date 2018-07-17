package com.architect.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${welcome.message}")
    private String welcomeMessage;
    @Value("${my.info}")
    private String myInfo;
    @Value("${random.string}")
    private String randomStrVal;
    @Value("${random.number}")
    private String randomNumVal;
    @Value("${random.long}")
    private String randomLongVal;
    @Value("${random.range}")
    private String randomRangeVal;

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/getMsg")
    public String getMsg() {
        return welcomeMessage;
    }

    @RequestMapping("/getInfo")
    public String getInfo() {
        return myInfo;
    }

    @RequestMapping("/getRandom")
    public String getRandom() {
        System.out.println(randomStrVal);
        System.out.println(randomNumVal);
        System.out.println(randomLongVal);
        System.out.println(randomRangeVal);
        return randomRangeVal;
    }
}
