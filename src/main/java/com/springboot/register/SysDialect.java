package com.springboot.register;

import com.springboot.custom.MenuIterator;
import com.springboot.custom.RoleBtnShowProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * 注册方言类
 */
public class SysDialect extends AbstractProcessorDialect {

    private RedisTemplate<Object,Object> redisTemplate;

    public SysDialect(RedisTemplate<Object,Object> redisTemplate) {
        super("zs", "pre", 1000);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        Set<IProcessor> set = new HashSet<>();
        set.add(new MenuIterator(redisTemplate));//注册自定义标签
        set.add(new RoleBtnShowProcessor(redisTemplate));//注册自定义属性
        return set;
    }
}
