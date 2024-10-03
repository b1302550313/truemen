package com.truemen.api.user.controller;

import com.truemen.api.user.model.User;
import com.truemen.api.user.model.WechatUserInfo;
import com.truemen.api.user.service.UserService;
import com.truemen.api.user.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatLoginController {
    @Autowired
    private WechatService wechatService;

    @Autowired
    private UserService userService;

    @PostMapping("/wechat-login")
    public ResponseEntity<Object> wechatLogin(@RequestParam("code") String code) {
        try {
            // 使用微信提供的 code 换取 access_token 和 openid
            WechatUserInfo userInfo = wechatService.getUserInfoByCode(code);

            // 检查用户是否已经注册
            User user = userService.getUserById(userInfo.getOpenId());
            if (user == null) {
                // 用户未注册, 创建新用户
                user = userService.createNewUser(userInfo);
            }

            // 生成登录成功响应
            LoginResponse loginResponse = new LoginResponse(true, "登录成功", user, "your_token_value");

            // 返回登录成功响应
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            // 登录失败, 返回错误信息
            LoginResponse loginResponse = new LoginResponse(false, "登录失败: " + e.getMessage(), null, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }

    private static class LoginResponse {
        private final boolean success;
        private final String message;
        private final User user;
        private final String token;

        public LoginResponse(boolean success, String message, User user, String token) {
            this.success = success;
            this.message = message;
            this.user = user;
            this.token = token;
        }

        // Getters and Setters (if needed)
    }
}