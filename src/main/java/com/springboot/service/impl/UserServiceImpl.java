package com.springboot.service.impl;

import com.springboot.in.UserDao;
import com.springboot.pojo.Menu;
import com.springboot.pojo.User;
import com.springboot.service.MenuService;
import com.springboot.service.UserService;
import com.springboot.utils.MD5Utils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @Resource
    private MenuService menuService;

    @Override
    public User addUser(User user) {
        //设置开关
        int i = 0;
        //获取数据库所有用户信息
        List<User> list = userDao.selectAll();
        for (User u : list) {
            //账号已存在
            if (u.getUserName().equals(user.getUserName())){
                i = 1;
            }
        }
        if (i!=1){
            //获取随机账号
            String loginName = MD5Utils.getLoginName(list);
            //获取密码
            String loginPass= MD5Utils.md5(user.getLoginPass(), "U");
            //将账号密码存入对象中
            User users = new User(user.getUserName(),loginName,loginPass);
            //创建集合，并将用户存入集合
            List<User> userList= new ArrayList<>();
            userList.add(users);
            //将用户信息存入对象，返回首页用的
            User reUser = new User(user.getUserName(),loginName,user.getLoginPass());
            //调用持久层的添加方法
            userDao.addUser(userList);
            //返回用户信息
            return reUser;
        }
        //账号已存在返回null
        return null;
    }

    @Override
    public int DelUser(User user) {
        //创建一个用户集合
        List<User> list = new ArrayList<>();
        //把用户存入集合中
        list.add(user);
        //调用持久层的删除方法
        return userDao.DelUser(list);
    }

    @Override
    public int UpUser(User user) {
        return userDao.UpUser(user);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public List<User> selectAllByPL() {
        return userDao.selectAllByPL();
    }

    @Override
    public User loginByLoginName(String loginName, String loginPass) {
        //获取用户
        User user = userDao.loginByLoginName(loginName);
        //判断账号是否存在，以及密码是否正确
        if (user!=null&&MD5Utils.verify(loginPass,"U",user.getLoginPass())){
            //使用前对rediskey进行序列化
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //获取用户所拥有的权限
            List<Menu> menuList = menuService.selectAllByUserID(user.getUserID());
            //如果正确将用户对象返回过去
            if(redisTemplate.opsForValue().get(user.getUserID())!=null){
                //如果存在干掉它
                redisTemplate.delete(user.getUserID());
            }
            //再放入redis中
            redisTemplate.opsForValue().set(user.getUserID(),menuList);
            return user;
        }
        return null;
    }

    @Override
    public List<User> selectUserByLoginName(String loginName) {
        return userDao.selectUserByLoginName("%"+loginName+"%");
    }


}
