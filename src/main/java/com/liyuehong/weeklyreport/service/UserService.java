package com.liyuehong.weeklyreport.service;

import com.google.common.collect.ImmutableMap;
import com.liyuehong.weeklyreport.configuration.CustomException;
import com.liyuehong.weeklyreport.mapper.RoleMapper;
import com.liyuehong.weeklyreport.mapper.UserMapper;
import com.liyuehong.weeklyreport.model.Role;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.utils.ErrorInfoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhli3
 * @classname UserService
 * @Date 2021/11/21 16:14
 */
@Service
//@CacheConfig(cacheNames = "user")
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 配置日志
     */
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * @description 查询所有用户信息
     * @author yhli3 
     * @param []
     * @updateTime 2022/2/26 14:50
     * @return java.util.List<com.liyuehong.weeklyreport.model.User>
     */
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    public Boolean addUser(User user) throws CustomException {
        //查询数据库中是否已经存在相同的用户名
        User user1 = userMapper.loadUserByUsername(user.getUsername());
        if(user1!=null) {
            throw new CustomException(ErrorInfoEnum.DUPLICATE_USERNAME, ImmutableMap.of("user:",user1.getUsername()));
//            return false;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //添加注册时间
        user.setRegTime(timestamp);
        //给密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //注册用户
        int reg = userMapper.reg(user);
        //默认注册的用户角色都为普通用户，即user
        int i = roleMapper.insertRole(2, user.getId());
        return true;
    }
    // TODO: 2021/12/30 登录认证(session或者token)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名未找到！");
        }
        //查询用户的角色
        List<Role> roles = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }


    public int updateUserRolesById(int uid,int[] rids) {
        int j = userMapper.deleteUserRolesById(uid);
        int i = userMapper.updateUserRolesById(uid,rids);
        return i;
    }


    /**
     * @description
     * @author yhli3 
     * @param [uid, enabled]
     * @updateTime 2022/2/26 14:50
     * @return int
     */
    public int updateAccountStatus(Integer uid,Boolean enabled) {
        return userMapper.updateAccountStatus(uid, enabled);
    }
    
    public int deleteUserById(Integer uid) {
        return userMapper.deleteUserById(uid);
    }
    
    /**
     * @description 
     * @author yhli3 
     * @param [tempFileName, id]
     * @updateTime 2022/2/26 14:48
     * @return int       
     */
    public int updateAvatarById(String tempFileName, Integer id) {
        return userMapper.updateAvatarById(tempFileName,id);
    }

}
