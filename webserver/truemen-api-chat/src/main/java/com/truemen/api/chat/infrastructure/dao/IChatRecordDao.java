package com.truemen.api.chat.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import com.truemen.api.chat.infrastructure.po.ChatRecord;

import java.util.List;

/** 

 */
@Mapper
public interface IChatRecordDao {

    void appendChatRecord(ChatRecord req);

    List<ChatRecord> queryUserChatRecordList(String talkId, String userId);

    List<ChatRecord> queryGroupsChatRecordList(String talkId, String userId);

}
