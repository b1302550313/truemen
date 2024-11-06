package com.truemen.api.user.controller;

import com.truemen.api.user.service.UserService;
import com.truemen.api.user.model.User;
import com.truemen.api.user.model.UserLocation;
import com.truemen.api.user.dao.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/locations")
public class UserLocationController {

    private final UserService userService;

    @Autowired
    public UserLocationController(UserService userService) {
        this.userService = userService;
    }

    // 获取当前在线用户的位置信息
    @GetMapping
    public ResponseEntity<List<UserLocation>> getUserLocations() {
        List<UserLocation> userLocations = userService.getUserLocations();
        return ResponseEntity.ok(userLocations);
    }

    // 根据用户ID获取详细个人信息
    @GetMapping("/{userId}/profile")
    public ResponseEntity<User> getUserProfile(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
