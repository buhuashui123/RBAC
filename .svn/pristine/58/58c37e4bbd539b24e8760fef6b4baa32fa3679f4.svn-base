package com.springboot.in;

import com.springboot.pojo.GUPojo;
import com.springboot.pojo.Group;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GUDao {

    /**
     * 给不同的群添加多个用户
     * @param list 群用户对象集合
     * @return 返回值大于0则添加成功，否则添加失败
     */
    int addGU(List<GUPojo> list);

    /**
     * 删除不同的群的用户
     * @param  guPojo 群用户对象
     * @return 返回值大于0则删除成功，否则删除失败
     */
    int DelGU(GUPojo guPojo);

    /**
     * 根据群id查出该群的用户信息
     * @param id 群id
     * @return 返回用户的信息
     */
    List<User> selectAllByID(String id);

    /**
     * 根据用户id查出该用户的群信息
     * @param userID 用户ID
     * @return 返回用户的信息
     */
    List<Group> selectAllByUserID(String userID);
}
