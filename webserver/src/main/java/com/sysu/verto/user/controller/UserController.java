package com.sysu.verto.user.controller;

import com.sysu.verto.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sysu.verto.user.service.UserService;
import java.util.Optional;

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
}
