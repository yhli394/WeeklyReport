package com.liyuehong.weeklyreport.configuration;

import com.liyuehong.weeklyreport.utils.ErrorReponse;
import com.liyuehong.weeklyreport.utils.RespMsg;
import com.sun.deploy.association.RegisterFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //打印日志
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest req) {
        logger.error(ex.getMessage(),ex);
        ErrorReponse representation = new ErrorReponse(ex, req.getRequestURI());
        return new ResponseEntity<>(representation, new HttpHeaders(), ex.getError().getStatus());
    }

    /**
     * 针对RegisterFailedException这个特定的异常进行处理
     * @param e
     * @return
     */
//    @ExceptionHandler(RegisterFailedException.class)
//    RespMsg handleException1(RegisterFailedException e){
//        logger.error("RegisterFailedException->"+e.getMessage(),e);
//        return new RespMsg(e.getMessage(),"888");
//    }




}
