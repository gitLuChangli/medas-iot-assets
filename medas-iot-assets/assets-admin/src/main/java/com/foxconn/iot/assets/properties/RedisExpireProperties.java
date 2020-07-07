package com.foxconn.iot.assets.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.expire")
public class RedisExpireProperties {
	
	private String common;

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
}
