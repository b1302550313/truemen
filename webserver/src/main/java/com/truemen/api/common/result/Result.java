package com.truemen.api.common.result;

import com.truemen.api.common.exception.ErrorCode;

import lombok.Data;

@Data
public class Result<T> {

    private int code = 0;
    private String msg = "success";
    private T data;

    public static <G> Result <G> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}