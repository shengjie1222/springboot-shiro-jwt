package com.ethereal.springboot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ethereal.springboot.config.JwtUtils;
import com.ethereal.springboot.dto.PermissionDto;
import com.ethereal.springboot.dto.RoleDto;
import com.ethereal.springboot.dto.UserDto;
import com.ethereal.springboot.mapper.PermissionMapper;
import com.ethereal.springboot.mapper.RoleMapper;
import com.ethereal.springboot.mapper.UserMapper;
import com.ethereal.springboot.model.Permission;
import com.ethereal.springboot.model.Role;
import com.ethereal.springboot.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户信息接口
 */
@Service
public class UserService {


	@Value("${token.refresh.interval}")
	private int tokenRefreshInterval = 300;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private PermissionMapper permissionMapper;

    /**
     * 保存user登录信息，返回token
     * @param username
     */
    public String generateJwtToken(String username) {
    	String salt = JwtUtils.generateSalt();
		User user = userMapper.selectByUsername(username);
		user.setSalt(salt);
		userMapper.updateByPrimaryKeySelective(user);
    	return JwtUtils.sign(username, salt, tokenRefreshInterval); //生成jwt token，设置过期时间为1小时
    }
    
    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    public UserDto getJwtTokenInfo(String username) {
    	return getUserInfo(username);
    }

    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     * @param userName
     * @return
     */
    public UserDto getUserInfo(String userName) {
		User user = userMapper.selectByUsername(userName);
		String salt = user.getSalt();
    	UserDto userDto = new UserDto();
		userDto.setUserId(user.getId());
		userDto.setUsername(userName);
		userDto.setEncryptPwd(new Sha256Hash(user.getPassword(), salt).toHex());
		userDto.setSalt(salt);

		List<Role> roles = roleMapper.selectByUsername(userName);
		List<RoleDto> roleDtos = Optional.ofNullable(roles).map(list -> list.parallelStream().map(item -> {
			RoleDto roleDto = new RoleDto();
			roleDto.setRoleName(item.getName());
			roleDto.setRoleCode(item.getCode());
			List<Permission> permissions = permissionMapper.selectByRoleId(item.getId());
			List<PermissionDto> permissionDtos = Optional.ofNullable(permissions).map(pers -> pers.stream().map(per -> {
				PermissionDto permission = new PermissionDto();
				permission.setPerCode(per.getCode());
				permission.setPerName(per.getName());
				return permission;
			}).collect(Collectors.toList())).orElse(Collections.emptyList());
			roleDto.setPermissions(permissionDtos);
			return roleDto;
		}).collect(Collectors.toList())).orElse(Collections.emptyList());
		userDto.setRoles(roleDtos);
    	return userDto;
    }

}
