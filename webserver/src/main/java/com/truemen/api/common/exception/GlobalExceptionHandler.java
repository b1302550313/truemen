package com.truemen.api.common.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.truemen.api.common.result.Result;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * file size too large
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<String> handleFileSizeException(MaxUploadSizeExceededException ex) {
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "File size too large!");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public Result<String> handleException(ServerException ex) {
        return Result.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * 处理其他所有未被特定异常处理器捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }
}