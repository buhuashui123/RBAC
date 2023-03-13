package com.springboot.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换数据源自定义注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})//注解只限于方法上
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface TargetDataSource {

    String value();
}
