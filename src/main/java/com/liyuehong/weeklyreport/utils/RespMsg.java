package com.liyuehong.weeklyreport.utils;

/**
 * @author yhli3
 * @classname RespMsg
 * @Date 2021/11/21 15:02
 */
public class RespMsg {
    private String status;
    private String msg;

    public RespMsg() {
    }

    public RespMsg(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public RespMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
