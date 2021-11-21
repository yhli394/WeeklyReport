package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhli3
 * @classname RigController
 * @Date 2021/11/21 19:41
 */
@RestController
public class RigController {
    @Autowired
    UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/reg")
    public RespMsg reg(@RequestBody User user){
        int res =userService.addUser(user);
        if(res==0){
            return new RespMsg(200,"注册成功");
        }else{
            return new RespMsg(500,"用户名重复，注册失败!");
        }
    }
}
