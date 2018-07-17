package com.architect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @RequestMapping("/helloThymeleaf")
    public String helloThymeleaf(ModelMap map) {
        //设置属性
        map.addAttribute("message", "Hello Thymeleaf!");
        //helloThymeleaf为模板文件的名称
        //对应src/main/resources/templates/helloThymeleaf.html
        return "helloThymeleaf";
    }
}
