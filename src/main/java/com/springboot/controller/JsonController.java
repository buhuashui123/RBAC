package com.springboot.controller;

import com.springboot.pojo.Details;
import com.springboot.pojo.User;
import com.springboot.utils.SftpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("json")
public class JsonController {

    @RequestMapping("checkAvatar.ht")
    @ResponseBody
    public String file(@RequestParam("fileName") MultipartFile fileName, HttpServletRequest request){
        String path = null;
        InputStream in = null;
        String name = fileName.getOriginalFilename();
        name = name.substring(name.lastIndexOf("."));
        User user =(User) request.getSession().getAttribute("user");
        if (name.equals(".jpg")) {
            //获取文件流
            try {
                in = fileName.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SftpUtils.login();
            String s = SftpUtils.upload("sb", UUID.randomUUID() + ".jpg", in);
            path = "http://192.168.80.128/xiaoma/sb/"+s;
            SftpUtils.closeServer();
        }else {
            System.out.println("图片违规");
            path = "http://192.168.80.128/xiaoma/imgs/666.jpg";
        }
        return path;
    }


}
