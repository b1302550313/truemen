package com.sysu.verto.user.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private long uid; // 系统生成的唯一标识符
    private String userId; // 用户自定义的标识符
    private String userName;
    private String wechatId;
    private String phone;
    private String password;
    private String avatar;
    private LocalDateTime createTime;
    private int permission;
    private String bio;
    private Gender gender;
    private LocalDate birthDate; // 新增生日信息

    public enum Gender {
        男, 女, 匿 // 新增匿藏选项
    }

    public User() {
    }

    public User(long uid, String userId, String userName, String wechatId, String phone, String password, String avatar,
            int permission, String bio, Gender gender, LocalDate birthDate) {
        this.uid = uid;
        this.userId = userId;
        this.userName = userName;
        this.wechatId = wechatId;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.permission = permission;
        this.bio = bio;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    // Getters and Setters
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
