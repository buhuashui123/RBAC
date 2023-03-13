package com.springboot.service.impl;

import com.springboot.in.RoleDao;
import com.springboot.pojo.Role;
import com.springboot.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;


    @Override
    public int addRole(Role role) {
        //设置开关
        int i = 0;
        //获取所有用户角色
        List<Role> list = roleDao.selectAll();
        //遍历集合
        for (Role roles : list) {
            if (roles.getRoleName().equals(role.getRoleName())) {
                //角色名已存在
                i = 1;
            }
        }
        if (i!=1){
            //创建一个空集合
            List<Role> roleList = new ArrayList<>();
            //把角色对象丢进去
            roleList.add(role);
            //调用持久层的添加方法
            roleDao.addRole(roleList);
        }
        return i;
    }

    @Override
    public int DelRole(Role role) {
        //创建一个集合
        List<Role> list = new ArrayList<>();
        //把角色对象存入集合
        list.add(role);
        //调用持久层删除方法
        return roleDao.DelRole(list);
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public int UpRole(Role role) {
        return roleDao.UpRole(role);
    }
}
