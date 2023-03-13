package com.springboot.controller;

import com.springboot.in.URDao;
import com.springboot.pojo.*;
import com.springboot.service.GRService;
import com.springboot.service.GUService;
import com.springboot.service.GroupService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("group")
public class GroupController {

    @Resource
    private GroupService groupService;

    @Resource
    private GUService guService;

    @Resource
    private GRService grService;

    @RequestMapping("add")
    public String add(HttpSession session, ModelMap map){
        User user = (User) session.getAttribute("user");
        //绑定用户ID
        map.addAttribute("userID",user.getUserID());
        //转发到添加群页面
        return "/group/addGroup";
    }

    @RequestMapping("addGroup")
    public String addGroup(String userID,String name,ModelMap map){
        if (name==""||userID==""){
            map.addAttribute("msg","群名违规");
            return "forward:/group/add";
        }
        Group group = new Group(null,name,userID,null);
        Group groups = groupService.addGroup(group);
        if (groups!=null){
            map.addAttribute("group",groups);
            guService.addGU(new GUPojo(groups.getId(),groups.getUserID()));
            return "/group/Finish";
        }else {
            map.addAttribute("msg","群名已存在");
            return "forward:/group/add";
        }

    }

    @RequestMapping("search")
    public String search(){
        return "/group/searchGroup";
    }

    @RequestMapping("searchGroup")
    public String searchGroup(String account,ModelMap map){
        List<Group> groupList = groupService.selectAllByAccount(account);
        //绑定信息
        if (groupList.isEmpty()){
            map.addAttribute("msg","搜索不到");
            //转发到搜索不到页面
            return "/uFriend/error";
        }else{
            map.addAttribute("groupList",groupList);
            //转发到展示页面
            return "/group/showSearch";
        }
    }

    @RequestMapping("show")
    public String show(HttpSession session,ModelMap map){
        //获取当前用户id
        User user = (User) session.getAttribute("user");
        //根据用户ID查出该用户的所有群
        List<Group> groupList = guService.selectAllByUserID(user.getUserID());
        if (CollectionUtils.isNotEmpty(groupList)) {
            //绑定用户的群信息
            map.addAttribute("groupList",groupList);
            //转发到展示群页面
            return "/group/showGroup";
        }
        map.addAttribute("msg","你没有加过任何群");
        return "/uFriend/error";
    }

    @RequestMapping("showGUser")
    public String showGUser(String id,String name,ModelMap map){
        //获取该表用户信息
        List<User> userList = guService.selectAllByID(id);
        //绑定用户信息
        map.addAttribute("userList",userList);
        map.addAttribute("name",name);
        //转发到展示群用户信息
        return "/group/showGUser";
    }

    @RequestMapping("addGR")
    public String addGR(HttpSession session,String id,ModelMap map){
        //获取当前用户id
        User user = (User) session.getAttribute("user");
        //调用业务层的添加方法
        int i = grService.addGR(new GRPojo(id, user.getUserID()));
        if (i == 1){
            map.addAttribute("msg","您已进群，拒绝发送申请");
        }else if (i == 2){
            map.addAttribute("msg","您已经发送过请求，拒绝重复");
        }else if (i == 0){
            map.addAttribute("msg","成功发送申请");
        }
        return "/uFriend/error";
    }

    @RequestMapping("showRequest")
    public String showRequest(HttpSession session,ModelMap map){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //根据用户ID查询当前用户所有群的申请
        List<GRName> grNameList = grService.selectAllByUserID(user.getUserID());
        //判断他的群申请是否是空的
        if (grNameList.isEmpty()) {
            map.addAttribute("msg","您加入的群没有申请信息");
            //转发到无好友页面
            return "/uFriend/error";
        }else {
            map.addAttribute("grNameList",grNameList);
            //转发到展示好友页面
            return "/group/showRequest";
        }
    }

    //同意
    @RequestMapping("consent")
    public String consent(String userID,String id,ModelMap map){
        //存入对象中
        GUPojo guPojo = new GUPojo(id,userID);
        //调用业务层的方法
        int i = guService.addGU(guPojo);
        grService.DelGR(new GRPojo(id,userID));
        if (i>0){
            //添加成功
            map.addAttribute("msg","添加成功");
        }
        return "/uFriend/error";
    }

    //拒绝
    @RequestMapping("decline")
    public String decline(String userID,String id){
        GRPojo grPojo = new GRPojo(id,userID);
        //调用删除方法
        grService.DelGR(grPojo);
        //重定向到展示申请页面
        return "redirect:/group/showRequest";
    }
}
