package com.springboot.controller;

import com.springboot.pojo.Menu;
import com.springboot.service.MenuService;
import com.springboot.service.RMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @Resource
    private RMService rmService;

    //添加权限页面
    @RequestMapping("add")
    public String add(ModelMap map){
        //获取所有权限
        List<Menu> list = menuService.selectAll();
        //绑定所有权限
        map.addAttribute("menuList",list);
        return "/menu/addMenu";
    }

    //添加
    @RequestMapping("addMenu")
    public String addMenu(String name,String sex,String pid,String menuCode,ModelMap map){
        int pID = 0;
        if (name==""||sex==""||menuCode==""){
            //权限添加失败,绑定错误信息，丢到添加用户页面
            map.addAttribute("msg","权限违规");
            //转发到添加页面
            return "forward:/menu/add";
        }
        //判断权限有没有父级权限
        if (sex.equals("是")) {
            pID = Integer.parseInt(pid);
        }
        //将数据存入对象中
        Menu menu = new Menu(name,pID,menuCode);
        //调用业务层的添加方法
        int i = menuService.addMenu(menu);
        //判断权限是否添加成功
        if (i!=1){
            //添加成功，重定向到展示页面
            return "redirect:/menu/show";
        }else {
            //添加失败，绑定错误信息
            map.addAttribute("msg","权限已存在");
            //转发到添加页面
            return "forward:/menu/add";
        }

    }

    //展示
    @RequestMapping("show")
    public String showMenu(ModelMap map){
        //获取所有权限
        List<Menu> menuList = menuService.selectAll();
        //绑定所有权限
        map.addAttribute("menuList",menuList);
        //转发到展示页面
        return "/menu/showMenu";
    }

    //删除权限
    @RequestMapping("DelMenu")
    public String DelMenu(String menuID){
        //把权限id存入对象中
        Menu menu = new Menu(Integer.parseInt(menuID));
        //调用业务层的删除方法
        menuService.DelMenu(menu);
        //重定向到展示页面
        return "redirect:/menu/show";
    }

    //给角色添加权限页面
    @RequestMapping("addRM")
    public String addRM(String roleID,String roleName,ModelMap map){
        //获取全量有序的菜单
        List<Menu> menuList = menuService.selectAllByPID(0);
        //绑定菜单，角色名，角色id
        map.addAttribute("menuList",menuList);
        map.addAttribute("roleID",roleID);
        map.addAttribute("roleName",roleName);
        //转发到给角色添加权限页面
        return "/menu/addRM";
    }

    @RequestMapping("addRoleMenu")
    public String addRoleMenu(String roleID,int hobby[]){
        //调用业务层的添加方法
        rmService.addRM(Integer.parseInt(roleID),hobby);
        //重定向到角色展示页面
        return "redirect:/role/show";
    }
}
