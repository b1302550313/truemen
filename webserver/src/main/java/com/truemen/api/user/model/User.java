package com.truemen.api.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Long uid; // 系统生成的唯一标识符

    @Column(name = "user_id")
    private String userId; // 用户自定义的标识符

    @Column(name = "user_name")
    private String userName;

    @Column(name = "wechat_id")
    private String wechatId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar") // 头像url
    private String avatar;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "permission")
    private int permission;

    @Column(name = "bio") // 座右铭，自我介绍
    private String bio;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate; // 新增生日信息

    @Column(name = "zodiac") // 星座
    private String zodiac;

    @Column(name = "tags") // 标签，以逗号分隔的字符串
    private String tags;

    public enum Gender {
        男, 女, 匿 // 新增匿藏选项
    }

    // 默认构造函数
    public User() {
        System.out.println(" Default User created");
        this.gender = gender == null ? Gender.匿 : gender; // 设置默认值
    }

    // 写一个复制构造函数
    public User(User user) {
        System.out.println(" Copy User created ");
        this.uid = user.getUid();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.wechatId = user.getWechatId();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.avatar = user.getAvatar();
        this.permission = user.getPermission();
        this.bio = user.getBio();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
        this.zodiac = user.getZodiac();
        this.tags = user.getTags();
    }

    // 参数构造函数
    public User(Long uid, String userId, String userName, String wechatId, String phone, String password, String avatar,
            int permission, String bio, Gender gender, LocalDate birthDate, String zodiac, String tags) {
        System.out.println(" Param User created");
        this.uid = uid;
        this.userId = "user_" + System.currentTimeMillis();
        this.userName = userName;
        this.wechatId = wechatId;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.permission = permission;
        this.bio = bio;
        this.gender = gender != null ? gender : Gender.匿; // 设置默认值
        this.birthDate = birthDate;
        this.zodiac = zodiac;
        this.tags = tags;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", wechatId='" + wechatId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", permission=" + permission +
                ", bio='" + bio + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", zodiac=" + zodiac +
                ", tags=" + tags +
                '}';
    }

    // Getters and Setters
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    // 将标签列表转换为逗号分隔的字符串
    public void setTagsFromList(List<String> tags) {
        if (tags != null) {
            this.tags = tags.stream().collect(Collectors.joining(","));
        } else {
            this.tags = null;
        }
    }

    // 将逗号分隔的字符串转换为标签列表
    public List<String> getTagsAsList() {
        if (tags != null && !tags.isEmpty()) {
            return Arrays.asList(tags.split(","));
        } else {
            return Collections.emptyList();
        }
    }
}