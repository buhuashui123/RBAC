package com.springboot.in;

import com.springboot.annotations.TargetDataSource;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 添加多个用户信息
     * @param list 用户集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addUser(List<User> list);

    /**
     * 删除多个用户信息
     * @param list 用户集合
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelUser(List<User> list);

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
    @TargetDataSource("d1")
    List<User> selectAllByPL();

    /**
     * 根据账号返回用户对象
     * @param loginName 账号
     * @return 返回用户对象
     */
    User loginByLoginName(String loginName);

    /**
     * 模糊查询用户信息
     * @param loginName 用户账号具部信息
     * @return 返回值用户集合
     */
    List<User> selectUserByLoginName(String loginName);

}
