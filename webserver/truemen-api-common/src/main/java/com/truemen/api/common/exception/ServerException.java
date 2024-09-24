package com.truemen.api.common.exception;

import lombok.Data;

@Data
public class ServerException extends RuntimeException {

    private int code;
    private String msg;

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
