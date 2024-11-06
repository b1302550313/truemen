package com.truemen.api.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.truemen.api.user.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
            // user.setBirthDate(rs.getDate("birthDate").toLocalDate());
            Date sqlBirthDate = rs.getDate("birthDate");
            if (sqlBirthDate != null) {
                LocalDate birthDate = sqlBirthDate.toLocalDate();
                user.setBirthDate(birthDate);
            } else {
                // 如果数据库中的 birthDate 是 null，可以决定如何处理
                // 例如，可以设置为 null 或者一个默认日期
                user.setBirthDate(null); // 或者 user.setBirthDate(LocalDate.of(1970, 1, 1));
            }
            user.setGender(User.Gender.valueOf(rs.getString("gender")));
            user.setZodiac(rs.getString("zodiac"));
            user.setTags(rs.getString("tags")); // 映射 tags 字段
            return user;
        }
    };

    public User checkUserByPhoneNumberOrWeChatID(String phone, String wechatId) {
        System.out.println("userDAO checkUserByPhoneNumberOrWeChatID");
        String sql = "SELECT * FROM userCoreInfo uci JOIN userBaseInfo ubi ON uci.uid = ubi.uid WHERE uci.phone = ? OR uci.wechatId = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, phone, wechatId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public boolean registerUser(User user) {
        String sqlCoreInfo = "INSERT INTO userCoreInfo (userId, phone, wechatId, password, permission, createTime) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo, user.getUserId(), user.getPhone(), user.getWechatId(),
                user.getPassword(), user.getPermission(), user.getCreateTime()); // 更新插入 userId

        String sqlBaseInfo = "INSERT INTO userBaseInfo (uid, userName, avatar, gender, birthDate, bio, zodiac, tags) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, user.getUid(), user.getUserName(), user.getAvatar(),
                user.getGender().name(), user.getBirthDate(), user.getBio(), user.getZodiac(), user.getTags());

        return rowsAffectedCore > 0 && rowsAffectedBase > 0;
    }

    @Transactional
    public boolean registerUserByPhone(User user) {
        System.out.println("userDAO registerUserByPhone");
        String sqlCoreInfo = "INSERT INTO userCoreInfo (userId, phone, password, permission, createTime) VALUES (?, ?, ?, ?, ?)";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo, user.getUserId(), user.getPhone(),
                user.getPassword(), user.getPermission(), user.getCreateTime()); // 更新插入 userId
        // 获取自增主键值
        Long generatedUid = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Long.class);

        if (generatedUid == null) {
            System.err.println("Failed to retrieve the generated UID.");
            return false;
        } else {
            System.out.println("Generated UID: " + generatedUid);
            user.setUid(generatedUid);
        }
        String sqlBaseInfo = "INSERT INTO userBaseInfo (uid, userName, avatar, gender, birthDate, bio, zodiac, tags) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, generatedUid, user.getUserName(), user.getAvatar(),
                user.getGender().name(), user.getBirthDate(), user.getBio(), user.getZodiac(), user.getTags());
        System.out.println(user.getPassword() + " " + user.getPhone());

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

    public User getUserByUid(Long uid) {
        String sql = "SELECT * FROM userCoreInfo uci JOIN userBaseInfo ubi ON uci.uid = ubi.uid WHERE uci.uid = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, uid);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public boolean updateUser(User user) {
        String sqlCoreInfo = "UPDATE userCoreInfo SET phone = ?, wechatId = ?, password = ?, permission = ? WHERE uid = ?";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo, user.getPhone(), user.getWechatId(), user.getPassword(),
                user.getPermission(), user.getUid());

        String sqlBaseInfo = "UPDATE userBaseInfo SET userName = ?, avatar = ?, gender = ?, bio = ?, zodiac = ?, tags = ? WHERE uid = ?";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, user.getUserName(), user.getAvatar(),
                user.getGender().name(), user.getBio(), user.getZodiac(), user.getTags(), user.getUid());

        return rowsAffectedCore > 0 && rowsAffectedBase > 0;
    }

    @Transactional
    public boolean updateUserBase(User user) {
        System.out.println("DAO updateUserBase called");
        System.out.println(user);
        String sqlBaseInfo = "UPDATE userBaseInfo SET userName = ?, birthDate = ?, gender = ?, bio = ?, zodiac = ?, tags = ? WHERE uid = ?";
        int rowsAffectedBase = jdbcTemplate.update(sqlBaseInfo, user.getUserName(), user.getBirthDate(),
                user.getGender().name(), user.getBio(), user.getZodiac(), user.getTags(), user.getUid());

        String sqlCoreInfo = "UPDATE userCoreInfo SET userId = ? WHERE uid = ?";
        int rowsAffectedCore = jdbcTemplate.update(sqlCoreInfo,
                user.getUserId(), user.getUid());
        return rowsAffectedCore > 0 && rowsAffectedBase > 0;
    }
}