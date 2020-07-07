package com.foxconn.iot.assets.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.foxconn.iot.assets.mapper","com.foxconn.iot.assets.dao"})
public class MyBatisConfig {
	
}
