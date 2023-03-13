package com.springboot.service;

import com.springboot.pojo.User;

import java.util.List;

/**
 * 用户业务
 */
public interface UserService {

    /**
     * 添加用户
     * @param user 用户对象
     * @return 返回用户
     */
    User addUser(User user);

    /**
     * 删除用户
     * @param user 用户对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelUser(User user);

    /**
     * 修改用户信息
     * @param user 用户对象
     * @return 返回值大于0则修改成功，否则修改失败
     */
    int UpUser(User user);

    /**
     * 查询所有用户信息
     * @return 返回用户集合
     */
    List<User> selectAll();

    /**
     * 切换数据源查询用户信息
     * @return 返回用户集合
     */
    List<User> selectAllByPL();

    /**
     * 根据用户名返回用户对象
     * @param loginName 账号
     * @param loginPass 密码
     * @return 返回用户对象
     */
    User loginByLoginName(String loginName,String loginPass);

    /**
     * 模糊查询用户信息
     * @param loginName 用户账号具部信息
     * @return 返回值用户集合
     */
    List<User> selectUserByLoginName(String loginName);


}
