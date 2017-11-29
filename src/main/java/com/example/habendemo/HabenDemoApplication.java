package com.example.habendemo;

import com.example.habendemo.anon.ReadOnlyConnection;
import com.example.habendemo.mapper.TestMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.CacheBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@Controller
@MapperScan(basePackages = "com.example.habendemo.mapper",sqlSessionFactoryRef = "sqlSessionFactoryaa")
public class HabenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabenDemoApplication.class, args);
	}


	@RequestMapping("/cache")
	public ResponseEntity<String> cache(
			//为了方便测试，此处传入文档最后修改时间
//			@RequestParam("millis") long lastModifiedMillis,
			//浏览器验证文档内容是否修改时传入的Last-Modified
			@RequestHeader(value = "If-Modified-Since", required = false) Date ifModifiedSince) {
		//当前系统时间
		long now = System.currentTimeMillis();
		//文档可以在浏览器端/proxy上缓存多久
		long maxAge = 20;
		//判断内容是否修改了，此处使用等值判断
		if (ifModifiedSince != null ) {
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		}
		DateFormat gmtDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		String body = "<a href=''>点击访问当前链接</a>";
		MultiValueMap<String, String> headers = new HttpHeaders();
		//文档修改时间
		headers.add("Last-Modified", gmtDateFormat.format(new Date(now + maxAge)));
		//当前系统时间
		headers.add("Date", gmtDateFormat.format(new Date(now)));
		//过期时间http 1.0支持
		headers.add("Expires", gmtDateFormat.format(new Date(now + maxAge)));
		//文档生存时间http 1.1支持
		headers.add("Cache-Control", "max-age=" + maxAge);
		return new ResponseEntity<String>(body, headers, HttpStatus.OK);
	}


	@RequestMapping("/cachea")
	public ResponseEntity<String> cachea(HttpServletRequest request,
										 //为了方便测试，此处传入文档最后修改时间
										 @RequestParam("millis") long lastModifiedMillis,
										 //浏览器验证文档内容是否修改时传入的Last-Modified
										 @RequestHeader(value = "If-Modified-Since", required = false) Date ifModifiedSince) {

		DateFormat gmtDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
//		long lastModify = get

//		Iterator<String> headerNames1 = webRequest.getHeaderNames();
//		while (headerNames1.hasNext()) {
//			System.out.println(headerNames1.next());
//		}
//
//		Enumeration<String> headerNames = request.getHeaderNames();
//
//		while (headerNames.hasMoreElements()) {
//			String s = headerNames.nextElement();
//			System.out.println(s + ":" + request.getHeader(s));
//
//		}

		long now = System.currentTimeMillis() / 1000 * 1000;

		long maxAge = 50;

		if (ifModifiedSince != null) {
			System.out.println("时间不空");
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		} else {
			System.out.println("时间是空的啊啊啊");
		}

		String body = "<a href=''>点击当前</a>";
//		MultiValueMap headers = new HttpHeaders();
//		headers.add("Date", gmtDateFormat.format(new Date(now)));
//		headers.add("expires", gmtDateFormat.format(new Date(now + 50 * 1000)));
//		headers.add("Last-Modified", gmtDateFormat.format(new Date(now + 50 * 1000)));

		MultiValueMap<String, String> headers = new HttpHeaders();
		//文档修改时间
		headers.add("Last-Modified", gmtDateFormat.format(new Date(now + maxAge)));
		//当前系统时间
		headers.add("Date", gmtDateFormat.format(new Date(now)));
		//过期时间http 1.0支持
		headers.add("Expires", gmtDateFormat.format(new Date(now + maxAge)));
		//文档生存时间http 1.1支持
		headers.add("Cache-Control", "max-age=" + maxAge);
		return new ResponseEntity<String>(body, headers, HttpStatus.OK);

//		return ResponseEntity.ok()
//				.cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
//				.body(manyUsers);

	}

	ReentrantLock reentrantLock = new ReentrantLock();

	@RequestMapping("/bf")
	@ResponseBody
	public String bf() throws InterruptedException {
//		reentrantLock.lock();
		byte [] bytes = new byte[1*512*512];
		Thread.sleep(100);
//		reentrantLock.unlock();
		return "123";
	}

	@Autowired
	TestMapper testMapper;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@RequestMapping("/gc")
	@ResponseBody
	@ReadOnlyConnection
	public Object gc(){
		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		garbageCollectorMXBeans.forEach((garbageCollectorMXBean)-> System.out.println(garbageCollectorMXBean.getName()));
		return null;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object add(){
		Random random = new Random();
		int i = random.nextInt(10)+20;
		TestEntity testEntity = new TestEntity();
		testEntity.setName(i+"name");
		testEntity.setAge(i);
		testMapper.add(testEntity);
//		Connection connection = sqlSessionFactory.openSession().getConnection();
//		System.out.println(one);
//		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
//		garbageCollectorMXBeans.forEach((garbageCollectorMXBean)-> System.out.println(garbageCollectorMXBean.getName()));
		return i;
	}

	@RequestMapping("/query")
	@ResponseBody
	@ReadOnlyConnection
	public Object query(){
		Random random = new Random();
		int i = random.nextInt(6)+1;
		TestEntity one = testMapper.getOne(i);
		return one;
	}
}
