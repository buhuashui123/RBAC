package com.springboot.service.impl;

import com.springboot.in.RMDao;
import com.springboot.pojo.Role;
import com.springboot.pojo.RoleAndMenu;
import com.springboot.service.RMService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RMServiceImpl implements RMService {

    @Resource
    private RMDao rmDao;


    @Override
    public int addRM(int roleID, int[] hobby) {
        //创建新集合
        List<RoleAndMenu> list = new ArrayList<>();
        //遍历存放权限id的数组
        for (int i = 0; i < hobby.length; i++) {
            //把数据存入对象中
            RoleAndMenu roleAndMenu = new RoleAndMenu(roleID,hobby[i]);
            //再把对象存入集合中
            list.add(roleAndMenu);
        }
        //调用持久层的添加方法
        return rmDao.addRM(list);
    }

    @Override
    public int DelRM(RoleAndMenu roleAndMenu) {
        //创建新集合
        List<RoleAndMenu> list = new ArrayList<>();
        //把数据存入集合中
        list.add(roleAndMenu);
        //调用持久层的删除方法
        return rmDao.DelRM(list);
    }
}
