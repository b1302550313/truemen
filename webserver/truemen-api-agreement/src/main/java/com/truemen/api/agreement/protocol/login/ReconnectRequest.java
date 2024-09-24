package com.truemen.api.agreement.protocol.login;

import com.truemen.api.agreement.protocol.Command;
import com.truemen.api.agreement.protocol.Packet;

/**

 */
public class ReconnectRequest extends Packet {

    private String userId;

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.ReconnectRequest;
    }
}
