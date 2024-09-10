package com.sysu.verto.user.controller;

import com.sysu.verto.user.model.User;
import com.sysu.verto.user.model.WechatUserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sysu.verto.user.service.UserService;
import java.util.Optional;


import java.time.Duration;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 测试
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("hello world");
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

    // 根据id查看个人信息
    @GetMapping("/{userId}/profile")
    public ResponseEntity<User> getUserProfile(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据id更改个人信息
    @PutMapping("/{userId}/profile")
    public ResponseEntity<String> updateUserProfile(@PathVariable int userId, @RequestBody User user) {
        user.setUid(userId);
        if (userService.updateUser(user)) {
            return ResponseEntity.ok("User profile updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user profile");
        }
    }

    // 编辑个人档案
    @PutMapping("/{userId}/profile/edit")
    public ResponseEntity<String> editUserProfile(@PathVariable String userId, @RequestBody User user) {
        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // 更新用户的个人信息，除了uid和permission
        existingUser.setUserName(user.getUserName());
        existingUser.setAvatar(user.getAvatar());
        existingUser.setBio(user.getBio());
        existingUser.setGender(user.getGender());
        existingUser.setBirthDate(user.getBirthDate());

        // 修改phone需要发送手机验证码
        if (user.getPhone() != null && !user.getPhone().equals(existingUser.getPhone())) {
            // 发送手机验证码逻辑
            if (!sendPhoneVerificationCode(user.getPhone())) {
                return ResponseEntity.badRequest().body("Failed to send phone verification code");
            }
            existingUser.setPhone(user.getPhone());
        }

        // 修改wechatId需要重新拉取微信验证
        if (user.getWechatId() != null && !user.getWechatId().equals(existingUser.getWechatId())) {
            // 重新拉取微信验证逻辑
            if (!revalidateWechatId(user.getWechatId())) {
                return ResponseEntity.badRequest().body("Failed to revalidate wechatId");
            }
            existingUser.setWechatId(user.getWechatId());
        }

        if (userService.updateUser(existingUser)) {
            return ResponseEntity.ok("User profile updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user profile");
        }
    }

    // 发送手机验证码逻辑（示例）
    private boolean sendPhoneVerificationCode(String phone) {
        // 这里实现发送手机验证码的逻辑
        // 假设我们使用一个第三方服务来发送验证码
        String verificationCode = generateVerificationCode(); // 生成验证码
        boolean isSent = sendVerificationCodeViaSMS(phone, verificationCode); // 发送验证码

        if (isSent) {
            // 将验证码存储在缓存中，设置过期时间（例如5分钟）
            cacheVerificationCode(phone, verificationCode, Duration.ofMinutes(5));
            return true;
        } else {
            return false;
        }
    }

    // 生成验证码
    private String generateVerificationCode() {
        // 生成一个6位数的验证码
        return String.format("%06d", new Random().nextInt(999999));
    }

    // 发送验证码通过短信
    private boolean sendVerificationCodeViaSMS(String phone, String verificationCode) {
        // 这里调用第三方短信服务API来发送验证码
        // 示例代码：假设我们使用一个名为SMSClient的类来发送短信
        try {
            SMSClient.sendSMS(phone, "Your verification code is: " + verificationCode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 将验证码存储在缓存中
    private void cacheVerificationCode(String phone, String verificationCode, Duration duration) {
        // 这里使用一个缓存服务（例如Redis）来存储验证码
        // 示例代码：假设我们使用一个名为CacheService的类来存储缓存
        CacheService.set(phone, verificationCode, duration);
    }

    // 重新拉取微信验证逻辑（示例）
    private boolean revalidateWechatId(String wechatId) {
        // 这里实现重新拉取微信验证的逻辑
        // 假设我们使用微信的OAuth2.0接口来重新验证微信ID
        String accessToken = getWechatAccessToken(); // 获取微信的access token
        boolean isValid = validateWechatId(wechatId, accessToken); // 验证微信ID

        if (isValid) {
            // 将微信ID存储在缓存中，设置过期时间（例如1小时）
            cacheWechatId(wechatId, Duration.ofHours(1));
            return true;
        } else {
            return false;
        }
    }

    // 获取微信的access token
    private String getWechatAccessToken() {
        // 这里调用微信的OAuth2.0接口来获取access token
        // 示例代码：假设我们使用一个名为WechatClient的类来获取access token
        try {
            return WechatClient.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 验证微信ID
    private boolean validateWechatId(String wechatId, String accessToken) {
        // 这里调用微信的用户信息接口来验证微信ID
        // 示例代码：假设我们使用一个名为WechatClient的类来验证微信ID
        try {
            WechatUserInfo userInfo = WechatClient.getUserInfo(wechatId, accessToken);
            return userInfo != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 将微信ID存储在缓存中
    private void cacheWechatId(String wechatId, Duration duration) {
        // 这里使用一个缓存服务（例如Redis）来存储微信ID
        // 示例代码：假设我们使用一个名为CacheService的类来存储缓存
        CacheService.set(wechatId, "valid", duration);
    }
}
