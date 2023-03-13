package com.springboot.service.impl;

import com.springboot.in.GRDao;
import com.springboot.in.GUDao;
import com.springboot.pojo.GRName;
import com.springboot.pojo.GRPojo;
import com.springboot.pojo.User;
import com.springboot.service.GRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GRServiceImpl implements GRService {

    @Resource
    private GRDao grDao;

    @Resource
    private GUDao guDao;


    @Override
    public int addGR(GRPojo grPojo) {
        int i = 0;
        //群里已有该用户，拒绝发送请求
        List<User> list = guDao.selectAllByID(grPojo.getId());
        for (User user : list) {
            if (user.getUserID().equals(grPojo.getUserID())) {
                i = 1;
            }
        }
        //群的所有申请里也有该用户，避免重复发送请求
        List<GRPojo> grPojoList = selectAllByID(grPojo.getId());
        for (GRPojo pojo : grPojoList) {
            if (pojo.getUserID().equals(grPojo.getUserID())) {
                i = 2;
            }
        }
        //没有任何情况，向群发送申请
        if (i == 0){
            List<GRPojo> grPojoLists = new ArrayList<>();
            grPojoLists.add(grPojo);
            grDao.addGR(grPojoLists);
         }
        return i;
    }

    @Override
    public int DelGR(GRPojo grPojo) {
        return grDao.DelGR(grPojo);
    }

    @Override
    public List<GRPojo> selectAllByID(String id) {
        return grDao.selectAllByID(id);
    }

    @Override
    public List<GRName> selectAllByUserID(String userID) {
        return grDao.selectAllByUserID(userID);
    }
}
