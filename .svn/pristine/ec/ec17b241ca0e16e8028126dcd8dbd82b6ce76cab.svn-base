package com.springboot.interceptors;

import com.springboot.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器 《登录验证》
 */
public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor() {
        System.out.println("登录拦截器初始化");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录过
        if (user!=null){
            //登录过
            return true;
        }else{
            response.sendRedirect("/user/login");
        }
        //未登录
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
