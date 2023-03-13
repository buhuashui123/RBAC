package com.springboot.service;

import com.springboot.pojo.Group;

import java.util.List;

public interface GroupService {

    /**
     * 添加多个群对象
     * @param  group 群对象
     * @return 返回值大于0则添加成功，否则添加失败
     */
    Group addGroup(Group group);

    /**
     * 删除多个群对象
     * @param group 群对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelGroup(Group group);

    /**
     * 查询所有群
     * @return 群群对象集合
     */
    List<Group> selectAll();

    /**
     * 模糊查询
     * @param account 根据群账号查群
     * @return 返回值群对象集合
     */
    List<Group> selectAllByAccount(String account);
}
