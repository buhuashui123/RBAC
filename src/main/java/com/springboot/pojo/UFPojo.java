package com.springboot.pojo;

/**
 * 用户好友实体类
 */
public class UFPojo {

    private String userID;
    private String friendsID;

    public UFPojo() {
    }

    public UFPojo(String userID, String friendsID) {
        this.userID = userID;
        this.friendsID = friendsID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFriendsID() {
        return friendsID;
    }

    public void setFriendsID(String friendsID) {
        this.friendsID = friendsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UFPojo ufPojo = (UFPojo) o;

        if (userID != null ? !userID.equals(ufPojo.userID) : ufPojo.userID != null) return false;
        return friendsID != null ? friendsID.equals(ufPojo.friendsID) : ufPojo.friendsID == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (friendsID != null ? friendsID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UFPojo{" +
                "userID='" + userID + '\'' +
                ", friendsID='" + friendsID + '\'' +
                '}';
    }
}
