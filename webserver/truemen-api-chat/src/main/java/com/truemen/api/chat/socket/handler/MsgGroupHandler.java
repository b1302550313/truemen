package com.truemen.api.chat.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import com.truemen.api.agreement.protocol.msg.MsgGroupRequest;
import com.truemen.api.agreement.protocol.msg.MsgGroupResponse;

import com.truemen.api.chat.application.UserService;
import com.truemen.api.chat.domain.user.model.ChatRecordInfo;
import com.truemen.api.chat.domain.user.model.UserInfo;
import com.truemen.api.chat.infrastructure.common.Constants;
import com.truemen.api.chat.infrastructure.common.SocketChannelUtil;
import com.truemen.api.chat.socket.MyBizHandler;

/**
 * 
 * <p>
 * 群组消息发送
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupRequest> {

    public MsgGroupHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        // 获取群组通信管道
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        if (null == channelGroup) {
            SocketChannelUtil.addChannelGroup(msg.getTalkId(), channel);
            channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        }

        // 异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getTalkId(), msg.getMsgText(),
                msg.getMsgType(), msg.getMsgDate(), Constants.TalkType.Group.getCode()));
        // 群发消息
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);
    }

}
