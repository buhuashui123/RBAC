package com.springboot.pojo;

/**
 * 用户申请实体类
 */
public class UserRPojo {

    private String userID; //被申请用户
    private String requestID; //发送申请用户

    public UserRPojo() {
    }

    public UserRPojo(String userID, String requestID) {
        this.userID = userID;
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRPojo urPojo = (UserRPojo) o;

        if (userID != null ? !userID.equals(urPojo.userID) : urPojo.userID != null) return false;
        return requestID != null ? requestID.equals(urPojo.requestID) : urPojo.requestID == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (requestID != null ? requestID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRPojo{" +
                "userID='" + userID + '\'' +
                ", requestID='" + requestID + '\'' +
                '}';
    }
}
