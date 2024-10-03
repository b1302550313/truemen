package com.truemen.api.chat.domain.inet.repository;

import java.util.List;

import com.truemen.api.chat.domain.inet.model.ChannelUserInfo;
import com.truemen.api.chat.domain.inet.model.ChannelUserReq;

/**

 */
public interface IInetRepository {

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
