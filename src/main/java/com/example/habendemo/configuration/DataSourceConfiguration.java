package com.example.habendemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-29 01:30
 * @Version: 1.0
 **/
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;

	@Bean(name = "masterDataSource")
	@Primary
	@ConfigurationProperties(prefix = "druid.master")
	public DataSource masterDataSource(){
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean(name = "slaveDataSource")
	@ConfigurationProperties(prefix = "druid.slave")
	public DataSource slaveDataSource1(){
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
}
