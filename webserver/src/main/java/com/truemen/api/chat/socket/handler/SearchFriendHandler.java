package com.truemen.api.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import com.truemen.api.chat.application.UserService;
import com.truemen.api.chat.domain.user.model.LuckUserInfo;
import com.truemen.api.chat.socket.MyBizHandler;

import io.netty.channel.Channel;

import com.truemen.api.agreement.protocol.friend.SearchFriendRequest;
import com.truemen.api.agreement.protocol.friend.SearchFriendResponse;
import com.truemen.api.agreement.protocol.friend.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        logger.info("搜索好友请求处理：{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }

}
