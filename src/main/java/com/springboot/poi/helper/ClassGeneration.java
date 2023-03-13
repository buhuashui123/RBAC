package com.springboot.poi.helper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 类转化器
 * 生成类对象
 */
@Component
public class ClassGeneration<T> {

    /**
     * 获取类的所有属性名
     * @param tClass 传入类型
     * @return 所有属性名
     */
    public String[] getAttributeName (Class<T> tClass){
        //获取到类的所有属性包含私有的
        Field[] fields = tClass.getDeclaredFields();
        //判断所获取到的类的属性是否为空 若为空则不运行下面代码  提高效率
        if (ArrayUtils.isEmpty(fields)) {
            return null;
        }
        //将获取到的属性的属性名赋值给一个新的数组
        String[] attributeName = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            attributeName[i] = fields[i].getName();
        }
        return attributeName;
    }

    /**
     * 创建对象，传入的值数组的顺序需要与类的属性的顺序一致
     * {"id","name","age"}<br>
     * {1,"zs",new Integer(30)}
     *
     * @param tClass
     *     Class实列
     * @param objects
     *     要设置的属性的值数组
     * @return
     *     利用反射机制创造的clazz类的对象，值数组为空时返回null
     */
    public T generateBean(Class<T> tClass,Object[] objects){
        //获取类的所有属性名（包括私有的）
        String[] attributeName = getAttributeName(tClass);
        //判断获取到的存储属性名的数组是否为空
        if (ArrayUtils.isEmpty(attributeName)){
            return null;
        }
        //获取属性长度
        int len = attributeName.length;
        //判断获取到的数组是否为空
        if(ArrayUtils.isEmpty(objects)){
            return null;
        }
        //截取excel数据
        Object[] objects1 = ArrayUtils.subarray(objects, 0, len);
        //创建对象
        T t = null;
        try {
            //对传过来的类进行实例化
            t = tClass.newInstance();
            //使用三元的方式判断使用属性数组的长度还是数据数组的长度 防止下标越界异常
            int s = attributeName.length > objects1.length ? objects1.length : attributeName.length;
            for (int i = 0; i < s; i++) {
                //为类的属性进行赋值
                    BeanUtils.setProperty(t,attributeName[i],objects1[i]);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 验证excle表头
     * @param titles  规定的表头
     * @param map excel的数据
     * @return true验证通过 false模板不对
     */
    public boolean validSheetTitles(String[] titles, Map<String, List<Object[]>> map){
        boolean flag = false;
        //遍历获取到的excel数据 以得到表头数据
        for (Map.Entry<String, List<Object[]>> entry : map.entrySet()) {
            List<Object[]> value = entry.getValue();
            Object[] objects = value.get(0);
            flag = Arrays.equals(objects, titles);
            if (flag){
                break;
            }
        }
        return flag;
    }

    /**
     * 按照指定的模板格式读取excel数据，封装至Map<String,List<T>>对象集合中
     * map的键值对的格式是：sheet页名称，List<T>
     *
     * @param map
     *        读取excel的数据
     * @param titles
     *        模板格式信息
     * @param tClass
     *        类实例
     * @return 当excel数据不符合指定的模板的格式时将抛出
     * InvalidExcelTemplateException异常
     */
    public Map<String, List<T>> readToBeanMap(Map<String, List<Object[]>> map,  String[] titles, Class<T> tClass){
        Map<String, List<T>> maps = new HashMap<>();
        //验证表头
        boolean b = validSheetTitles(titles, map);
        if (!b) {
            System.out.println("表头验证失败");
            return null;
        }
        //得到Map集合中的value值 即存储的数据
        for (Map.Entry<String, List<Object[]>> stringListEntry : map.entrySet()) {
            List<T> list = new ArrayList<>();
            //判断表格表头数据与模板是否一致
            Object[] objects1 = stringListEntry.getValue().get(0);
            //判断表头是否一致
            if (Arrays.equals(objects1,titles)){
                //删除表头数据
                stringListEntry.getValue().remove(0);
            }
            List<Object[]> objects = stringListEntry.getValue();
            for (Object[] object : objects) {
                //调用方法将数据对属性进行赋值
                T t = generateBean(tClass, object);
                //把转换的数据存入新集合中
                list.add(t);
            }
            // 将list集合作为value存储到Map集合中
            maps.put(stringListEntry.getKey(),list);
        }
        return maps;
    }
}
