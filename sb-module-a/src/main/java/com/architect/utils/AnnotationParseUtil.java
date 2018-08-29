package com.architect.utils;

import com.architect.annotation.SystemLogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author wenxiong.jia
 * @since 2018/8/12
 */
public class AnnotationParseUtil {

    /**
     * 解析系统日志注解
     */
    public static String parseSystemLogAnnotation(JoinPoint joinPoint) throws Exception {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Class<?>[] paramenterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        Method method = targetClass.getMethod(joinPoint.getSignature().getName(), paramenterTypes);
        return method.getAnnotation(SystemLogAnnotation.class).description();
    }
}
