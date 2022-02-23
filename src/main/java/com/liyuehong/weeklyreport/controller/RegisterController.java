package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.configuration.CustomException;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhli3
 * @classname RigController
 * @Date 2021/11/21 19:41
 */
@RestController
public class RegisterController {
    @Autowired
    UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @ApiOperation("用户注册接口,enabled(默认为true即可),username,password,email需要填，其他字段不用填")
//    @ApiImplicitParams({ @ApiImplicitParam(name = "enabled",value = "必填，默认为1，不用改",required = true,defaultValue ="1",dataType = "int"),@ApiImplicitParam(name = "email",value = "必填",required = true),@ApiImplicitParam(name = "username",value = "必填",required = true),@ApiImplicitParam(name = "password",value = "必填",required = true)})
    @PostMapping(value = "/register")
    public RespMsg reg(@RequestBody User user) throws CustomException {
        Boolean res =userService.addUser(user);
        if(res){
            return new RespMsg("success","注册成功！");
        }else{
            return new RespMsg("error","用户名重复，注册失败!");
        }
    }
}
