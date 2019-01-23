package com.architect.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理，自定义异常处理方式之一
 *
 * @author wenxiong.jia
 * @since 2018/7/20
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error("参数校验出错！", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "出错了！");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
