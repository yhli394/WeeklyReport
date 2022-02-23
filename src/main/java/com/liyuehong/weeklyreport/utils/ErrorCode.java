package com.liyuehong.weeklyreport.utils;

import org.springframework.http.HttpStatus;


public enum ErrorCode {

    RESOURCE_NOT_FOUND(1001,"未找到该资源"),
    REQUEST_VALIDATION_FAILED(1002, "请求数据格式验证失败"),
    DUPLICATE_USERNAME(407,"用户名重复");

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
