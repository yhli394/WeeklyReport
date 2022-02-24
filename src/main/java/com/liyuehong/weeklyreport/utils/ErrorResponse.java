package com.liyuehong.weeklyreport.utils;

import java.time.Instant;
import java.util.Map;

public class ErrorResponse {
    //状态码
    private int code;
    //响应信息
    private String message;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public ErrorResponse(int code, String message, Instant timestamp, String url, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.url = url;
        this.data = data;
    }
}
