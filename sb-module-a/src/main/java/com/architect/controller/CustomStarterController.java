package com.architect.controller;

import com.architect.service.CustomStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenxiong.jia
 * @since 2018/7/22
 */
@RestController
@RequestMapping("/customStarter")
public class CustomStarterController {

    @Autowired
    private CustomStarterService customStarterService;

    @GetMapping("/getMsg")
    public String getMsg(){
        return customStarterService.sayHello();
    }
}
