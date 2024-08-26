package com.sysu.verto.service;

import com.sysu.verto.dao.MessageDao;
import com.sysu.verto.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public List<Message> getMessagesBetweenUsers(Long userId1, Long userId2) {
        return messageDao.findMessagesBetweenUsers(userId1, userId2);
    }
}
