package com.springboot.config;

import com.springboot.register.SysDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * 完成Thymeleaf自定义方言类的初始化
 */
@Configuration
public class ThymeleafDialectConfig {
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Bean
    public SysDialect sysDialect(){
        return new SysDialect(redisTemplate);
    }
}
