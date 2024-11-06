package com.truemen.api.user.controller;

import com.truemen.api.user.model.UserBaseInfo;
import com.truemen.api.user.model.UserLocation;
import com.truemen.api.user.service.UserService;
import com.truemen.api.user.model.User;
import com.truemen.api.user.dao.RegisterResponse;
import com.truemen.api.user.service.WechatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final WechatClient wechatClient;

    @Autowired
    public UserController(UserService userService, WechatClient wechatClient) {
        this.userService = userService;
        this.wechatClient = wechatClient;
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
            return ResponseEntity.ok("User profile updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user profile");
        }
    }

    // 获取用户位置
    @GetMapping("/locations")
    public List<UserLocation> getUserLocations() {
        return userService.getUserLocations();
    }

    // 获取用户基本信息
    @GetMapping("/{uid}/baseinfo")
    public ResponseEntity<UserBaseInfo> getUserBaseInfo(@PathVariable Long uid) {
        UserBaseInfo userBaseInfo = userService.getUserBaseInfo(uid);
        return userBaseInfo != null ? ResponseEntity.ok(userBaseInfo) : ResponseEntity.notFound().build();
    }

    // 重新拉取微信验证逻辑
    private boolean revalidateWechatId(String wechatId) {
        String accessToken = wechatClient.getAccessToken();
        return wechatClient.getUserInfo(wechatId, accessToken) != null;
    }
}
