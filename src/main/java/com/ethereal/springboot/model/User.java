package com.ethereal.springboot.model;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private Integer roleId;

}