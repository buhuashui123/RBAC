package com.springboot.interceptors;

import com.springboot.annotations.MenuAnnotation;
import com.springboot.pojo.Menu;
import com.springboot.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 菜单拦截器 《权限拦截》
 */
public class MenuInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    public MenuInterceptor() {
        System.out.println("菜单拦截器初始化");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取调用的方法
        Method method = ((HandlerMethod) handler).getMethod();
        //获取方法上的注解
        MenuAnnotation annotation = method.getAnnotation(MenuAnnotation.class);
        //序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        if(annotation==null){
            return true;
        }
        //获取登陆后的绑定标识
        User user =(User) request.getSession().getAttribute("user");
        //获取用户ID
        String userID = user.getUserID();
        //获取用户权限
        List<Menu> list =(List<Menu>) redisTemplate.opsForValue().get(userID);
        for (Menu m : list) {
            if(m.getMenuCode().equals(annotation.value())){
                return true;
            }
        }
        //重定向到错误页面
        response.sendRedirect("/text/error");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
