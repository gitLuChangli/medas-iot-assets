package com.foxconn.iot.assets.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	
	private String tokenHeader;
	
	private String secret;
	
	private String expiration;
	
	private String tokenHead;

	public String getTokenHeader() {
		return tokenHeader;
	}

	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getTokenHead() {
		return tokenHead;
	}

	public void setTokenHead(String tokenHead) {
		this.tokenHead = tokenHead;
	}
}
