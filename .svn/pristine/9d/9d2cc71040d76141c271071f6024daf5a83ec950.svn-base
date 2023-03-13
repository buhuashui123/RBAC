package com.springboot.pojo;

/**
 * 群
 */
public class Group {

    private String id; //群ID
    private String name; //群名
    private String userID; //群主ID
    private String account; //群账号

    public Group() {
    }

    public Group(String name, String userID, String account) {
        this.name = name;
        this.userID = userID;
        this.account = account;
    }

    public Group(String id, String name, String userID, String account) {
        this.id = id;
        this.name = name;
        this.userID = userID;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (userID != null ? !userID.equals(group.userID) : group.userID != null) return false;
        return account != null ? account.equals(group.account) : group.account == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userID='" + userID + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
