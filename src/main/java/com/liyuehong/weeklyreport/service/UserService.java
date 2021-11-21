package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.UserMapper;
import com.liyuehong.weeklyreport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yhli3
 * @classname UserService
 * @Date 2021/11/21 16:14
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     * 0表示成功
     * 1表示用户名重复，注册失败
     */
    public int addUser(User user){
        List<User> users = userMapper.loadUserByUsername(user.getUsername());
        //users!=null为true，因为users有地址
        if(users.size()>0){
            return 1;
        }else{
            int insert = userMapper.reg(user);
            return 0;
        }
    }

    /**
     * 登录
     * @param username
     * @return
     *
     */
    public List<User> loadUserByUsername(String username){
        List<User> userList = userMapper.loadUserByUsername(username);
        return userList;
    }

}
