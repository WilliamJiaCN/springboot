package com.architect.annotation;

import java.lang.annotation.*;

/**
 * @author wenxiong.jia
 * @since 2018/8/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation {
    String description() default "";
}
