package com.liyuehong.weeklyreport.configuration;

import com.liyuehong.weeklyreport.utils.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    //打印日志
    private static Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    //返回类型为ResponseEntity<>会报媒体类型错误
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAppException(Exception e) {
        CustomException ce = ((CustomException) e);
        int code = ce.getErrorCode().getCode();
        String message = ce.getErrorCode().getMessage();
        return new ErrorResponse(code,message,Instant.now(),ce.getData());
    }

}
