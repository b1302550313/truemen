package com.truemen.api.chat.application;

import java.util.List;

import com.truemen.api.chat.domain.inet.model.ChannelUserInfo;
import com.truemen.api.chat.domain.inet.model.ChannelUserReq;
import com.truemen.api.chat.domain.inet.model.InetServerInfo;

/**
 *
 * 
 * 网络信息查询
 */
public interface InetService {

    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
