//package com.architect.controller;
//
//import org.springframework.boot.autoconfigure.web.BasicErrorController;
//import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
///**
// * @author wenxiong.jia
// * @since 2018/7/20
// */
////@Controller
//public class MyErrorController extends BasicErrorController {
//    public MyErrorController() {
//        super(new DefaultErrorAttributes(), new ErrorProperties());
//    }
//
//    @Override
//    @RequestMapping(value = "/500", produces = "text/html")
//    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        model.put("message", "自定义错误信息");
//        return new ModelAndView("error/500", model);
//    }
//
//    @Override
//    @RequestMapping(value = "/500")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus Status = getStatus(request);
//        return new ResponseEntity<>(body, Status);
//    }
//}
