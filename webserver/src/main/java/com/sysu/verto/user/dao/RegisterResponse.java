package com.sysu.verto.user.dao;

import com.sysu.verto.user.model.User;

public class RegisterResponse extends User {
    private String msg;

    // 构造函数、getter 和 setter
    public RegisterResponse(String msg, String userName, String uid, String phone) {
        super();
        System.out.println("RegisterResponse constructor called");
        this.msg = msg;
        this.setUserName(userName);
        this.setUid(uid);
        this.setPhone(phone);
    }

    // Getter 和 Setter
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return this.getUserName();
    }

    public void setUserName(String userName) {
        this.setUserName(userName);
    }

    public String getUid() {
        return this.getUid();
    }

    public void setUid(String uid) {
        this.setUid(uid);
    }

    public String getPhone() {
        return this.getPhone();
    }

    public void setPhone(String phone) {
        this.setPhone(phone);
    }
}
