package com.sysu.verto.dao;

import com.sysu.verto.model.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("UserID"));
        user.setUserName(rs.getString("UserName"));
        user.setWeChatID(rs.getString("WeChatID"));
        user.setPhoneNumber(rs.getString("PhoneNumber"));
        user.setPassword(rs.getString("Password"));
        user.setAvatar(rs.getString("Avatar"));
        user.setRegistrationTime(rs.getTimestamp("RegistrationTime").toLocalDateTime());
        user.setPermissionLevel(User.PermissionLevel.valueOf(rs.getString("PermissionLevel")));
        return user;
    }
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
        String sql = "INSERT INTO user (UserName, WeChatID, PhoneNumber, Password, Avatar, RegistrationTime, PermissionLevel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getUserName(), user.getWeChatID(), user.getPhoneNumber(), user.getPassword(), user.getAvatar(), user.getRegistrationTime(), user.getPermissionLevel().name());
        return rowsAffected > 0;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE UserID = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, userId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE user SET UserName = ?, WeChatID = ?, PhoneNumber = ?, Password = ?, Avatar = ?, PermissionLevel = ? WHERE UserID = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getUserName(), user.getWeChatID(), user.getPhoneNumber(), user.getPassword(), user.getAvatar(), user.getPermissionLevel(), user.getUserID());
        return rowsAffected > 0;
    }
}
