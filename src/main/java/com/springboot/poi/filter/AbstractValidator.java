package com.springboot.poi.filter;

import com.springboot.poi.dto.ResultValidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *数据校验器
 *
 * @param <T>
 *           待校验的数据类型
 * @param <R>
 *           效验完成后返回的数据类型
 */
public class AbstractValidator<T,R extends ResultValidate> implements Filter{

    /**
     * 对待过滤的数据进行校验，不合法的数据将会被过滤掉，同时将校验的结果作为一个集合返回
     *
     * @param coll
     *        待过滤的数据集合
     * @return
     *        校验结果的集合
     */
    @Override
    public Collection doFilter(Collection coll) {
        if(coll == null){
            return null;
        }
        //创建要返回的错误集合
        List<R> reList = new ArrayList<>();
        //遍历需要校验的所有数据
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            //获取集合中的数据
            T bean = (T)iterator.next();
            //进行数据校验
            R r = validate(bean);
            //如果数据不合法，添加到要返回的错误集合，从原集合中删除  !isValid(r) = false
            if (!isValid(r)) {
                //将错误数据添加错误集合中
                reList.add(r);
                //删除原集合中错误的数据
                iterator.remove();
            }

        }
        return reList;
    }

    /**
     * 针对于每条数据进行校验，返回该条数的校验结果
     *
     * @param t
     *        待校验的数据
     * @return
     *        由于此方法需要被子类重写，因此这里只能返回null
     */
    public R validate(T t){
        return null;
    }

    /**
     * 是否合法，之力针对的是每一条数据
     *
     * @param r
     *         数据的校验结果
     * @return
     *         依据数据的校验结果判断是否合法，合法则返回true r == null(true)
     */
    protected boolean isValid(R r){
        return r == null;
    }
}
