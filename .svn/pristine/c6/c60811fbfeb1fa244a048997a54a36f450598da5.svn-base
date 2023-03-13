package com.springboot.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限拦截自定义注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})//注解只限于方法上
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface MenuAnnotation {
    String value();
}

