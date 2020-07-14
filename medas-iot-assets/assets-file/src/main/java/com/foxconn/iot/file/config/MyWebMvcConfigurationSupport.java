package com.foxconn.iot.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
	
	@Value("${assets.file-path}")
	private String filePath;
	
	@Value("${assets.allowed-origins}")
	private String[] allowdOrigins;
	
	@Value("${assets.resource-handlers}")
	private String[] resourceHandlers;
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		for (String resource : resourceHandlers) {
			String location = "file: " + filePath + resource.replace("*", "");
			registry.addResourceHandler(resource).addResourceLocations(location);
		}
	}
}
