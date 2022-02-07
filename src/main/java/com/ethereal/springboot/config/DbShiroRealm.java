package com.ethereal.springboot.config;

import java.util.List;

import com.ethereal.springboot.dto.PermissionDto;
import com.ethereal.springboot.dto.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.ethereal.springboot.dto.UserDto;
import com.ethereal.springboot.service.UserService;
import org.springframework.util.CollectionUtils;


@Slf4j
public class DbShiroRealm extends AuthorizingRealm {

	private UserService userService;
	
	public DbShiroRealm(UserService userService) {
		this.userService = userService;
		this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
	}
	
	@Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userPasswordToken = (UsernamePasswordToken)token;
		String username = userPasswordToken.getUsername();
		UserDto user = userService.getUserInfo(username);
		if(user == null)
			throw new AuthenticationException("用户名或者密码错误");
		return new SimpleAuthenticationInfo(user, user.getEncryptPwd(), ByteSource.Util.bytes(user.getSalt()), "dbRealm");
	}


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {      
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserDto user = (UserDto) principals.getPrimaryPrincipal();
		List<RoleDto> roles = user.getRoles();
		if (!CollectionUtils.isEmpty(roles))
		for (RoleDto role : roles) {
			//添加角色
			simpleAuthorizationInfo.addRole(role.getRoleCode());
			//添加权限
			for (PermissionDto permissions : role.getPermissions()) {
				simpleAuthorizationInfo.addStringPermission(permissions.getPerCode());
			}
		}
		return simpleAuthorizationInfo;
	}

	
}
