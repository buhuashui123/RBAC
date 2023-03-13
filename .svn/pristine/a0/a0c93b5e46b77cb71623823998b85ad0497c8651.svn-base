package com.springboot.pojo;

/**
 * 用户的具体信息
 */
public class Details {

    /**
     * 用户ID
     */
    private String userID;

    /**
     * 账号状态
     */
    private String envelop;

    /**
     * 用户的头像路径
     */
    private String path;

    public Details() {
    }

    public Details(String userID, String envelop, String path) {
        this.userID = userID;
        this.envelop = envelop;
        this.path = path;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEnvelop() {
        return envelop;
    }

    public void setEnvelop(String envelop) {
        this.envelop = envelop;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Details details = (Details) o;

        if (userID != null ? !userID.equals(details.userID) : details.userID != null) return false;
        if (envelop != null ? !envelop.equals(details.envelop) : details.envelop != null) return false;
        return path != null ? path.equals(details.path) : details.path == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (envelop != null ? envelop.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Details{" +
                "userID='" + userID + '\'' +
                ", envelop=" + envelop +
                ", path='" + path + '\'' +
                '}';
    }
}
