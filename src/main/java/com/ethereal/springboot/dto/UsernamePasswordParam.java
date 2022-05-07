package com.ethereal.springboot.dto;

import lombok.Data;

/**
 * 用户对象
 */
@Data
public class UsernamePasswordParam {
    private String username;
    private String password;
}
