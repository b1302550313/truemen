package com.truemen.api.chat.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import com.truemen.api.chat.domain.inet.model.ChannelUserReq;
import com.truemen.api.chat.infrastructure.po.User;

import java.util.List;

/** 

 */
@Mapper
public interface IUserDao {

    String queryUserPassword(String userId);

    User queryUserById(String userId);

    List<User> queryFuzzyUserList(String userId, String searchKey);

    Long queryChannelUserCount(ChannelUserReq req);

    List<User> queryChannelUserList(ChannelUserReq req);

}
