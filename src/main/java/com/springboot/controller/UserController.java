package com.springboot.controller;

import com.springboot.annotations.MenuAnnotation;
import com.springboot.in.URDao;
import com.springboot.pojo.*;
import com.springboot.service.*;
import com.springboot.utils.FTPUtil;
import com.springboot.utils.FtpUtils;
import com.springboot.utils.MD5Utils;
import com.springboot.utils.SftpUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private URService urService;

    @Resource
    private UserRService userRService;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource
    private UFService ufService;

    @Resource
    private DetailService detailService;

    //添加用户页面
    @RequestMapping("add")
    public String add(){
        return "/user/addUser";
    }

    //添加用户
    @RequestMapping("addUser")
    public String addUser(String userName, String loginPass, ModelMap map){
        if (userName==""||loginPass==""){
            //账号注册失败,绑定错误信息，丢到添加用户页面
            map.addAttribute("msg","用户名密码违规");
            //转发到添加页面
            return "/user/addUser";
        }
        //获取前端传过来的数据并存入对象中
        User user = new User(userName,loginPass);
        //调用业务层的添加方法
        User users = userService.addUser(user);
        if (users!=null) {
            //账号注册成功,绑定用户信息，丢到首页
            map.addAttribute("user",users);
            /**
             * 上传头像
             */
            //转发到首页
            return "/user/Finish";
        }else{
            //账号注册失败,绑定错误信息，丢到添加用户页面
            map.addAttribute("msg","账号已存在");
            //转发到添加页面
            return "/user/addUser";
        }
    }

    //登录页面
    @RequestMapping("login")
    public String login(){
        return "/user/loginUser";
    }

    //登录
    @RequestMapping("loginUser")
    public String loginUser(String loginName, String loginPass, ModelMap map, HttpSession session){
        if (loginName==""||loginPass==""){
            //账号注册失败,绑定错误信息，丢到添加用户页面
            map.addAttribute("msg","账号密码违规");
            //转发到登录页面
            return "/user/loginUser";
        }
        User user = userService.loginByLoginName(loginName, loginPass);
        if (user!=null){
            //session绑定用户信息，用于登录验证
            session.setAttribute("user",user);
            //账号密码正确，重定向到展示
            return "redirect:/user/home";
        }else{
            //账号密码错误，绑定错误信息
            map.addAttribute("msg","账号或密码错误");
            //转发到登录页面
            return "/user/loginUser";
        }
    }

    //首页
    @RequestMapping("home")
    public String Home(ModelMap map,HttpSession session){
        String path = "http://192.168.80.128/xiaoma/imgs/666.jpg";
        //获取session里绑定的用户信息
        User user = (User) session.getAttribute("user");
        //获取用户头像
        Details details = detailService.selectAll(user.getUserID());
        if (details!=null) {
           path = "http://192.168.80.128/xiaoma/"+user.getUserID()+"/"+details.getPath();
//            path = "http://192.168.80.128/xiaoma/"+details.getPath();
            System.out.println(path);
            //绑定用户头像路径
            map.addAttribute("path",path);
        }
        //绑定当前用户信息
        map.addAttribute("user",user);
        //转发到展示页面
        return "/user/Home";
    }

    //展示
    @RequestMapping("show")
    public String show(ModelMap map,HttpSession session){
        //获取所有用户信息
        List<User> userList = userService.selectAll();
        //绑定所有用户信息
        map.addAttribute("userList",userList);
        //转发到展示页面
        return "/user/showUser";
    }

    //删除
    @MenuAnnotation("XSGL_ZSXS_SC")
    @RequestMapping("DelUser")
    public String DelUser(String userID){
        //调用业务层的删除用户方法
        userService.DelUser(new User(userID));
        //删除该用户的所有角色
        urService.DelUR(new UserAndRole(userID));
        //重定向到展示页面
        return "redirect:/user/show";
    }

    //修改页面
    @RequestMapping("UpUser")
    public String UpUser(String userID,ModelMap map){
        //绑定用户ID
        map.addAttribute("userID",userID);
        //转发到修改页面
        return "/user/UpdateUser";
    }

    @RequestMapping("UpdateUser")
    public String UpdateUser(String userID,String userName,String loginPass){
        //三元表达式，避免空指针
        String userNames = userName==""?null:userName;
        String password = loginPass==""?null: MD5Utils.md5(loginPass,"U");
        //把用户信息存入对象中
        User user = new User(userID,userNames,null,password);
        //调用业务层的修改方法
        userService.UpUser(user);
        //重定向到登录页面
        return "redirect:/user/login";
    }

    @RequestMapping("search")
    public String search(){
        return "/uFriend/searchUser";
    }

    @RequestMapping("searchUser")
    public String searchUser(String loginName,ModelMap map){
        //获取模糊查询出来的所有数据
        List<User> userList = userService.selectUserByLoginName(loginName);
        //绑定信息
        if (userList.isEmpty()){
            map.addAttribute("msg","搜索不到");
            //转发到搜索不到页面
            return "/uFriend/error";
        }else{
            map.addAttribute("userList",userList);
            //转发到展示页面
            return "/uFriend/showSearch";
        }

    }

    //查看用户好友
    @RequestMapping("showFriend")
    public String showFriend(HttpSession session,ModelMap map){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //获取好友信息
        List<User> userList = ufService.selectAll(user.getUserID());
        //绑定信息
        if (userList.isEmpty()){
            map.addAttribute("msg","您没有好友");
            //转发到无好友页面
            return "/uFriend/error";
        }else{
            map.addAttribute("userList",userList);
            //转发到展示好友页面
            return "/uFriend/showFriend";
        }
    }

    @RequestMapping("DelFriend")
    public String DelFriend(String friendsID,HttpSession session){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //存入对象中
        UFPojo ufPojo = new UFPojo(user.getUserID(),friendsID);
        //调用删除好友的业务层
        ufService.DelUF(ufPojo);
        //转发到展示好友页面
        return "redirect:/user/showFriend";
    }

    //展示申请页面
    @RequestMapping("showRequest")
    public String showRequest(HttpSession session,ModelMap map){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //获取申请信息
        List<User> userList = userRService.selectAll(user.getUserID());
        //绑定信息
        if (userList.isEmpty()){
            map.addAttribute("msg","您没有申请信息");
            //转发到无好友页面
            return "/uFriend/error";
        }else{
            map.addAttribute("userList",userList);
            //转发到展示好友页面
            return "/uFriend/showRequest";
        }
    }

    //添加申请
    @RequestMapping("addRequest")
    public String addRequest(String requestID,HttpSession session,ModelMap map){
        User user = (User) session.getAttribute("user");
        UserRPojo userRPojo = new UserRPojo(user.getUserID(),requestID);
        int i = userRService.addUserR(userRPojo);
        if(i == 1){
            //添加成功，绑定信息
            map.addAttribute("msg","您已成功添加该用户");
        }else if (i ==0){
            //发送添加申请，绑定信息
            map.addAttribute("msg","请求发送成功，等待用户同意");
        }else if (i == 2){
            map.addAttribute("msg","你们已经成为了好友！不可重复加");
        }
        return "/uFriend/error";

    }

    //同意
    @RequestMapping("consent")
    public String consent(String requestID,HttpSession session,ModelMap map){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //存入对象中
        UFPojo ufPojo = new UFPojo(user.getUserID(),requestID);
        //调用业务层的方法
        int i = ufService.addUF(ufPojo);
        userRService.DelUserR(new UserRPojo(user.getUserID(),requestID));
        if (i!=1){
            //添加成功
            map.addAttribute("msg","添加成功");
        }else {
            //好友已存在
            map.addAttribute("msg","好友已存在");
        }
        return "/uFriend/error";
    }

    //拒绝
    @RequestMapping("decline")
    public String decline(String requestID,HttpSession session){
        //获取当前用户ID
        User user = (User) session.getAttribute("user");
        //存入对象中
        UserRPojo userRPojo = new UserRPojo(user.getUserID(),requestID);
        //调用删除方法
        userRService.DelUserR(userRPojo);
        //重定向到展示申请页面
        return "redirect:/user/showRequest";
    }

    /**
     * 上传头像页面
     */
    @RequestMapping("upload")
    public String upload(HttpSession session,ModelMap map){
        String path = null;
        //获取用户
        User user = (User) session.getAttribute("user");
        Details details = detailService.selectAll(user.getUserID());
        if(details!=null){
            path = "http://192.168.80.128/xiaoma/"+user.getUserID()+"/"+details.getPath();
        }
        //绑定用户ID和用户名
        map.addAttribute("userID",user.getUserID());
        map.addAttribute("userName",user.getUserName());
        map.addAttribute("path",path);
        //转发到上传头像页面
        return "/user/uploadSFTP";
    }

    //上传头像
    @RequestMapping("upLoadAvatar")
    public String upLoadAvatar(String userID, HttpServletRequest request, HttpServletResponse response, ModelMap map){
        InputStream in = null;
        MultipartFile fileName = ((MultipartHttpServletRequest) request).getFile("fileName");
        //获取文件名
        String name = fileName.getOriginalFilename();
        name = name.substring(name.lastIndexOf("."));
        if (name.equals(".jpg")) {
            //获取文件流
            try {
                in = fileName.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SftpUtils.login();
//            String s = FTPUtil.uploadFile(userID, userID + ".jpg", in);
            String s = SftpUtils.upload(userID, userID + ".jpg", in);
            //将头像名入库
            detailService.addDetail(new Details(userID,null,s));
            return "redirect:/user/home";
        }else {
            map.addAttribute("msg","上传的文件是不合法的");
            return  "/uFriend/error";
        }

    }


    /**
     *SFTP上传头像页面
     * @return
     */
    @RequestMapping("uploadSFTP")
    public String uploadSFTP(HttpSession session,ModelMap map){
        String path = "http://192.168.80.128/xiaoma/imgs/666.jpg";
        //获取用户
        User user = (User) session.getAttribute("user");
        //绑定用户ID和用户名
        map.addAttribute("userID",user.getUserID());
        map.addAttribute("userName",user.getUserName());
        //获取当前头像
        Details details = detailService.selectAll(user.getUserID());
        if (details!= null){
            path = "http://192.168.80.128/xiaoma/"+user.getUserID()+"/"+details.getPath();
        }
        map.addAttribute("path",path);
        //转发到上传头像页面
        return "/user/uploadSFTP";
    }

    /**
     * 同意上传页面
     * @return
     */
    @RequestMapping("upLoadPreview")
    public String upLoadPreview(@RequestParam("fileName")MultipartFile fileName,String userID,String path){
        InputStream in = null;
        try {
            in = fileName.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ps = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(ps+"-------------");
        SftpUtils.login();
        SftpUtils.delete("sb",ps);
        detailService.addDetails(userID,in);
        SftpUtils.closeServer();
        return "redirect:/user/home";
    }

    /**
     * 拒绝上传头像
     * @param path
     * @return
     */
    @RequestMapping("noUpload")
    public String noUpload(String path){
        String ps = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(ps);
        //连接SFTP服务
        SftpUtils.login();
        //删除临时目录的头像
        SftpUtils.delete("sb",ps);
        //关闭SFTP服务
        SftpUtils.closeServer();
        return "redirect:/user/home";
    }
    @RequestMapping("exit")
    public String Exit(HttpSession session){
        //使用前对rediskey进行序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //强制转换
        User u = (User) session.getAttribute("user");
        //获取用户ID
        String userID = u.getUserID();
        //删除redis缓存
        redisTemplate.delete(userID);
        //删除session
        session.removeAttribute("user");
        //重定向到登录页面
        return "redirect:/user/login";
    }

}
