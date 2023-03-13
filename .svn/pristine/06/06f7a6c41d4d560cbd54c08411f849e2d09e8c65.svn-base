package com.springboot.pojo;

/**
 * 角色权限实体类
 */
public class RoleAndMenu {

    private int roleID;
    private int menuID;

    public RoleAndMenu() {
    }

    public RoleAndMenu(int roleID) {
        this.roleID = roleID;
    }

    public RoleAndMenu(int roleID, int menuID) {
        this.roleID = roleID;
        this.menuID = menuID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleAndMenu that = (RoleAndMenu) o;

        if (roleID != that.roleID) return false;
        return menuID == that.menuID;
    }

    @Override
    public int hashCode() {
        int result = roleID;
        result = 31 * result + menuID;
        return result;
    }

    @Override
    public String toString() {
        return "RoleAndMenu{" +
                "roleID=" + roleID +
                ", menuID=" + menuID +
                '}';
    }
}
