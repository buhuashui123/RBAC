package com.springboot.in;

import com.springboot.annotations.TargetDataSource;
import com.springboot.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao {

    /**
     * 批量导入用户信息
     * @param list 用户信息集合
     * @return 返回值为0则添加成功，否则添加失败
     */
    @TargetDataSource("d2")
    int addTeacher(List<Teacher> list);

    /**
     * 查询用户信息
     * @return 返回值为用户信息集合
     */
    @TargetDataSource("d2")
    List<Teacher> selectAll();
}
