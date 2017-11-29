package com.example.habendemo.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-29 01:35
 * @Version: 1.0
 **/
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}
}