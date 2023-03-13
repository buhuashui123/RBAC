package com.springboot.service.impl;

import com.springboot.in.GroupDao;
import com.springboot.pojo.Group;
import com.springboot.service.GroupService;
import com.springboot.utils.MD5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;


    @Override
    public Group addGroup(Group group) {
        int i = 0;
        List<Group> list = groupDao.selectAll();
        String account = MD5Utils.getAccount(list);
        group.setAccount(account);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Group groups : list) {
                //群名已存在
                if (groups.getName().equals(group.getName())){
                    i = 1;
                }
            }
        }
        if (i!=1){
            List<Group> groupList = new ArrayList<>();
            groupList.add(group);
            groupDao.addGroup(groupList);
            List<Group> groups = selectAllByAccount(group.getAccount());
            for (Group g : groups) {
                group.setId(g.getId());
            }
            return group;
        }
        return null;
    }

    @Override
    public int DelGroup(Group group) {
        //创建群集合
        List<Group> list = new ArrayList<>();
        //把对象存入集合中
        list.add(group);
        //调用持久层的删除群方法
        return groupDao.DelGroup(list);
    }

    @Override
    public List<Group> selectAll() {
        return groupDao.selectAll();
    }

    @Override
    public List<Group> selectAllByAccount(String account) {
        return groupDao.selectAllByAccount("%"+account+"%");
    }
}
