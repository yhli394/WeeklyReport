package com.liyuehong.weeklyreport.configuration;

import com.google.common.collect.ImmutableMap;
import com.liyuehong.weeklyreport.utils.ErrorCode;

import java.util.Map;

public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
    private Integer code;
    private Map<String,Object> data;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public CustomException(ErrorCode errorCode){
        this.errorCode=errorCode;
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }


    public CustomException(ErrorCode errorCode, ImmutableMap<String, Object> of) {
        this.errorCode=errorCode;
        this.data=of;
    }

}
