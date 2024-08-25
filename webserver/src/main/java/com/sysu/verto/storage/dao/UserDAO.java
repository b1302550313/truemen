package com.sysu.verto.storage.dao;

import com.sysu.verto.storage.model.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User();
        user.setUserID(rs.getInt("UserID"));
        user.setUserName(rs.getString("UserName"));
        user.setWeChatID(rs.getString("WeChatID"));
        user.setPhoneNumber(rs.getString("PhoneNumber"));
        user.setPassword(rs.getString("Password"));
        user.setAvatar(rs.getString("Avatar"));
        user.setPermissionLevel(rs.getInt("PermissionLevel"));
        return user;
    };

    public User checkUserByPhoneNumberOrWeChatID(String phoneNumber, String weChatID) {
            String sql = "SELECT * FROM user WHERE PhoneNumber = ? OR WeChatID = ?";
            try {
                User user = jdbcTemplate.queryForObject(sql, userRowMapper, phoneNumber, weChatID);
                return user;
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (UserName, WeChatID, PhoneNumber, Password, Avatar, PermissionLevel) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getUserName(), user.getWeChatID(), user.getPhoneNumber(), user.getPassword(), user.getAvatar(), user.getPermissionLevel());
        return rowsAffected > 0;
    }
}
