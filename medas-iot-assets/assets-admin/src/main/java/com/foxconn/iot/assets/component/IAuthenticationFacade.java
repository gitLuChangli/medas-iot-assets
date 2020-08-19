package com.foxconn.iot.assets.component;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	
	Authentication getAuthentication();
	
	Long getUserId();
	
	Long getCompanyId();
}
