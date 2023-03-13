package com.springboot.service;

import com.springboot.pojo.User;
import com.springboot.pojo.UserRPojo;

import java.util.List;

/**
 * 用户申请业务
 */
public interface UserRService {

    /**
     * 删除某个用户的某个权限
     * @param userRPojo 用户申请对象
     * @return
     */
    int DelUserR(UserRPojo userRPojo);

    /**
     * 根据当前用户ID查询出他的申请页面
     * @param userID 用户ID
     * @return 返回值是用户集合
     */
    List<User> selectAll(String userID);

    /**
     * 添加多个用户 的多个申请
     * @param userRPojo 用户申请对象
     * @return 返回值大于1则添加成功，否则添加失败
     */
    int addUserR(UserRPojo userRPojo);

}
