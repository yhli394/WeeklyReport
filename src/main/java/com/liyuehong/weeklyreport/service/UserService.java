package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.RoleMapper;
import com.liyuehong.weeklyreport.mapper.UserMapper;
import com.liyuehong.weeklyreport.model.Role;
import com.liyuehong.weeklyreport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 登录
     * @param username
     * @return
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userMapper.loadUserByUsername(username);
        if(users==null){
            throw new UsernameNotFoundException("用户名未找到");
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = roleMapper.getRolesByUid(users.getId());
        users.setRoles(roles);
        return users;
    }
}
