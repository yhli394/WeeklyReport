package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 登录接口
     * @param person
     * @return
     */
    @PostMapping("/login")
    public RespMsg login(@RequestBody Map<String,String> person){
        String username = person.get("username");
        String password = person.get("password");
        //判断用户名和密码是否为空
        if(username!=null&&password!=null){
            User users = (User) userService.loadUserByUsername(username);
            //判断用户名是否存在
            if(users!=null){
                //判断密码是否正确
                if(password.equals(users.getPassword())){
                    //密码正确
                    return new RespMsg(200,"登录成功");
                }else{
                    return new RespMsg(500,"登录失败");
                }
            }else{
                return new RespMsg(500,"用户名不存在");
            }
        }else{
            return new RespMsg(500,"登录失败，请检查用户名和密码是否为空");
        }
    }

}
