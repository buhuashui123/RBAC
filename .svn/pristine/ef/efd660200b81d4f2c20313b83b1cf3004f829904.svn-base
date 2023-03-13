package com.springboot.config;

import com.springboot.interceptors.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 登录验证
 */

/*
 * 控制器（注入服务） 用于标注控制层
 */
@Controller
/*
（把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）泛指各种组件，
就是说当我们的类不属于各种归类的时候（不属于@Controller、@Service等的时候），
我们就可以使用@Component来标注这个类。
 */
@Component
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //要拦截的路径
        registration.addPathPatterns("/user/*","/role/*","/menu/*","/group/*");
        //放行不拦截的路径
        registration.excludePathPatterns("/user/login","/user/loginUser","/user/add","/user/addUser");
    }
}
