package com.liyuehong.weeklyreport.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhli3
 * @classname SessionController
 * @Date 2022/1/10 19:19
 */
@RestController
public class SessionController {
    @GetMapping("/session/invalid")
    @ResponseStatus
    public String sessionInvalid(){
        return "session失效，请重新登录";
    }

    @ApiOperation(value = "获取sessionId，用户登陆后调用此接口")
    @GetMapping("/getSessionId")
    public String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

}

