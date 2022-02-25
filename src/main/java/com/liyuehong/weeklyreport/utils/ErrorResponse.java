package com.liyuehong.weeklyreport.utils;

import java.time.Instant;
import java.util.Map;

public class ErrorResponse {

    private ErrorInfoEnum errorInfo;

    //时间戳
    private Instant timestamp;
    //请求的url
    private String url;
    //异常数据
    private Map<String,Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ErrorInfoEnum getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoEnum errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorResponse(ErrorInfoEnum errorInfo, Instant timestamp, String url, Map<String, Object> data) {
        this.errorInfo = errorInfo;
        this.timestamp = timestamp;
        this.url = url;
        this.data = data;
    }
}
