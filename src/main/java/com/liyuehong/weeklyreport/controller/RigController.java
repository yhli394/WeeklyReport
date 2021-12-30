package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("只需要传用户名和密码即可")
    @PostMapping("/register")
    public RespMsg reg(User user){
        Boolean res =userService.addUser(user);
        if(res){
            return new RespMsg("success","注册成功！");
        }else{
            return new RespMsg("error","用户名重复，注册失败!");
        }
    }

}
