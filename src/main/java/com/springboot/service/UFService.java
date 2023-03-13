package com.springboot.service;

import com.springboot.pojo.UFPojo;
import com.springboot.pojo.User;

import java.util.List;

/**
 * 用户好友业务
 */
public interface UFService {

    /**
     * 根据用户ID查询他对应的好友
     * @param userID 用户ID
     * @return 返回值用户集合
     */
    List<User> selectAll(String userID);

    /**
     * 添加好友
     * @param ufPojo 用户好友对象
     * @return 返回值大于1则添加成功，否则添加失败
     */
    int addUF(UFPojo ufPojo);

    /**
     * 删除好友
     * @param ufPojo 用户好友对象
     * @return 返回值大于1则添加成功，否则添加失败
     */
    void DelUF(UFPojo ufPojo);
}
