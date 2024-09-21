package com.sysu.verto.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "还未授权，不能访问"), // 未登陆
    FORBIDDEN(403, "没有权限，禁止访问"), // 用户缺少权限发生
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"); // 逻辑异常

    private final int code;
    private final String msg;
}