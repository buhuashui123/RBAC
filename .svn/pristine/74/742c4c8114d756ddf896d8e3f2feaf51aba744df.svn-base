package com.springboot.service.impl;

import com.springboot.in.GUDao;
import com.springboot.pojo.GUPojo;
import com.springboot.pojo.Group;
import com.springboot.pojo.User;
import com.springboot.service.GUService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GUServiceImpl implements GUService {

    @Resource
    private GUDao guDao;


    @Override
    public int addGU(GUPojo guPojo) {
        List<GUPojo> list = new ArrayList<>();
        list.add(guPojo);
        return guDao.addGU(list);
    }

    @Override
    public int DelGU(GUPojo guPojo) {
        return guDao.DelGU(guPojo);
    }


    @Override
    public List<User> selectAllByID(String id) {
        return guDao.selectAllByID(id);
    }

    @Override
    public List<Group> selectAllByUserID(String userID) {

        return guDao.selectAllByUserID(userID);
    }
}
