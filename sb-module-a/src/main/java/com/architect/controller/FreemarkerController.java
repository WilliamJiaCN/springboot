package com.architect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @RequestMapping("/helloFreemarker")
    public String helloFreemarker(Map<String,String> map) {
        map.put("message", "Hello Freemarker!");
        //helloFreemarker为模板文件的名称
        //对应src/main/resources/templates/helloFreemarker.ftl
        return "helloFreemarker";
    }
}
