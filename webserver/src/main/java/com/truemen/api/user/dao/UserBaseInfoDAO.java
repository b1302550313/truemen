package com.truemen.api.user.dao;

import com.truemen.api.user.model.UserBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserBaseInfoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserBaseInfo getUserBaseInfoByUid(Long uid) {
        String sql = "SELECT * FROM userbaseinfo WHERE uid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{uid}, (rs, rowNum) -> {
            UserBaseInfo userBaseInfo = new UserBaseInfo();
            userBaseInfo.setUid(rs.getLong("uid"));
            userBaseInfo.setUserName(rs.getString("userName"));
            userBaseInfo.setAvatar(rs.getString("avatar"));
            userBaseInfo.setGender(rs.getString("gender"));
            userBaseInfo.setBirthDate(rs.getDate("birthDate"));
            userBaseInfo.setBio(rs.getString("bio"));
            userBaseInfo.setZodiac(rs.getString("zodiac"));
            return userBaseInfo;
        });
    }
}
