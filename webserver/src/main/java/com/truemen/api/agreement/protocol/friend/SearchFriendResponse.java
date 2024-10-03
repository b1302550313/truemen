package com.truemen.api.agreement.protocol.friend;

import com.truemen.api.agreement.protocol.Command;
import com.truemen.api.agreement.protocol.Packet;
import com.truemen.api.agreement.protocol.friend.dto.UserDto;

import java.util.List;

/**
 * 搜索好友应答
 */
public class SearchFriendResponse extends Packet {

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
