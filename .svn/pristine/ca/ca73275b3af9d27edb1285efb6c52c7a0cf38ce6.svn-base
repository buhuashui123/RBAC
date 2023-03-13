package com.springboot.service;

import com.springboot.pojo.Menu;

import java.util.List;

//权限业务
public interface MenuService {

    /**
     * 添加多个权限
     * @param menu 权限对象
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addMenu(Menu menu);

    /**
     * 删除多个权限
     * @param menu 权限对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelMenu(Menu menu);

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
