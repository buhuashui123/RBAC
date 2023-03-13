package com.springboot.in;

import com.springboot.pojo.UFPojo;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UFDao {


    /**
     * 根据用户ID查询他对应的好友
     * @param userID 用户ID
     * @return 返回值用户集合
     */
    List<User> selectAll(String userID);

    /**
     * 添加好友
     * @param list 用户好友集合
     * @return 返回值大于1则添加成功，否则添加失败
     */
    int addUF(List<UFPojo> list);

    /**
     * 删除好友
     * @param list 用户好友集合
     * @return 返回值大于1则添加成功，否则添加失败
     */
    void DelUF(List<UFPojo> list);
}
