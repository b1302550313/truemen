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

    public ServerException(String msg){
        super(msg);
        this.code=ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg=msg;
    }

}
