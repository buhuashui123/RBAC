package com.springboot.service.impl;

import com.springboot.in.TeacherDao;
import com.springboot.poi.dto.ResultRow;
import com.springboot.poi.filter.TeacherFilterChain;
import com.springboot.poi.helper.ClassGeneration;
import com.springboot.poi.utils.ExcelsUtil;
import com.springboot.pojo.Teacher;
import com.springboot.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherDao teacherDao;


    @Override
    public ModelMap addTeacher(InputStream in, ModelMap map) {
        //获取工作簿的数据
        Map<String, List<Object[]>> maps = ExcelsUtil.getMap(in);
        //获取表头
        String [] obj = {"序号","姓名","年龄","性别","手机"};
        //进行类型转换
        ClassGeneration<Teacher> classGeneration = new ClassGeneration<>();
        Map<String, List<Teacher>> readMap = classGeneration.readToBeanMap(maps, obj, Teacher.class);
        List<Teacher> teList = new ArrayList<>(); //正确数据的集合
        List<ResultRow> reList = new ArrayList<>(); //错误信息的集合
        //过滤器链
        TeacherFilterChain vol = new TeacherFilterChain();

        int sum = 0;
        for (Map.Entry<String, List<Teacher>> listEntry : readMap.entrySet()) {
            List<Teacher> value = listEntry.getValue();
            sum = sum+value.size();
            reList.addAll(vol.doFilter(value)); //过滤出来错误的数据并存入集合中
            teList.addAll(value); //过滤后，把成功的数据存入集合中

        }

        teacherDao.addTeacher(teList);
        map.addAttribute("sum",sum);
        map.addAttribute("trueNum",teList.size());
        map.addAttribute("falseNum",reList.size());
        map.addAttribute("errorList",reList);
        return map;
    }

    @Override
    public List<Teacher> selectAll() {
        return teacherDao.selectAll();
    }
}
