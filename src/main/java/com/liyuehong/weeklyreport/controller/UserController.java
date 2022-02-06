package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询所有的用户")
    @GetMapping("/selectAllUsers")
    public List<User> selectAllUser(){
        return userService.selectAllUsers();
    }
}
