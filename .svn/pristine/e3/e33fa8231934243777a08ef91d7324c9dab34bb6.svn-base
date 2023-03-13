package com.springboot.pojo;

/**
 * 用户角色实体类
 */
public class UserAndRole {

    private String userID;
    private int roleID;

    public UserAndRole() {
    }

    public UserAndRole(String userID) {
        this.userID = userID;
    }

    public UserAndRole(String userID, int roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAndRole that = (UserAndRole) o;

        if (roleID != that.roleID) return false;
        return userID != null ? userID.equals(that.userID) : that.userID == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + roleID;
        return result;
    }

    @Override
    public String toString() {
        return "UserAndRole{" +
                "userID='" + userID + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
