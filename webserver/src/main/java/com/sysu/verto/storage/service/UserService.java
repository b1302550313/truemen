package com.sysu.verto.storage.service;

import com.sysu.verto.storage.dao.UserDAO;
import com.sysu.verto.storage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User checkUser(String phoneNumber, String weChatID) {
        return userDAO.checkUserByPhoneNumberOrWeChatID(phoneNumber, weChatID);
    }

    public boolean register(User user) {
        if (userDAO.checkUserByPhoneNumberOrWeChatID(user.getPhoneNumber(), user.getWeChatID()) != null) {
            return false; // 用户已存在
        }
        return userDAO.registerUser(user);
    }
}
