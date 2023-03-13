package com.springboot.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1843082746943355790L;
    private int menuID;
    private String menuName;
    private int pID;
    private String menuCode;
    private List<Menu> menuList;

    public Menu() {
    }

    public Menu(int menuID) {
        this.menuID = menuID;
    }

    public Menu(String menuName, int pID, String menuCode) {
        this.menuName = menuName;
        this.pID = pID;
        this.menuCode = menuCode;
    }

    public Menu(int menuID, String menuName, int pID, String menuCode) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.pID = pID;
        this.menuCode = menuCode;
    }

    public Menu(int menuID, String menuName, int pID, String menuCode, List<Menu> menuList) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.pID = pID;
        this.menuCode = menuCode;
        this.menuList = menuList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (menuID != menu.menuID) return false;
        if (pID != menu.pID) return false;
        if (menuName != null ? !menuName.equals(menu.menuName) : menu.menuName != null) return false;
        return menuCode != null ? menuCode.equals(menu.menuCode) : menu.menuCode == null;
    }

    @Override
    public int hashCode() {
        int result = menuID;
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + pID;
        result = 31 * result + (menuCode != null ? menuCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuID=" + menuID +
                ", menuName='" + menuName + '\'' +
                ", pID=" + pID +
                ", menuCode='" + menuCode + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}
