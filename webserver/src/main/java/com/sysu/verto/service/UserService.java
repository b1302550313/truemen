package com.sysu.verto.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysu.verto.dao.UserDAO;
import com.sysu.verto.model.User;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User checkUser(String phoneNumber, String weChatID) {
        return userDAO.checkUserByPhoneNumberOrWeChatID(phoneNumber, weChatID);
    }

    public boolean register(User user) {
        if (userDAO.checkUserByPhoneNumberOrWeChatID(user.getPhoneNumber(), user.getWeChatID()) != null) {
            return false; // 用户已存在
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationTime(LocalDateTime.now());
        user.setPermissionLevel(User.PermissionLevel.User);
        return userDAO.registerUser(user);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
