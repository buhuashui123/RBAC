package com.springboot.service;

import com.springboot.pojo.Teacher;
import org.springframework.ui.ModelMap;

import java.io.InputStream;
import java.util.List;

public interface TeacherService {


    /**
     * 使用批量导入功能
     * @param in 流
     * @return 最终结果
     */
    ModelMap addTeacher(InputStream in, ModelMap map);

    /**
     * 查询用户信息
     * @return 返回值为用户信息集合
     */
    List<Teacher> selectAll();
}
