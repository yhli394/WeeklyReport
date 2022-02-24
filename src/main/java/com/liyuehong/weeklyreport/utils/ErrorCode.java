package com.liyuehong.weeklyreport.utils;

import org.springframework.http.HttpStatus;



public enum ErrorCode {

    // TODO: 2022/2/24  如何根据占位符来填写message?
    RESOURCE_NOT_FOUND(404,"未找到该资源"),
    REQUEST_VALIDATION_FAILED(100, "请求数据格式验证失败"),
    DUPLICATE_USERNAME(409,"用户名重复，请更换用户名"),
    NULL_POINTER_EXCEPTION(404,"空指针异常"),
    OPERATION_FAILED(409,"操作失败"),
    PARAMS_ERROR(404,"{}"),
    UPDATE_FAILED(404,"更新失败!");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return"ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }


}
