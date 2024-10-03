package com.truemen.api.chat.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.truemen.api.chat.domain.inet.model.ChannelUserInfo;
import com.truemen.api.chat.domain.inet.model.ChannelUserReq;
import com.truemen.api.chat.domain.inet.repository.IInetRepository;
import com.truemen.api.chat.infrastructure.dao.IUserDao;
import com.truemen.api.chat.infrastructure.po.User;

import java.util.ArrayList;
import java.util.List;

/** 

 */
@Repository("inetRepository")
public class InetRepository implements IInetRepository {

    @Autowired
    private IUserDao userDao;

    @Override
    public Long queryChannelUserCount(ChannelUserReq req) {
        return userDao.queryChannelUserCount(req);
    }

    @Override
    public List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req) {
        List<ChannelUserInfo> channelUserInfoList = new ArrayList<>();
        List<User> userList = userDao.queryChannelUserList(req);
        for (User user : userList) {
            ChannelUserInfo channelUserInfo = new ChannelUserInfo();
            channelUserInfo.setUserId(user.getUserId());
            channelUserInfo.setUserNickName(user.getUserNickName());
            channelUserInfo.setUserHead(user.getUserHead());
            channelUserInfoList.add(channelUserInfo);
        }
        return channelUserInfoList;
    }
}