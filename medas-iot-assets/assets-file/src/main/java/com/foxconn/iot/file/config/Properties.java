package com.foxconn.iot.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "assets")
public class Properties {
	
	/**
	 * 文件存储路径
	 */
	private String filePath;
	
	/**
	 * 允许访问资源路径
	 */
	private String[] resourceHandlers;
	
	/**
	 * 允许跨域访问服务器地址
	 */
	private String[] allowedOrigins;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getResourceHandlers() {
		return resourceHandlers;
	}

	public void setResourceHandlers(String[] resourceHandlers) {
		this.resourceHandlers = resourceHandlers;
	}

	public String[] getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(String[] allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}
}
