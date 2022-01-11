package com.liyuehong.weeklyreport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
