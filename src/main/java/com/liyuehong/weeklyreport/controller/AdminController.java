package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    /**
     * allowMultiple = true//表示数组类型的参数
     * @param uid
     * @param rids
     * @return
     */
    // TODO: 2022/2/9 使用int uid,int[] rids和Integer uid,Integer[] rids的区别？
    @ApiOperation("更改用户角色接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "uid",value = "用户id",required = true,dataType = "int"),@ApiImplicitParam(name = "rids",value = "存放用户持有的角色id的数组",required = true,allowMultiple = true,dataType = "int")})
    @PutMapping("/user/role")
    public RespMsg updateUserRolesById(int uid,int[] rids){
        int i = userService.updateUserRolesById(uid,rids);
        if(i==rids.length){
            return new RespMsg("true","更新成功");
        }
        return new RespMsg("false","更新失败");
    }

    @ApiOperation("更改用户账号状态接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "uid",value = "用户id",required = true,dataType = "int"),@ApiImplicitParam(name = "enabled",value = "ture则启用账号，false则禁用账号",required = true,dataType = "Boolean")})
    @PutMapping("/user/account")
    public RespMsg updateAccountStatus(Integer uid,Boolean enabled){
        int i = userService.updateAccountStatus(uid,enabled);
        if(i==1){
            return new RespMsg("true","更新成功");
        }
        return new RespMsg("false","更新失败");
    }

    @ApiOperation("根据用户的id删除用户")
    @ApiImplicitParam(name = "uid",value = "用户id",required = true,dataType = "int")
    @DeleteMapping("/user/delete")
    public RespMsg deleteUserById(Integer uid){
        int i = userService.deleteUserById(uid);
        if(i==1){
            return new RespMsg("true","删除成功");
        }
        return new RespMsg("false","删除失败");
    }

}
