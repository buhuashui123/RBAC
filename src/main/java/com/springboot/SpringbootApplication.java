package com.springboot;

import com.springboot.dataSource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 小马在线摆烂666
 */
/**
 * 赐赐在线烧脑
 */
/**
 * 小洋一直摆烂 永远的神
 */

/**
 *一直摆烂
 */
@SpringBootApplication
@MapperScan("com.springboot.in")
@Import({DynamicDataSourceRegister.class})
@RequestMapping("spring")
public class SpringbootApplication {

    private static ConfigurableApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(SpringbootApplication.class, args);
    }

    @RequestMapping("closeContext")
    public void close(){
        if (context!=null){
            context.close();

        }

    }

}
