package com.sysu.verto.friendship.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_core_info")
public class UserCoreInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Long permission;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    // 无参构造函数
    public UserCoreInfo() {
    }

    // 全参构造函数
    public UserCoreInfo(Long uid, String phone, Long permission, LocalDateTime createTime) {
        this.uid = uid;
        this.phone = phone;
        this.permission = permission;
        this.createTime = createTime;
    }

    // Getters 和 Setters
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}