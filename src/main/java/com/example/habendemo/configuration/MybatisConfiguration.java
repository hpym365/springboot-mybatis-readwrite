package com.example.habendemo.configuration;

import com.sun.org.apache.bcel.internal.util.ClassLoaderRepository;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-29 01:32
 * @Version: 1.0
 **/
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

//	private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;
	@Resource(name = "slaveDataSource")
	private DataSource slaveDataSource;

	public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
	}

	@Bean(name = "sqlSessionFactoryaa")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		System.out.println("========sqlSessionFactory========");
		return super.sqlSessionFactory(roundRobinDataSouceProxy());
	}

	public AbstractRoutingDataSource roundRobinDataSouceProxy(){
		ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
		Map<Object,Object> targetDataResources = new HashMap<>();
		targetDataResources.put(DbContextHolder.DbType.MASTER,masterDataSource);
		targetDataResources.put(DbContextHolder.DbType.SLAVE,slaveDataSource);
		proxy.setDefaultTargetDataSource(masterDataSource);//默认源
		proxy.setTargetDataSources(targetDataResources);
		proxy.afterPropertiesSet();
		return proxy;
	}
}