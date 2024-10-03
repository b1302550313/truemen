package com.truemen.api.agreement.protocol;

import com.truemen.api.agreement.protocol.friend.AddFriendRequest;
import com.truemen.api.agreement.protocol.friend.AddFriendResponse;
import com.truemen.api.agreement.protocol.friend.SearchFriendRequest;
import com.truemen.api.agreement.protocol.friend.SearchFriendResponse;
import com.truemen.api.agreement.protocol.login.LoginRequest;
import com.truemen.api.agreement.protocol.login.LoginResponse;
import com.truemen.api.agreement.protocol.login.ReconnectRequest;
import com.truemen.api.agreement.protocol.msg.MsgGroupRequest;
import com.truemen.api.agreement.protocol.msg.MsgGroupResponse;
import com.truemen.api.agreement.protocol.msg.MsgRequest;
import com.truemen.api.agreement.protocol.msg.MsgResponse;
import com.truemen.api.agreement.protocol.talk.DelTalkRequest;
import com.truemen.api.agreement.protocol.talk.TalkNoticeRequest;
import com.truemen.api.agreement.protocol.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议包
 * 
 */
public abstract class Packet {

    private final static Map<Byte, Class<? extends Packet>> packetType = new ConcurrentHashMap<>();

    static {
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);
        packetType.put(Command.MsgRequest, MsgRequest.class);
        packetType.put(Command.MsgResponse, MsgResponse.class);
        packetType.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
        packetType.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
        packetType.put(Command.SearchFriendRequest, SearchFriendRequest.class);
        packetType.put(Command.SearchFriendResponse, SearchFriendResponse.class);
        packetType.put(Command.AddFriendRequest, AddFriendRequest.class);
        packetType.put(Command.AddFriendResponse, AddFriendResponse.class);
        packetType.put(Command.DelTalkRequest, DelTalkRequest.class);
        packetType.put(Command.MsgGroupRequest, MsgGroupRequest.class);
        packetType.put(Command.MsgGroupResponse, MsgGroupResponse.class);
        packetType.put(Command.ReconnectRequest, ReconnectRequest.class);
    }

    public static Class<? extends Packet> get(Byte command) {
        return packetType.get(command);
    }

    /**
     * 获取协议指令
     *
     * @return 返回指令值
     */
    public abstract Byte getCommand();

}
