package com.liyuehong.weeklyreport.configuration;

import com.liyuehong.weeklyreport.utils.RespMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //打印日志
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Exception及其子类的异常都会被处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    RespMsg handleException(Exception e){
        logger.error(e.getMessage(),e);
        return new RespMsg(e.getMessage());
    }

    /**
     * 针对某一个异常进行特定的处理
     * @param e
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    RespMsg handleException1(UsernameNotFoundException e){
        logger.error(e.getMessage(),e);
        return new RespMsg(e.getMessage());
    }


}
