package com.springboot.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3913190448039400982L;
    private String userID;
    private String userName;
    private String loginName;
    private String loginPass;

    public User() {
    }

    public User(String userID) {
        this.userID = userID;
    }

    public User(String userName, String loginPass) {
        this.userName = userName;
        this.loginPass = loginPass;
    }

    public User(String userName, String loginName, String loginPass) {
        this.userName = userName;
        this.loginName = loginName;
        this.loginPass = loginPass;
    }

    public User(String userID, String userName, String loginName, String loginPass) {
        this.userID = userID;
        this.userName = userName;
        this.loginName = loginName;
        this.loginPass = loginPass;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userID != null ? !userID.equals(user.userID) : user.userID != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null) return false;
        return loginPass != null ? loginPass.equals(user.loginPass) : user.loginPass == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (loginPass != null ? loginPass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPass='" + loginPass + '\'' +
                '}';
    }
}
