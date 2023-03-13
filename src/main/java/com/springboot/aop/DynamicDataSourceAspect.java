package com.springboot.aop;

import com.springboot.annotations.TargetDataSource;
import com.springboot.dataSource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态数据源通知
 */
@Component
@Aspect
@Order(-10)//数值越小，越优先加载保证aop在@TargetDataSource之前执行
public class DynamicDataSourceAspect {

    /**
     * 改变数据源，拦截注解
     * @param joinPoint
     * @param targetDataSource 注解
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
        //获取当前指定数据源
        String nameID = targetDataSource.value();
        System.out.println(nameID);
        //数据源不存在则使用默认的
        if (!DynamicDataSourceContextHolder.isContainsDataSource(nameID)) {
            //joinPoint.getSignature() 获取链接点的方法签名对象
            System.out.println("数据源："+nameID+"不存在使用默认的数据源-》"+joinPoint.getSignature());
        }else{
            //存在设置到上下文
            System.out.println("使用数据源："+nameID);
            DynamicDataSourceContextHolder.setDataSourceType(nameID);
        }

    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint joinPoint,TargetDataSource targetDataSource){
        System.out.println("清空数据源："+targetDataSource.value());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
