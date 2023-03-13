package com.springboot.service;

import com.springboot.pojo.Role;

import java.util.List;

/**
 * 角色业务
 */
public interface RoleService {

    /**
     * 添加角色
     * @param role 角色对象
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addRole(Role role);

    /**
     * 删除角色信息
     * @param role 角色对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelRole(Role role);

    /**
     * 查询所有角色信息
     * @return 返回角色集合
     */
    List<Role> selectAll();

    /**
     * 修改角色信息
     * @param role 角色对象
     * @return 返回值大于0则修改成功，否则修改失败
     */
    int UpRole(Role role);
}
