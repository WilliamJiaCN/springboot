package com.architect.controller;

import com.architect.annotation.SystemLogAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenxiong.jia
 * @since 2018/8/12
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @SystemLogAnnotation(description = "This is my annotation")
    @GetMapping("/index")
    public String index() {
        System.out.println("业务处理...");
        return "Hello Annotation";
    }

    @GetMapping("/getMsg")
    public String getMsg() {
        System.out.println("业务处理...");
        return "no my annotation";
    }
}
