package com.example.habendemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-17 01:39
 * @Version: 1.0
 **/
public class ThreadLocalTest {

	static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal3 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal4 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal5 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal6 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal7 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal8 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal9 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal10 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal11 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal12 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal13 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal14 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal15 = new ThreadLocal<>();
	static final ThreadLocal<String> threadLocal16 = new ThreadLocal<>();
	public static void main(String[] args) {
		threadLocal.set("123123");
		threadLocal1.set("123123");
		threadLocal2.set("123123");
		threadLocal3.set("123123");
		threadLocal4.set("123123");
		threadLocal5.set("123123");
		threadLocal6.set("123123");
		threadLocal7.set("123123");
		threadLocal8.set("123123");
		threadLocal9.set("123123");
		threadLocal10.set("123123");
		threadLocal11.set("123123");
		threadLocal12.set("123123");
		threadLocal13.set("123123");
		threadLocal14.set("123123");
		threadLocal15.set("123123");
		threadLocal16.set("123123");
//		threadLocal.remove();
		threadLocal1.set("456456");
		threadLocal.get();


	}

}
