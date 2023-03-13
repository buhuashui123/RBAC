package com.springboot.in;

import com.springboot.pojo.User;
import com.springboot.pojo.UserRPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRDao {

    /**
     * 根据当前用户ID查询出他的申请页面
     * @param userID 用户ID
     * @return 返回值是用户集合
     */
    List<User> selectAll(String userID);


    /**
     * 删除某个用户的某个申请请求（拒绝）
     * @param userRPojo 用户申请对象
     * @return
     */
    int DelUserR(UserRPojo userRPojo);

    /**
     * 添加多个用户 的多个申请
     * @param list 用户申请集合
     * @return 返回值大于1则添加成功，否则添加失败
     */
    int addUserR(List<UserRPojo> list);

}
