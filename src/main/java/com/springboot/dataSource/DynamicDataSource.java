package com.springboot.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 每执行一次数据库，动态获取数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //设置当前数据源
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
