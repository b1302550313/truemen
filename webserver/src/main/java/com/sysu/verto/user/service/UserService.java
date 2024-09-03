package com.sysu.verto.user.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysu.verto.user.dao.UserDAO;
import com.sysu.verto.user.model.User;

// 登录注册相关
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

    public User checkUser(String phone, String wechatId) {
        return userDAO.checkUserByPhoneNumberOrWeChatID(phone, wechatId);
    }

    public boolean register(User user) {
        if (userDAO.checkUserByPhoneNumberOrWeChatID(user.getPhone(), user.getWechatId()) != null) {
            return false; // 用户已存在
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setPermission(1);

        // 如果 userId 为空，生成默认值
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(generateUserId(user));
        }

        return userDAO.registerUser(user);
    }

    public User getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    private String generateUserId(User user) {
        // 生成 userId 的逻辑，例如基于用户名或其他规则生成
        return "user_" + System.currentTimeMillis(); // 基于当前时间戳生成
    }
}
