package com.springboot.utils;

import com.springboot.pojo.Group;
import com.springboot.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 账号密码工具类
 */
public class MD5Utils {

    /**
     * 随机用户账号(避免账号一致)
     * @param list 用户集合
     * @return 返回账号
     */
    public static String getLoginName(List<User> list){
        boolean b = true;
        String loginName = null;
        while (b){
            int min = 10000;
            int max = 99999;
            String top = (int)(Math.random()*(max-min))+10000+"";
            String end = (int)(Math.random()*(max-min))+10000+"";
            String centre = (int)(Math.random()*(10-1))+"";
            loginName = top +end+centre;
            for (User user : list) {
                if (!user.getLoginName().equals(loginName)) {
                    b = false;
                }
            }
        }
        return loginName;
    }

    /**
     * 随机群账号(避免账号一致)
     * @param list 群集合
     * @return 返回账号
     */
    public static String getAccount(List<Group> list){

        boolean b = true;
        String account = null;
        while (b){
            int min = 10000;
            int max = 99999;
            String top = (int)(Math.random()*(max-min))+10000+"";
            String end = (int)(Math.random()*(9999-1000))+1000+"";
            account = top +end;
            if (CollectionUtils.isNotEmpty(list)){
                for (Group group : list) {
                    if (!group.getAccount().equals(account)) {
                        b = false;
                    }
                }
            }
            break;

        }
        return account;
    }

    /**
     *密码加密
     * @param password 密码
     * @param key 密钥
     * @return 返回加密后的字符串
     */
    public static String md5(String password, String key) {
        // 加密后的字符串
        String s = DigestUtils.md5Hex(password + key);
        return s;
    }

    /**
     *MD5验证密码是否正确
     *
     * @param password 密码
     * @param key 密钥
     * @param md5 加密后的密码
     * @return
     */
    public static boolean verify(String password, String key, String md5){
        //根据传入的密钥进行验证
        String s = md5(password,key);
        return s.equalsIgnoreCase(md5);
    }


}
