package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author yhli3
 * @classname LoginController
 * @Date 2021/11/21 15:03
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public RespMsg login(@RequestBody User user){
        User users =userService.loadUserByUsername(user.getUsername());
        if(users!=null){
            return new RespMsg("登录成功");
        }else{
            return new RespMsg("登录失败!");
        }
    }



}
