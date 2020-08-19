package com.foxconn.iot.assets.bo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsResource;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String username;
	
	private String password;
	
	private Long companyId;
	
	private Integer status;
	
	private List<UmsResource> resourceList;

	public AdminUserDetails(UmsAdmin umsAdmin, List<UmsResource> resourceList) {
		this.userId = umsAdmin.getId();
		this.username = umsAdmin.getUsername();
		this.password = umsAdmin.getPassword();
		this.companyId = umsAdmin.getCompanyId();
		this.status = umsAdmin.getStatus();
		this.resourceList = resourceList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 返回当前用户的角色
		return resourceList.stream().map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status.equals(1);
	}
	
	public Long getUserId() {
		return userId;
	}

	public Long getCompanyId() {
		return this.companyId;
	}
}
