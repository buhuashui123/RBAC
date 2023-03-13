package com.springboot.pojo;

public class GRName {

    private String name;
    private String userName;
    private String userID;
    private String id;

    public GRName() {
    }

    public GRName(String name, String userName, String userID, String id) {
        this.name = name;
        this.userName = userName;
        this.userID = userID;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GRName grName = (GRName) o;

        if (name != null ? !name.equals(grName.name) : grName.name != null) return false;
        if (userName != null ? !userName.equals(grName.userName) : grName.userName != null) return false;
        if (userID != null ? !userID.equals(grName.userID) : grName.userID != null) return false;
        return id != null ? id.equals(grName.id) : grName.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GRName{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userID='" + userID + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
