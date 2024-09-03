package com.sysu.verto.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sysu.verto.user.model.User;

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
            user.setUid(rs.getLong("uid"));
            user.setUserId(rs.getString("userId")); // 新增这一行，映射userId
            user.setUserName(rs.getString("userName"));
            user.setWechatId(rs.getString("wechatId"));
            user.setPhone(rs.getString("phone"));
            user.setPassword(rs.getString("password"));
            user.setAvatar(rs.getString("avatar"));
            user.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
            user.setPermission(rs.getInt("permission"));
            user.setBio(rs.getString("bio"));
            user.setGender(User.Gender.valueOf(rs.getString("gender")));
            return user;
        }
    };

    public User checkUserByPhoneNumberOrWeChatID(String phone, String wechatId) {
        String sql = "SELECT * FROM userCoreInfo uci JOIN userBaseInfo ubi ON uci.uid = ubi.uid WHERE uci.phone = ? OR uci.wechatId = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, phone, wechatId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean registerUser(User user) {
        String sqlCoreInfo = "INSERT INTO userCoreInfo (userId, phone, wechatId, password, permission, createTime) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo, user.getUserId(), user.getPhone(), user.getWechatId(),
                user.getPassword(), user.getPermission(), user.getCreateTime()); // 更新插入 userId

        String sqlBaseInfo = "INSERT INTO userBaseInfo (uid, userName, avatar, gender, birthDate, bio) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, user.getUid(), user.getUserName(), user.getAvatar(),
                user.getGender().name(), null, user.getBio());

        return rowsAffectedCore > 0 && rowsAffectedBase > 0;
    }

    public User getUserById(String userId) {
        String sql = "SELECT * FROM userCoreInfo uci JOIN userBaseInfo ubi ON uci.uid = ubi.uid WHERE uci.userId = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, userId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateUser(User user) {
        String sqlCoreInfo = "UPDATE userCoreInfo SET phone = ?, wechatId = ?, password = ?, permission = ? WHERE uid = ?";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo, user.getPhone(), user.getWechatId(), user.getPassword(),
                user.getPermission(), user.getUid());

        String sqlBaseInfo = "UPDATE userBaseInfo SET userName = ?, avatar = ?, gender = ?, bio = ? WHERE uid = ?";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, user.getUserName(), user.getAvatar(),
                user.getGender().name(), user.getBio(), user.getUid());

        return rowsAffectedCore > 0 && rowsAffectedBase > 0;
    }
}
