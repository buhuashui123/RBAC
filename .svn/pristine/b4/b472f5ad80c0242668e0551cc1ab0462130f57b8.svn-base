package com.springboot.in;

import com.springboot.pojo.UserAndRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface URDao {

    /**
     * 给用户添加角色
     * @param list 用户角色集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addUR(List<UserAndRole> list);

    /**
     * 删除用户角色
     * @param list 用户角色集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelUR(List<UserAndRole> list);
}
