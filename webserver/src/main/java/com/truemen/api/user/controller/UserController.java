package com.truemen.api.user.controller;

<<<<<<< HEAD
import com.truemen.api.user.model.UserBaseInfo;
import com.truemen.api.user.model.UserLocation;
import com.truemen.api.user.service.UserService;
import com.truemen.api.user.model.User;
import com.truemen.api.user.dao.RegisterResponse;
import com.truemen.api.user.service.WechatClient;
=======
import java.util.List;

>>>>>>> 23d4caa4247c65d601cf8303d399d09dda7282db
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.truemen.api.user.dao.RegisterResponse;
import com.truemen.api.user.model.User;
import com.truemen.api.user.model.WechatUserInfo;
import com.truemen.api.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 测试hello验证apifox是否连接成功
    @GetMapping("/hello")
    public String sayHello() {
        return "你好";
    }

    // 注册
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.register(user)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }

    // 手机注册
    @PostMapping("/registerByPhone")
    public ResponseEntity<RegisterResponse> registerUserByPhone(@RequestBody User user) {
        if (userService.registerByPhone(user)) {
            return ResponseEntity.ok(new RegisterResponse(user, "User registered successfully", 0));
        } else {
            return ResponseEntity.badRequest().body(new RegisterResponse(user, "User already exists", 1));
        }
    }

    // 登录
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String identifier, @RequestParam String password) {
        User user = userService.checkUser(identifier, identifier);
        if (user != null && userService.checkPassword(password, user.getPassword())) {
            user.setPermission(1);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    // 根据uid查看个人信息
    @GetMapping("/uid/{uid}/profile")
    public ResponseEntity<RegisterResponse> getUserProfileByUid(@PathVariable("uid") Long uid) {
        User user = userService.getUserByUid(uid);
        if (user != null) {
            return ResponseEntity.ok(new RegisterResponse(user, "get userProfile by uid successfully", 0));
        } else {
            return ResponseEntity.badRequest().body(new RegisterResponse(user, "not get userProfile by uid", 1));
        }
    }

    // 用uid编辑个人基本信息
    @PutMapping("/uid/{uid}/profile/edit")
    public ResponseEntity<String> editUserProfile(@PathVariable("uid") Long uid, @RequestBody User user) {
        User existingUser = userService.getUserByUid(uid);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // 更新用户信息
        existingUser.setUserName(user.getUserName());
        existingUser.setBio(user.getBio());
        existingUser.setGender(user.getGender());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setUserId(user.getUserId());

<<<<<<< HEAD
        // 更新微信ID逻辑
        if (user.getWechatId() != null && !user.getWechatId().equals(existingUser.getWechatId())) {
            try {
                if (!revalidateWechatId(user.getWechatId())) {
                    return ResponseEntity.badRequest().body("Failed to revalidate wechatId");
                }
                existingUser.setWechatId(user.getWechatId());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Failed to revalidate wechatId: " + e.getMessage());
            }
        }

        if (userService.updateUserBase(existingUser)) {
=======
        // // 修改phone需要发送手机验证码
        // if (user.getPhone() != null &&
        // !user.getPhone().equals(existingUser.getPhone())) {
        // // 发送手机验证码逻辑
        // if (!sendPhoneVerificationCode(user.getPhone())) {
        // return ResponseEntity.badRequest().body("Failed to send phone verification
        // code");
        // }
        // existingUser.setPhone(user.getPhone());
        // }

        if (userService.updateUser(existingUser)) {
>>>>>>> 23d4caa4247c65d601cf8303d399d09dda7282db
            return ResponseEntity.ok("User profile updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user profile");
        }
    }

<<<<<<< HEAD
    // 获取用户位置
    @GetMapping("/locations")
    public List<UserLocation> getUserLocations() {
        return userService.getUserLocations();
    }
=======
    // 保存用户的标签
    @PostMapping("/{uid}/savetags")
    public ResponseEntity<String> saveTags(@PathVariable Long uid, @RequestBody List<String> tags) {
        if (userService.saveTags(uid, tags)) {
            return ResponseEntity.ok("Tags saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to save tags");
        }
    }

    // 获取用户的标签
    @GetMapping("/{uid}/gettags")
    public ResponseEntity<List<String>> getTags(@PathVariable Long uid) {
        List<String> tags = userService.getTags(uid);
        if (tags != null) {
            return ResponseEntity.ok(tags);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // // 发送手机验证码逻辑（示例）
    // private boolean sendPhoneVerificationCode(String phone) {
    // // 这里实现发送手机验证码的逻辑
    // // 假设我们使用一个第三方服务来发送验证码
    // String verificationCode = generateVerificationCode(); // 生成验证码
    // boolean isSent = sendVerificationCodeViaSMS(phone, verificationCode); //
    // 发送验证码
>>>>>>> 23d4caa4247c65d601cf8303d399d09dda7282db

    // 获取用户基本信息
    @GetMapping("/{uid}/baseinfo")
    public ResponseEntity<UserBaseInfo> getUserBaseInfo(@PathVariable Long uid) {
        UserBaseInfo userBaseInfo = userService.getUserBaseInfo(uid);
        return userBaseInfo != null ? ResponseEntity.ok(userBaseInfo) : ResponseEntity.notFound().build();
    }

<<<<<<< HEAD
    // 重新拉取微信验证逻辑
    private boolean revalidateWechatId(String wechatId) {
        String accessToken = wechatClient.getAccessToken();
        return wechatClient.getUserInfo(wechatId, accessToken) != null;
=======
    // 微信登录
    @PostMapping("/wechat-login")
    public ResponseEntity<Object> wechatLogin(@RequestParam("code") String code) {
        try {
            // 使用微信提供的 code 换取 access_token 和 openid
            WechatUserInfo userInfo = userService.getUserInfoByCode(code);

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
    // 登录响应类
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
>>>>>>> 23d4caa4247c65d601cf8303d399d09dda7282db
    }
}
