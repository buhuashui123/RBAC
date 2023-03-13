package com.springboot.in;

import com.springboot.pojo.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupDao {

    /**
     * 添加多个群对象
     * @param list 群对象集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addGroup(List<Group> list);

    /**
     * 删除多个群对象
     * @param list 群对象集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelGroup(List<Group> list);

    /**
     * 查询所有群
     * @return 群对象集合
     */
    List<Group> selectAll();

    /**
     * 模糊查询
     * @param account 根据群账号查群
     * @return 返回值群对象集合
     */
    List<Group> selectAllByAccount(String account);

}
