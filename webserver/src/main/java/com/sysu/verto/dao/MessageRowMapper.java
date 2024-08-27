package com.sysu.verto.dao;

import com.sysu.verto.model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setSenderId(rs.getLong("sender_id"));
        message.setReceiverId(rs.getLong("receiver_id"));
        message.setContent(rs.getString("content"));
        message.setTimestamp(rs.getTimestamp("timestamp").toInstant());
        return message;
    }
}
