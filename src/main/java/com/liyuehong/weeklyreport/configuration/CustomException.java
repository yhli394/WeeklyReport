package com.liyuehong.weeklyreport.configuration;

import com.google.common.collect.ImmutableMap;
import com.liyuehong.weeklyreport.utils.ErrorInfoEnum;

import java.util.Map;

public class CustomException extends RuntimeException{
    private ErrorInfoEnum errorInfoEnum;
    private Map<String,Object> data;

    public ErrorInfoEnum getErrorCode() {
        return errorInfoEnum;
    }

    public void setErrorCode(ErrorInfoEnum errorInfoEnum) {
        this.errorInfoEnum = errorInfoEnum;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public CustomException(ErrorInfoEnum errorInfoEnum, ImmutableMap<String, Object> of) {
        this.errorInfoEnum = errorInfoEnum;
        this.data=of;
    }

}
