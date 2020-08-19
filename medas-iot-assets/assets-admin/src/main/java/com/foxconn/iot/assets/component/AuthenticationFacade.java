package com.foxconn.iot.assets.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.foxconn.iot.assets.bo.AdminUserDetails;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Long getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			AdminUserDetails user = (AdminUserDetails) authentication.getPrincipal();
			if (user != null) {
				return user.getUserId();
			}
		}
		return null;
	}

	@Override
	public Long getCompanyId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			AdminUserDetails user = (AdminUserDetails) authentication.getPrincipal();
			if (user != null) {
				return user.getCompanyId();
			}
		}
		return null;
	}

	@Override
	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			AdminUserDetails user = (AdminUserDetails) authentication.getPrincipal();
			if (user != null) {
				return user.getUsername();
			}
		}
		return null;
	}
}
