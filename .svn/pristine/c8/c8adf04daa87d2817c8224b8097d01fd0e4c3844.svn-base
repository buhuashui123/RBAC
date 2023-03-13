package com.springboot.config;

import com.springboot.pojo.Menu;
import com.springboot.service.MenuService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
使用注解@Configuration，告诉Spring Boot这是一个配置类。
 在项目初始化的时候把全量有序的菜单存入redis里，
 关闭项目的时候，把redis清空
*/

@Component
@Configuration
public class RedisConfig implements Serializable {
    private static final long serialVersionUID = -3022897777020793558L;
    @Resource
    private MenuService menuService;
    //引用缓存
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    //初始化
    @PostConstruct
    public void init(){
        //使用前对rediskey进行序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //获取有序菜单
        List<Menu> list = menuService.selectAllByPID(0);
        //判断缓存是否存在
        if(redisTemplate.opsForValue().get("menuList") != null){
            //存在干掉
            redisTemplate.delete("menuList");
        }
        //放入新的到redis中
        redisTemplate.opsForValue().set("menuList",list);
    }

    @PreDestroy
    public void destroy(){
        //使用前对rediskey进行序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //如果存在干掉它
        redisTemplate.delete("menuList");
        System.out.println("项目结束");
    }
}
