package com.springboot.in;

import com.springboot.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    /**
     * 添加角色
     * @param list 角色对象集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addRole(List<Role> list);

    /**
     * 删除多个角色信息
     * @param list 角色对象集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelRole(List<Role> list);

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
