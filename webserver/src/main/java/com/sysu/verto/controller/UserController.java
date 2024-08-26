package com.sysu.verto.controller;

import com.sysu.verto.dao.UserDAO;
import com.sysu.verto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("register");
        if (userDAO.checkUserByPhoneNumberOrWeChatID(user.getPhoneNumber(), user.getWeChatID()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        boolean registered = userDAO.registerUser(user);
        if (registered) {
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.internalServerError().body("Failed to create user");
        }
    }
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("hello world");
        return "你好";
    }
}