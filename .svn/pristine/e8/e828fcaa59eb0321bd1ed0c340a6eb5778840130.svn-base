package com.springboot.config;

import com.springboot.interceptors.MenuInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置权限拦截
 */
@Configuration
public class MenuInterceptorConfig implements WebMvcConfigurer {
    /**
    @Bean 用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean
     */
    @Bean
    public MenuInterceptor getMenuInterceptor(){
        return new MenuInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMenuInterceptor())
                //要拦截的路径
                .addPathPatterns("/user/DelUser");
    }
}
