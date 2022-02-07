package com.ethereal.springboot.controller;


import com.ethereal.springboot.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RequiresRoles("admin")
	@RequiresPermissions("admin")
	@GetMapping("/info/current")
	public ResponseEntity<UserDto> getInfo(){
		return ResponseEntity.ok((UserDto)SecurityUtils.getSubject().getPrincipal());
	}
}
