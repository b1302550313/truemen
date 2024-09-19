package com.sysu.verto.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sysu.verto.user.dao.RegisterResponse;
import com.sysu.verto.user.model.User;

import com.sysu.verto.user.service.UserService;
import com.sysu.verto.user.service.WechatClient;

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
    // 手机注册
    @PostMapping("/registerByPhone")
    public ResponseEntity<User> registerUserByPhone(@RequestBody User user) {
        System.out.println("controller registerByPhone");
        if (userService.registerByPhone(user)) {
            // 获取用户的基本信息
            String userName = user.getUserName();
            String uid = user.getUid(); // 假设 userService.registerByPhone 返回 uid 或者你在 service 中设置了 uid
            String phone = user.getPhone();

            // 创建响应对象
            // RegisterResponse response = new RegisterResponse("User registered successfully", userName, uid, phone);

            return ResponseEntity.ok(user);
            // return ResponseEntity.ok("User registered successfully");
        } else {
             // 返回错误响应
             RegisterResponse errorResponse = new RegisterResponse("User already exists", null, null, null);
             return ResponseEntity.badRequest().body(errorResponse);
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
       // 根据uid查看个人信息
       @GetMapping("/uid/{userId}/profile")
       public ResponseEntity<User> getUserProfileByUid(@PathVariable String userId) {
           User user = userService.getUserByUid(userId);
           if (user != null) {
               return ResponseEntity.ok(user);
           } else {
               return ResponseEntity.notFound().build();
           }
       }


    // 根据id更改个人信息
    @PutMapping("/{userId}/profile")
    public ResponseEntity<String> updateUserProfile(@PathVariable String userId, @RequestBody User user) {
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

        // // 修改phone需要发送手机验证码
        // if (user.getPhone() != null && !user.getPhone().equals(existingUser.getPhone())) {
        //     // 发送手机验证码逻辑
        //     if (!sendPhoneVerificationCode(user.getPhone())) {
        //         return ResponseEntity.badRequest().body("Failed to send phone verification code");
        //     }
        //     existingUser.setPhone(user.getPhone());
        // }

        // 修改wechatId需要重新拉取微信验证
        if (user.getWechatId() != null && !user.getWechatId().equals(existingUser.getWechatId())) {
            // 重新拉取微信验证逻辑
            try {
                if (!revalidateWechatId(user.getWechatId())) {
                    return ResponseEntity.badRequest().body("Failed to revalidate wechatId");
                }
                existingUser.setWechatId(user.getWechatId());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Failed to revalidate wechatId: " + e.getMessage());
            }
        }

        if (userService.updateUser(existingUser)) {
            return ResponseEntity.ok("User profile updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user profile");
        }
    }

    // // 发送手机验证码逻辑（示例）
    // private boolean sendPhoneVerificationCode(String phone) {
    //     // 这里实现发送手机验证码的逻辑
    //     // 假设我们使用一个第三方服务来发送验证码
    //     String verificationCode = generateVerificationCode(); // 生成验证码
    //     boolean isSent = sendVerificationCodeViaSMS(phone, verificationCode); // 发送验证码

    //     if (isSent) {
    //         // 将验证码存储在缓存中，设置过期时间（例如5分钟）
    //         cacheService.set(phone, verificationCode, Duration.ofMinutes(5));
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    // // 生成验证码
    // private String generateVerificationCode() {
    //     // 生成一个6位数的验证码
    //     return String.format("%06d", new Random().nextInt(999999));
    // }

    // // 通过短信发送验证码
    // private boolean sendVerificationCodeViaSMS(String phone, String verificationCode) {
    //     // 这里调用第三方短信服务API来发送验证码
    //     // 示例代码：假设我们使用一个名为SMSClient的类来发送短信
    //     try {
    //         SMSClient.sendSMS(phone, "Your verification code is: " + verificationCode);
    //         return true;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    // 重新拉取微信验证逻辑
    private boolean revalidateWechatId(String wechatId) {
        // 使用微信的OAuth2.0接口来重新验证微信ID
        String accessToken = wechatClient.getAccessToken(); // 获取微信的access token
        boolean isValid = wechatClient.getUserInfo(wechatId, accessToken) != null; // 验证微信ID
        return isValid;
    }
}
