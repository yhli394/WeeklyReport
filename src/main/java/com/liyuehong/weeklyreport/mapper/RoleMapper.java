package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Role;

import java.util.List;


public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRolesByUid(Integer uid);
}