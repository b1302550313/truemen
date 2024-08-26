package com.sysu.verto.dao.impl;

import com.sysu.verto.dao.FriendshipDAO;
import com.sysu.verto.model.Friendship;
import com.sysu.verto.model.UserCoreInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FriendshipDAOImpl implements FriendshipDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Friendship> findFriendsByWeChatOrPhone(String identifier) {
        String sql = "SELECT f.id, f.friend_id, f.created_at, u.* " +
                "FROM friendship f " +
                "JOIN user_core_info u ON f.uid = u.uid " +
                "WHERE u.wechat_id = ? OR u.phone = ?";

        return jdbcTemplate.query(sql, new Object[]{identifier, identifier}, this::mapRowToFriendship);
    }

    private Friendship mapRowToFriendship(ResultSet rs, int rowNum) throws SQLException {
        UserCoreInfo user = new UserCoreInfo();
        user.setUid(rs.getLong("uid"));
        user.setPhone(rs.getString("phone"));
        user.setPermission(rs.getLong("permission"));

        // 将 Timestamp 转换为 LocalDateTime
        Timestamp timestamp = rs.getTimestamp("create_time");
        LocalDateTime createTime = timestamp != null ? timestamp.toLocalDateTime() : null;
        user.setCreateTime(createTime);

        Friendship friendship = new Friendship();
        friendship.setId(rs.getLong("id"));
        friendship.setUser(user);
        friendship.setFriendId(rs.getLong("friend_id"));

        // 将 Timestamp 转换为 LocalDateTime
        Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
        LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
        friendship.setCreatedAt(Timestamp.valueOf(createdAt));

        return friendship;
    }
}
