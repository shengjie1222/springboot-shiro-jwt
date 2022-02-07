package com.ethereal.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Void> unauthorizedHandler(AuthorizationException e) {
        log.warn("没有通过权限验证！{}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}