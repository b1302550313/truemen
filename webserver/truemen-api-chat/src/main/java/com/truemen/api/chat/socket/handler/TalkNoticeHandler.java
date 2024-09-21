package com.truemen.api.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import com.truemen.api.chat.application.UserService;
import com.truemen.api.chat.domain.user.model.UserInfo;
import com.truemen.api.chat.infrastructure.common.Constants;
import com.truemen.api.chat.infrastructure.common.SocketChannelUtil;
import com.truemen.api.chat.socket.MyBizHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.truemen.api.agreement.protocol.talk.TalkNoticeRequest;
import com.truemen.api.agreement.protocol.talk.TalkNoticeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 
 * <p>
 * 对话通知应答处理
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeRequest> {

    public TalkNoticeHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, TalkNoticeRequest msg) {
        logger.info("对话通知应答处理：{}", JSON.toJSONString(msg));

        switch (msg.getTalkType()) {
            case 0:
                // 1. 对话框数据落库
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 0);
                userService.addTalkBoxInfo(msg.getFriendUserId(), msg.getUserId(), 0);
                // 2. 查询对话框信息[将自己发给好友的对话框中]
                UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
                // 3. 发送对话框消息给好友
                TalkNoticeResponse response = new TalkNoticeResponse();
                response.setTalkId(userInfo.getUserId());
                response.setTalkName(userInfo.getUserNickName());
                response.setTalkHead(userInfo.getUserHead());
                response.setTalkSketch(null);
                response.setTalkDate(new Date());
                // 获取好友通信管道
                Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendUserId());
                if (null == friendChannel) {
                    logger.info("用户id：{}未登录！", msg.getFriendUserId());
                    return;
                }
                friendChannel.writeAndFlush(response);
                break;
            case 1:
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 1);
                break;
            default:
                break;
        }

    }

}
