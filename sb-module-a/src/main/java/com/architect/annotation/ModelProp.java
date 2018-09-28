package com.architect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenxiong.jia
 * @since 2018/9/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModelProp {
    String name();

    int colIndex() default -1;

    boolean nullable() default true;

    String interfaceXmlName() default "";
}
