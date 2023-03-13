package com.springboot.controller;

import com.springboot.pojo.Teacher;
import com.springboot.pojo.User;
import com.springboot.service.TeacherService;
import com.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("text")
public class HelloController {

    @Resource
    private UserService userService;

    @Resource
    private TeacherService teacherService;

    @RequestMapping("hello")
    public String hello(){
        return "/text/hello";
    }

    //权限不足返回错误页面
    @RequestMapping("error")
    public String error(){
        return "/text/error";
    }


    /**
     * 切换数据源测试
     * @return
     */
    @RequestMapping("show")
    @ResponseBody
    public String show(){
        List<User> userList = userService.selectAll();
        List<User> userLists = userService.selectAllByPL();
        List<Teacher> teacherList = teacherService.selectAll();

        System.out.println(userList.toString());
        System.out.println(userLists.toString());
        System.out.println(teacherList.toString());
        return "OK";
    }

    /**
     * 批量导入页面
     * @return
     */
    @RequestMapping("addTeachers")
    public String addTeachers(){
        return "/text/addTeacher";
    }

    @RequestMapping("upLoad")
    public String upLoad(HttpServletRequest request, HttpServletResponse response, ModelMap m){
        InputStream in = null;
        MultipartFile fileName = ((MultipartHttpServletRequest) request).getFile("fileName");
        //获取文件名
        String name = fileName.getOriginalFilename();
        name = name.substring(name.lastIndexOf("."));

        if (name.equals(".xlsx")||name.equals(".xls")) {
            //获取文件流
            try {
                in = fileName.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            m = teacherService.addTeacher(in, m);
            return "/text/Finish";
        }else {
            m.addAttribute("msg","上传的文件是不合法的");
            return  "/uFriend/error";
        }



    }


}
