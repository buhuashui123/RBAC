package com.springboot.controller;

import com.springboot.in.URDao;
import com.springboot.pojo.Role;
import com.springboot.pojo.RoleAndMenu;
import com.springboot.pojo.UserAndRole;
import com.springboot.service.RMService;
import com.springboot.service.RoleService;
import com.springboot.service.URService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private URService urService;

    @Resource
    private RMService rmService;

    //添加角色页面
    @RequestMapping("add")
    public String add(){
        return "/role/addRole";
    }

    //添加角色
    @RequestMapping("addRole")
    public String addRole(String roleName, ModelMap map){
        if (roleName==""){
            //角色添加失败,绑定错误信息，丢到添加用户页面
            map.addAttribute("msg","角色名违规");
            //转发到添加页面
            return "/role/addRole";
        }
        //把数据存入对象中
        Role role = new Role(roleName);
        //调用业务层的添加方法
        int i = roleService.addRole(role);
        //角色名不存在
        if (i!=1){
            //重定向到角色展示页面
            return "redirect:/role/show";
        }else {
            //角色已存在，绑定错误信息
            map.addAttribute("msg","角色已存在");
            //转发到添加角色页面
            return "/role/addRole";
        }
    }

    //角色展示页面
    @RequestMapping("show")
    public String showRole(ModelMap map){
        //获取角色所有信息
        List<Role> roleList = roleService.selectAll();
        //绑定所有角色信息
        map.addAttribute("roleList",roleList);
        //转发到展示角色页面
        return "/role/showRole";
    }

    //删除角色
    @RequestMapping("DelRole")
    public String DelRole(String roleID){
        //把角色id强制转换并存入对象中
        Role role = new Role(Integer.parseInt(roleID));
        //调用业务层的删除方法
        roleService.DelRole(role);
        //删除角色的所有权限
        rmService.DelRM(new RoleAndMenu(Integer.parseInt(roleID)));
        //重定向到展示页面
        return "redirect:/role/show";
    }

    //修改角色页面
    @RequestMapping("UpRole")
    public String UpRole(String roleID,ModelMap map){
        //绑定角色id
        map.addAttribute("roleID",roleID);
        //转发到修改角色页面
        return "/role/UpdateRole";
    }

    //修改角色
    @RequestMapping("UpdateRole")
    public String UpdateRole(String roleID,String roleName){
        //获取角色ID
        int i = Integer.parseInt(roleID);
        String roleNames = roleName==""?null:roleName;
        Role roles = new Role(i,roleNames);
        if (roleNames!=null){
            //把角色信息存入对象中
            Role role = new Role(i,roleNames);
            //调用修改角色的方法
            roleService.UpRole(role);
        }
        //重定向到展示
        return "redirect:/role/show";
    }

    //给用户添加角色页面
    @RequestMapping("addUR")
    public String addUR(String userID,String userName,ModelMap map){
        //获取所有角色
        List<Role> roleList = roleService.selectAll();
        //绑定所有角色，用户id，用户姓名
        map.addAttribute("roleList",roleList);
        map.addAttribute("userID",userID);
        map.addAttribute("userName",userName);
        //转发到给用户添加角色页面
        return "/role/addUR";
    }

    //给用户添加角色
    @RequestMapping("addUserRole")
    public String addUserRole(String userID,String roleID){
        //调用业务层的添加角色方法
        urService.addUR(new UserAndRole(userID,Integer.parseInt(roleID)));
        //添加成功，重定向到用户展示页面
        return "redirect:/user/show";
    }
}
