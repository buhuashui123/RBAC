package com.springboot.service.impl;

import com.springboot.in.URDao;
import com.springboot.pojo.UserAndRole;
import com.springboot.service.URService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class URServiceImpl implements URService {

    @Resource
    private URDao urDao;


    @Override
    public int addUR(UserAndRole userAndRole) {
        //创建一个集合
        List<UserAndRole> list = new ArrayList<>();
        //把数据存入集合中
        list.add(userAndRole);
        //调用持久层的添加方法
        return urDao.addUR(list);
    }

    @Override
    public int DelUR(UserAndRole userAndRole) {
        //创建一个集合
        List<UserAndRole> list = new ArrayList<>();
        //把数据存入集合中
        list.add(userAndRole);
        //调用持久层的删除方法
        return urDao.DelUR(list);
    }
}
