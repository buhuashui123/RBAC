package com.springboot.service.impl;

import com.springboot.in.UFDao;
import com.springboot.in.UserRDao;
import com.springboot.pojo.UFPojo;
import com.springboot.pojo.User;
import com.springboot.pojo.UserRPojo;
import com.springboot.service.UFService;
import com.springboot.service.UserRService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRServiceImpl implements UserRService {

    @Resource
    private UserRDao userRDao;

    @Resource
    private UFDao ufDao;

    @Resource
    private UFService ufService;


    @Override
    public int DelUserR(UserRPojo userRPojo) {
        return userRDao.DelUserR(userRPojo);
    }

    @Override
    public List<User> selectAll(String userID) {
        return userRDao.selectAll(userID);
    }

    @Override
    public int addUserR(UserRPojo userRPojo) {
        int i =0;
        //查询当前用户的好友表
        List<User> users = ufDao.selectAll(userRPojo.getUserID());
        //遍历好友表
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                //好友已存在，拒绝发申请
                if (user.getUserID().equals(userRPojo.getRequestID())) {
                    i = 2;
                }
            }
        }
        //从数据库里查出申请者是否被申请
        List<User> userList = userRDao.selectAll(userRPojo.getUserID());
        //遍历循环，查一下被申请者是否存在
        if (CollectionUtils.isNotEmpty(userList)){
            for (User user : userList) {
                //被申请者已存在
                if (user.getUserID().equals(userRPojo.getRequestID())){
                    //被申请者存在，添加好友成功，删除被申请者的
                    //被申请者，申请者
                    userRDao.DelUserR(userRPojo);
                    i = 1;
                }
            }
        }
        if (i == 0){
            //不存在既发送申请
            List<UserRPojo> list = new ArrayList<>();
            UserRPojo userR = new UserRPojo(userRPojo.getRequestID(), userRPojo.getUserID());
            //避免重复申请
            userRDao.DelUserR(userR);
            //被申请者，申请者
            list.add(userR);
            userRDao.addUserR(list);
        }else{
            //如果被申请者已存在，直接同意好友
            UFPojo ufPojo = new UFPojo(userRPojo.getUserID(),userRPojo.getRequestID());
            //调用业务层的添加好友方法
            ufService.addUF(ufPojo);
        }
        return i;
    }


}
