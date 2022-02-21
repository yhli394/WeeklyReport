package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.ArticleMapper;
import com.liyuehong.weeklyreport.mapper.RoleMapper;
import com.liyuehong.weeklyreport.mapper.UserMapper;
import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.model.Role;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.utils.RespMsg;
import com.sun.deploy.association.RegisterFailedException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    //配置日志
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 查询所有用户信息用于后台管理
     * @return
     */
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    /**
     * 注册用户
     * @param user
     * @return
     * true表示注册成功
     * false表示注册失败（用户名重复）
     */
    public Boolean addUser(User user) throws RegisterFailedException {
        //查询数据库中是否已经存在相同的用户名
        User user1 = userMapper.loadUserByUsername(user.getUsername());
        if(user1!=null) {
            throw new RegisterFailedException();
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
    /**
     * 登陆时验证用户身份
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
//    @Cacheable(key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        logger.debug("1");
        if(user==null){
            logger.debug("2");
            throw new UsernameNotFoundException("用户名未找到！");
        }
        //查询用户的角色
        List<Role> roles = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        //设置sessionId
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        //user.setSessionId(details.getSessionId());
        return user;
    }

//    @CachePut(key = "#uid")
//    @Caching(evict = @CacheEvict(key = "#uid",beforeInvocation = true),put = @CachePut(key = "#uid"))
    public int updateUserRolesById(int uid,int[] rids) {
        int j = userMapper.deleteUserRolesById(uid);
        int i = userMapper.updateUserRolesById(uid,rids);
        return i;
    }

    public int updateAccountStatus(Integer uid,Boolean enabled) {
        return userMapper.updateAccountStatus(uid, enabled);
    }

    public int deleteUserById(Integer uid) {
        return userMapper.deleteUserById(uid);
    }

    /**
     * 更新用户头像
     * @param tempFileName
     * @param id
     * @return
     */
    public int updateAvatarById(String tempFileName, Integer id) {
        return userMapper.updateAvatarById(tempFileName,id);
    }
}
