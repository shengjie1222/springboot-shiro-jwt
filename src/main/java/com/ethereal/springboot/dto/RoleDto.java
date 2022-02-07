package com.ethereal.springboot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色对象
 */
@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -9077975168976887742L;

	private String roleName;

	private String roleCode;

    private List<PermissionDto> permissions;


}
