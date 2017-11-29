package com.example.habendemo.interceptor;

import com.example.habendemo.anon.ReadOnlyConnection;
import com.example.habendemo.configuration.DbContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-29 01:40
 * @Version: 1.0
 **/
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	public static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);

	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
		try {
//			logger.info("set database connection to read only");
			DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
			Object result = proceedingJoinPoint.proceed();
			return result;
		}finally {
			DbContextHolder.clearDbType();
//			logger.info("restore database connection");
		}
	}


	@Override
	public int getOrder() {
		return 0;
	}
}