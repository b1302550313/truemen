package com.truemen.api.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.truemen.api.user.dao.UserBaseInfoDAO;
import com.truemen.api.user.model.UserBaseInfo;
import com.truemen.api.user.model.UserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.truemen.api.user.dao.UserDAO;
import com.truemen.api.user.model.User;
import com.truemen.api.user.model.WechatUserInfo;
import com.truemen.api.user.storage.UserRepository;

import jakarta.servlet.http.HttpSession;

// 登录注册相关
@Service
public class UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Autowired
    public UserService(UserRepository userRepository, HttpSession httpSession, UserDAO userDAO,
                       BCryptPasswordEncoder passwordEncoder, UserBaseInfoDAO userBaseInfoDAO) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.userBaseInfoDAO = userBaseInfoDAO;
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
            System.out.println("registerByPhone 用户已经存在");
            return false; // 用户已存在
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setPermission(1);

        // 如果 userId 为空，生成默认值
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(generateUserId(user));
        }
        return userDAO.registerUserByPhone(user);
    }

    public User getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    public User getUserByUid(Long uid) {
        System.out.println("getUserByUid service called");
        return userDAO.getUserByUid(uid);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // 更新用户基本信息
    public boolean updateUserBase(User user) {
        return userDAO.updateUserBase(user);
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

    public List<UserLocation> getUserLocations() {
        List<UserLocation> userLocations = new ArrayList<>();
        // 示例数据，实际应该从数据库获取
        userLocations.add(new UserLocation("123", "http://example.com/avatar123.jpg", 34.0522, -118.2437));
        userLocations.add(new UserLocation("456", "http://example.com/avatar456.jpg", 34.0523, -118.2438));
        return userLocations;
    }

    private final UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    public UserService(UserBaseInfoDAO userBaseInfoDAO) {
        this.userBaseInfoDAO = userBaseInfoDAO;
    }

    public UserBaseInfo getUserBaseInfo(Long uid) {
        return userBaseInfoDAO.getUserBaseInfoByUid(uid);
    }

}
