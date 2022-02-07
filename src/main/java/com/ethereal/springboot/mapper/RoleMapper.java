package com.ethereal.springboot.mapper;

import com.ethereal.springboot.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectByUsername(String username);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}