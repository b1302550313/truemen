package com.sysu.verto.storage.model;


import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String weChatID;
    private String phoneNumber;
    private String password;
    private String avatar;
    private Integer permissionLevel;

    public User() {}

    public User(int userID, String userName, String weChatID, String phoneNumber, String password, String avatar, Integer permissionLevel) {
        this.userID = userID;
        this.userName = userName;
        this.weChatID = weChatID;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.avatar = avatar;
        this.permissionLevel = permissionLevel;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWeChatID() {
        return weChatID;
    }

    public void setWeChatID(String weChatID) {
        this.weChatID = weChatID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}
