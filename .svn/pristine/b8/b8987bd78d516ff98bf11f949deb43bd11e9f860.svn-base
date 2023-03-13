package com.springboot.in;

import com.springboot.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao {

    /**
     * 添加多个权限
     * @param list 权限对象集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addMenu(List<Menu> list);

    /**
     * 删除多个权限
     * @param list 权限对象集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelMenu(List<Menu> list);

    /**
     * 查询所有权限
     * @return 返回值是权限对象集合
     */
    List<Menu> selectAll();


    /**
     * 使用递归的方式查出全量有序的菜单
     * @param pID 父级菜单
     * @return 返回值是权限菜单集合
     */
    List<Menu> selectAllByPID(int pID);

    /**
     * 根据用户ID链表查询用户的菜单
     * @param userID 用户ID
     * @return 返回值是权限菜单集合
     */
    List<Menu> selectAllByUserID(String userID);
}
