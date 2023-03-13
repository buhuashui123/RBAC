package com.springboot.service.impl;

import com.springboot.in.UFDao;
import com.springboot.pojo.UFPojo;
import com.springboot.pojo.User;
import com.springboot.pojo.UserRPojo;
import com.springboot.service.UFService;
import com.springboot.service.UserRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UFServiceImpl implements UFService {

    @Resource
    private UFDao ufDao;

    @Override
    public List<User> selectAll(String userID) {
        return ufDao.selectAll(userID);
    }

    @Override
    public int addUF(UFPojo ufPojo) {
        UserRPojo userRPojo = new UserRPojo(ufPojo.getUserID(),ufPojo.getFriendsID());
        int i = 0;
        //获取该用户的所有好友
        List<User> list = ufDao.selectAll(ufPojo.getUserID());
        //循环集合，判断好友是否已存在
        if (!list.isEmpty()) {
            for (User user : list) {
                if (user.getUserID().equals(ufPojo.getFriendsID())) {
                    //好友已存在
                    i = 1;
                }
            }
        }
        if (i == 0){
            //创建一个新集合
            List<UFPojo> lists = new ArrayList<>();
            //把对象存进去
            lists.add(ufPojo);
            //调用持久层添加好友方法
            ufDao.addUF(lists);
        }
        return i;
    }

    @Override
    public void DelUF(UFPojo ufPojo) {
        //创建集合
        List<UFPojo> list = new ArrayList<>();
        //存入集合中
        list.add(ufPojo);
        //删除好友
        ufDao.DelUF(list);
    }
}
