package com.sysu.verto.storage.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// import com.sysu.verto.storage.dao.MessageRowMapper;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MessageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // public List<Message> findMessagesBetweenUsers(Long userId1, Long userId2) {
    //     String sql = "SELECT * FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)";
    //     return jdbcTemplate.query(sql, new Object[] { userId1, userId2, userId2, userId1 }, new MessageRowMapper());
    // }
}
