package com.springboot.service.impl;

import com.springboot.in.MenuDao;
import com.springboot.pojo.Menu;
import com.springboot.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public int addMenu(Menu menu) {
        int i = 0;
        //获取所有权限
        List<Menu> list = menuDao.selectAll();
        //判断权限是否已存在
        for (Menu menus : list) {
            if (menus.getMenuName().equals(menu.getMenuName())) {
                //权限已存在
                i = 1;
            }
        }
        if (i!=1){
            //创建一个新集合
            List<Menu> menuList = new ArrayList<>();
            //把对象存入集合中
            menuList.add(menu);
            //调用持久层的添加方法
            menuDao.addMenu(menuList);
        }
        return i;
    }

    @Override
    public int DelMenu(Menu menu) {
        //创建一个集合
        List<Menu> list = new ArrayList<>();
        //把对象存入集合
        list.add(menu);
        //调用删除的业务层方法
        return menuDao.DelMenu(list);
    }

    @Override
    public List<Menu> selectAll() {
        return menuDao.selectAll();
    }

    @Override
    public List<Menu> selectAllByPID(int pID) {
        //获取一级权限
        List<Menu> list = menuDao.selectAllByPID(pID);
        if(!list.isEmpty()){
            for (Menu m:list) {
                m.setMenuList(selectAllByPID(m.getMenuID()));
            }
        }
        return list;
    }

    @Override
    public List<Menu> selectAllByUserID(String userID) {
        return menuDao.selectAllByUserID(userID);
    }
}
