package com.liyuehong.weeklyreport.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

//序列化枚举对象
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorInfoEnum {

    /**
     * 自定义异常
     */
    RESOURCE_NOT_FOUND(404,"未找到该资源","RESOURCE_NOT_FOUND"),
    DUPLICATE_USERNAME(409,"用户名重复，请更换用户名","DUPLICATE_USERNAME"),
    NULL_POINTER_EXCEPTION(404,"传过来的内容为空","NULL_POINTER_EXCEPTION"),
    OPERATION_FAILED(409,"操作失败","OPERATION_FAILED"),
    PARAMS_ERROR(404,"参数不符合规范","PARAMS_ERROR"),
    CREATE_ARTICLE_FAILED(404,"第一次创建文章不需要传递文章id","CREATE_FAILED"),
    UPDATE_FAILED(404,"数据库更新失败!","UPDATE_FAILED");

    private final int code;
    private final String message;
    private final String reasonPhrase;

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    ErrorInfoEnum(int code, String message, String reasonPhrase) {
        this.code = code;
        this.message = message;
        this.reasonPhrase = reasonPhrase;
    }

}
