package com.springboot.poi.filter;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *过滤器链：
 * 待过滤的数据依次经过链上的各个过滤器，层层过滤
 *
 * @param <T>
 *            待过滤的数据类型
 * @param <R>
 *            过滤完成后返回的数据类型
 */
public abstract class AbstractFilterChain<T,R>{

    //过滤器集合
    public List<Filter<T,R>> filterList = new ArrayList<>();

    /**
     * 过滤数据
     * @param list
     *             待过滤的数据类型
     * @return
     *         将各个过滤器过滤完后返回的数据集合汇总
     */
    public List<R> doFilter(List<T> list){
        //如果待过滤的数据为空，则不需要过滤
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        //创建存放错误信息的集合
        List<R> reList = new ArrayList<>();
        //遍历过滤器集合
        for (Filter<T, R> filters : filterList) {
            //由各个过滤器进行层层过滤
            Collection<R> resList =  filters.doFilter(list);
            if (CollectionUtils.isNotEmpty(resList)){
                //汇总各个过滤器返回的数据
                reList.addAll(resList);
            }
        }
        return reList;
    }


}
