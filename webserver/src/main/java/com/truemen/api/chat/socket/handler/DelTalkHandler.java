package com.truemen.api.chat.socket.handler;

import io.netty.channel.Channel;

import com.truemen.api.agreement.protocol.talk.DelTalkRequest;

import com.truemen.api.chat.application.UserService;
import com.truemen.api.chat.socket.MyBizHandler;

/**

 */
public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {

    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}
