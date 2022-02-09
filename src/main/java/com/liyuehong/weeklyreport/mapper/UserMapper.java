package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteUserRolesById(Integer id);

    /**
     * 注册
     * @param user
     * @return
     */
    int reg(User user);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用于登陆时验证用户
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    List<User> selectAllUsers();

    int updateUserRolesById(@Param("uid") int uid, @Param("rids") int[] rids);

    int updateAccountStatus(@Param("uid") Integer uid, @Param("enabled") Boolean enabled);

    int deleteUserById(Integer uid);

}