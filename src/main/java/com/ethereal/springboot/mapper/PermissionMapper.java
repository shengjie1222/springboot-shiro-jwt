package com.ethereal.springboot.mapper;

import com.ethereal.springboot.model.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectByRoleId(Integer roleId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}