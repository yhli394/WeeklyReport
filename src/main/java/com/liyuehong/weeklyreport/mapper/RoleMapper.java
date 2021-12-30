package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleMapper {

    int insertRole(@Param("rid") Integer rid, @Param("uid") Integer uid);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRolesByUid(Integer uid);
}