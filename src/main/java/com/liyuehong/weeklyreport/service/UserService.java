package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.RoleMapper;
import com.liyuehong.weeklyreport.mapper.UserMapper;
import com.liyuehong.weeklyreport.model.Role;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yhli3
 * @classname UserService
 * @Date 2021/11/21 16:14
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    /**
     * 注册
     * @param user
     * @return
     * 0表示成功
     * 1表示用户名重复，注册失败
     */
    public int addUser(User user){
        User users = userMapper.loadUserByUsername(user.getUsername());
        if(users!=null){
            return 1;
        }else{
            int insert = userMapper.reg(user);
            return 0;
        }
    }

    /**
     * 登陆时验证用户身份
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            //User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userMapper.loadUserByUsername(username);
            return user;
        }catch (UsernameNotFoundException e){
            e.getMessage();
        }
        return new User();
    }

    //public User loadUserByUsername(String username){
    //    User user = userMapper.loadUserByUsername(username);
    //    if(user==null){
    //        return new User();
    //    }
    //    return user;
    //}

}
