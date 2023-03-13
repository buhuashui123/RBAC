package com.springboot.service;

import com.springboot.pojo.RoleAndMenu;

/**
 * 角色权限持久层
 */
public interface RMService {

    /**
     * 给角色添加权限
     * @param roleID 角色ID
     * @param hobby 权限ID数组
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addRM(int roleID,int hobby[]);

    /**
     * 给角色删除权限
     * @param roleAndMenu 角色权限对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelRM(RoleAndMenu roleAndMenu);

}
