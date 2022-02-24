package com.liyuehong.weeklyreport.configuration;

import com.liyuehong.weeklyreport.utils.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


/**
 * @author yhli3
 */
@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 打印日志
     */
    private static Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    /**
     * 返回类型为ResponseEntity<>会报媒体类型错误
     * @param e
     * @param req
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAppException(Exception e,HttpServletRequest req) {
        logger.debug(e.getMessage(),e);
        String url = req.getRequestURL().toString();
        CustomException ce = ((CustomException) e);
        int code = ce.getErrorCode().getCode();
        String message = ce.getErrorCode().getMessage();
        return new ErrorResponse(code,message,Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)),url,ce.getData());
    }

}
