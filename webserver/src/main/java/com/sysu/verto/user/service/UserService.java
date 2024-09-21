package com.sysu.verto.user.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysu.verto.user.dao.UserDAO;
import com.sysu.verto.user.model.User;
import com.sysu.verto.user.model.WechatUserInfo;
import com.sysu.verto.user.storage.UserRepository;

import jakarta.servlet.http.HttpSession;

// 登录注册相关
@Service
public class UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Autowired
    public UserService(UserRepository userRepository, HttpSession httpSession, UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
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
    // 手机注册
    public boolean registerByPhone(User user) {
        System.out.println("registerByPhone called");
        if (userDAO.checkUserByPhoneNumberOrWeChatID(user.getPhone(), user.getWechatId()) != null) {
            System.out.println("registerByPhone checked");
            return false; // 用户已存在
        }
        System.out.println("debug1");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setPermission(1);
        System.out.println("debug2");

        // 如果 userId 为空，生成默认值
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(generateUserId(user));
        }
        System.out.println(user.getUserId());
        System.out.println("registerByPhone");
        return userDAO.registerUserByPhone(user);
    }

    public User getUserById(String userId) {
        return userDAO.getUserById(userId);
    }
    public User getUserByUid(String userId) {
        return userDAO.getUserByUid(userId);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    private String generateUserId(User user) {
        // 生成 userId 的逻辑，例如基于用户名或其他规则生成
        return "user_" + System.currentTimeMillis(); // 基于当前时间戳生成
    }

    public User createNewUser(WechatUserInfo userInfo) {
        User user = new User();
        user.setUserId(generateUserId(user));
        user.setUserId(userInfo.getOpenId());
        user.setUserName(userInfo.getNickname());
        user.setAvatar(userInfo.getAvatar());
        User savedUser = userRepository.save(user);

        // 将用户信息存储在 HttpSession 中
        httpSession.setAttribute("user", savedUser);

        return savedUser;
    }
}
