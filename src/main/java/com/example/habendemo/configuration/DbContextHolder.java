package com.example.habendemo.configuration;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-29 01:34
 * @Version: 1.0
 **/
public class DbContextHolder {

	public enum DbType {
		MASTER, SLAVE
	}

	private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

	public static void setDbType(DbType dbType) {
		if (dbType == null) throw new NullPointerException();
		contextHolder.set(dbType);
	}

	public static DbType getDbType() {
		return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}