package com.springboot.in;

import com.springboot.pojo.RoleAndMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RMDao {

    /**
     * 给角色添加权限
     * @param list 角色权限集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addRM(List<RoleAndMenu> list);

    /**
     * 给角色删除权限
     * @param list 角色权限集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelRM(List<RoleAndMenu> list);
}
