package com.sysu.verto.user.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private String uid; // 系统生成的唯一标识符
    @Column(name = "userId")
    private String userId; // 用户自定义的标识符
    @Column(name = "userName")
    private String userName;
    @Column(name = "wechatId")
    private String wechatId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "createTime")
    private LocalDateTime createTime;
    @Column(name = "permission")
    private int permission;
    @Column(name = "bio")
    private String bio;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "birthDate")
    private LocalDate birthDate; // 新增生日信息

    public enum Gender {
        男, 女, 匿 // 新增匿藏选项
    }

    public User() {
    }

    public User(String userId, String userName, String wechatId, String phone, String password, String avatar,
            int permission, String bio, Gender gender, LocalDate birthDate) {
        this.userId = "user_" + System.currentTimeMillis();
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
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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
