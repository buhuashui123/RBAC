package com.springboot.dataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册动态数据源
 * 初始化数据源和提供了执行动态切换数据源的工具类
 * EnvironmentAware （获取配置文件的（配置的属性值））
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    //指定默认数据源（springboot2.0默认数据源是ikarih如何使用其他数据源可以自己配置）
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";
    //默认数据源
    private DataSource defaultDataSource;
    //用户自定义数据源
    private Map<String,DataSource> slaveDataSources = new HashMap<>();

    /**
     * 加载多数据源配置
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initSlaveDataSources(environment);
    }

    //设置默认数据源
    private void initSlaveDataSources(Environment env) {
        //读取主数据源
        HashMap<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver",env.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url",env.getProperty("spring.datasource.url"));
        dsMap.put("username",env.getProperty("spring.datasource.username"));
        dsMap.put("password",env.getProperty("spring.datasource.password"));
        //创建数据源
        defaultDataSource = buildDataSource(dsMap);
        System.out.println("默认数据库设置成功");
    }

    //设置其他的数据源
    private void initDefaultDataSource(Environment env) {
        //读取配置文件获取更多的数据源
        String dsPrefix = env.getProperty("slave.datasource.names");
        for (String s : dsPrefix.split(",")) {
            //多个数据源
            HashMap<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver",env.getProperty("slave.datasource."+s+".driver"));
            dsMap.put("url",env.getProperty("slave.datasource."+s+".url"));
            dsMap.put("username",env.getProperty("slave.datasource."+s+".username"));
            dsMap.put("password",env.getProperty("slave.datasource."+s+".password"));
            DataSource dataSource = buildDataSource(dsMap);
            slaveDataSources.put(s,dataSource);
            System.out.println("数据库"+s+"设置成功");
        }
    }

    private DataSource buildDataSource(HashMap<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driver = dsMap.get("driver").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create().driverClassName(driver).url(url).username(username).password(password);
            return dataSourceBuilder.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String,DataSource> targetDataSources = new HashMap<>();
        //添加默认数据源
        targetDataSources.put("dataSource",this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIDs.add("dataSource");
        //添加其他数据源
        targetDataSources.putAll(slaveDataSources);
        for (String s : slaveDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIDs.add(s);
        }
        //创建动态资源
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource",defaultDataSource);
        mpv.addPropertyValue("targetDataSources",targetDataSources);

        //注册beanDefinition
        registry.registerBeanDefinition("dataSource",beanDefinition);
        System.out.println("-------------------");
    }
}
