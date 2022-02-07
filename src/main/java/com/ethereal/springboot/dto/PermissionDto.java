package com.ethereal.springboot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限对象
 */
@Data
public class PermissionDto implements Serializable {
    private static final long serialVersionUID = -9077975168976887742L;

	private String perName;

    private String perCode;

}
