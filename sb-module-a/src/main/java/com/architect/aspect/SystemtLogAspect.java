package com.architect.aspect;

import com.architect.util.AnnotationParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wenxiong.jia
 * @since 2018/8/12
 */
@Slf4j
@Aspect
@Component
public class SystemtLogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.architect.annotation.SystemLogAnnotation)")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            System.out.println("开始解析注解");
            String desc = AnnotationParseUtil.parseSystemLogAnnotation(joinPoint);
            System.out.println("获取到注解值：" + desc);
            System.out.println("验证处理...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 后置通知
     */
    @After("controllerAspect()")
    public void doAfter() {
        try {
            System.out.println("记录日志");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
