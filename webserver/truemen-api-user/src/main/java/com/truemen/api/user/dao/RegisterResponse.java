package com.truemen.api.user.dao;

import com.truemen.api.user.model.User;

// 注册响应类
public class RegisterResponse extends User {
    private String msg; // 注册结果信息
    private int code; // 注册结果代码,0表示成功，1表示失败

    // 构造函数、getter 和 setter
    public RegisterResponse(User user, String msg, int code) {
        super(user);
        System.out.println("RegisterResponse constructor called");
        this.msg = msg;
        this.code = code;
    }

    // Getter 和 Setter
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
