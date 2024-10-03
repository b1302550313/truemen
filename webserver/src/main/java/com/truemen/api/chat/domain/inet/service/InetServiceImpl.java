package com.truemen.api.chat.domain.inet.service;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.truemen.api.chat.application.InetService;
import com.truemen.api.chat.domain.inet.model.ChannelUserInfo;
import com.truemen.api.chat.domain.inet.model.ChannelUserReq;
import com.truemen.api.chat.domain.inet.model.InetServerInfo;
import com.truemen.api.chat.domain.inet.repository.IInetRepository;
import com.truemen.api.chat.infrastructure.common.SocketChannelUtil;
import com.truemen.api.chat.socket.NettyServer;

import jakarta.annotation.Resource;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

/**

 */
@Service("inetService")
public class InetServiceImpl implements InetService {

    private Logger logger = LoggerFactory.getLogger(InetServiceImpl.class);

    @Value("${netty.ip}")
    private String ip;
    @Value("${netty.port}")
    private int port;

    @Resource
    private NettyServer nettyServer;
    @Resource
    private IInetRepository inetRepository;

    @Override
    public InetServerInfo queryNettyServerInfo() {
        InetServerInfo inetServerInfo = new InetServerInfo();
        inetServerInfo.setIp(ip);
        inetServerInfo.setPort(port);
        inetServerInfo.setStatus(nettyServer.channel().isActive());
        return inetServerInfo;
    }

    @Override
    public Long queryChannelUserCount(ChannelUserReq req) {
        return inetRepository.queryChannelUserCount(req);
    }

    @Override
    public List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req) {
        List<ChannelUserInfo> channelUserInfoList = inetRepository.queryChannelUserList(req);
        for (ChannelUserInfo channelUserInfo : channelUserInfoList) {
            Channel channel = SocketChannelUtil.getChannel(channelUserInfo.getUserId());
            if (null == channel || !channel.isActive()) {
                channelUserInfo.setStatus(false);
            } else {
                channelUserInfo.setStatus(true);
            }
        }
        return channelUserInfoList;
    }
}
