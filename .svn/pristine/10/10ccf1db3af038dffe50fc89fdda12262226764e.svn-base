package com.springboot.pojo;

/**
 * 群申请
 */
public class GRPojo {

    private String id;
    private String userID;

    public GRPojo() {
    }

    public GRPojo(String id) {
        this.id = id;
    }

    public GRPojo(String id, String userID) {
        this.id = id;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GRPojo grPojo = (GRPojo) o;

        if (id != null ? !id.equals(grPojo.id) : grPojo.id != null) return false;
        return userID != null ? userID.equals(grPojo.userID) : grPojo.userID == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GRPojo{" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
