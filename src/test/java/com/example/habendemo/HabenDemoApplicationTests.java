package com.example.habendemo;

import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.Predicate;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HabenDemoApplicationTests {

	@Test
	public void contextLoads() {

	}


	@Test
	public void test() throws InterruptedException {
		String str = new String("123");
		System.out.println(str);
		SoftReference<String> wf = new SoftReference<String>(str);
//		WeakReference<String> wf = new WeakReference<String>(str);
		System.out.println(wf.get());

		str = null;
		System.gc();

		System.gc();
		System.gc();
		Thread.sleep(1000);
		System.out.println(wf.get());

		System.out.println(str);
	}

	int count = 0;
	ReentrantLock lock = new ReentrantLock();

	@Test
	public void volitileTest() throws InterruptedException {
		Thread t = new Thread() {
			int zxcs = 0;

			@Override
			public void run() {

				while (zxcs < 11111) {
					lock.lock();
					count = count + 1;
					zxcs++;
					lock.unlock();
				}
			}
		};

		Thread t1 = new Thread() {
			int zxcs = 0;

			@Override
			public void run() {
				while (zxcs < 11111) {
					lock.lock();
					count = count + 1;
					zxcs++;
					lock.unlock();

				}
			}
		};
		t.start();
		t1.start();

		Thread.sleep(2000);

		System.out.println("t1:" + count);

	}

	@Test
	public void hashMap() {
		HashMap<String, Object> map = Maps.newHashMap();
	}


	@Test
	public void completionFutureTest() {

		long now = System.currentTimeMillis();
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> sleep(1000));
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> sleep(1000));
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> sleep(1000));

		try {
			System.out.println(future1.get() + future2.get() + future3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println(System.currentTimeMillis() - now);


	}

	public String sleep(long sleep) {

		double random = Math.random();

		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Math.random() + "";
	}


	@Test
	public void lambdaTest() throws InterruptedException {

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 999999; i++) {
			list.add(i);
		}
		Thread.sleep(5000);
		System.out.println("初始化完成");
		long now1 = System.currentTimeMillis();
//		list.stream().forEach(item -> {
//			try {
////				List<Integer> aaa = new ArrayList<>();
//////				System.out.println(Thread.currentThread().getThreadGroup().getName());
////				for(int i=0;i<9999;i++){
////					aaa.add(i);
////				}
////				Thread.sleep(1);
//				int c = aaa.size();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
		Double res1 = list.stream().map(i -> i * Math.random() / Math.random() * Math.random()).reduce(Double.NaN, Double::sum);
//
		System.out.println("Stream流:" + (System.currentTimeMillis() - now1));


		long now2 = System.currentTimeMillis();
		Double res2 = Double.NaN;
		for (Integer integer : list) {
			Double c = integer * Math.random() / Math.random() * Math.random();
			res2 = res2 + c;
//			List<Integer> aaa = new ArrayList<>();
//
//			for(int i=0;i<9999;i++){
//				aaa.add(i);
//			}
//			Thread.sleep(1);
//			int c = aaa.size();
		}
		System.out.println("for (Integer integer : list):" + (System.currentTimeMillis() - now2));

		long now3 = System.currentTimeMillis();
		Double res3 = Double.NaN;

		for (int i = 0; i < list.size(); i++) {
			Integer integer = list.get(i);
			Double c = integer * Math.random() / Math.random() * Math.random();
			res3 = res3 + c;
//			List<Integer> aaa = new ArrayList<>();
//
//			for(int i=0;i<9999;i++){
//				aaa.add(i);
//			}
//			Thread.sleep(1);
//			int c = aaa.size();
		}
		System.out.println("for (int i=0;i<list.size();i++):" + (System.currentTimeMillis() - now3));

//		System.out.println(res1+"   "+res2);
//		List<String> collect1 = list.stream().forEach(iteam -> System.out.println(iteam)).collect(toList());


//		System.out.println(collect1);


	}


	@Test
	public void streamTest() throws InterruptedException {
		ConcurrentHashMap c = new ConcurrentHashMap();
		c.put("123", "456");
		c.get("123");

		Thread.sleep(8000000);
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 8, 0, 1)
				.stream()
				.parallel()
				.collect(Collectors.groupingBy(x -> x % 10))
				.forEach((x, y) -> System.out.println(x + ":" + y));
	}


	@Test
	public void treeMapTest() {
		TreeMap t = new TreeMap();
		t.put(123, "456");
		t.put(222, "345");
		t.put(1, "2222");

		System.out.println(t);
	}




	public static void main(String[] args) throws InterruptedException {
		HttpGet request = new HttpGet("http://www.163.com");

		ioThread io = new ioThread(request);

		io.setUncaughtExceptionHandler((thread, throwable) -> {
			throwable.printStackTrace();
		});
		Thread p = new Thread() {
			@Override
			public void run() {
				super.run();
				while (true) {
					System.out.println("print....");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};


		io.start();
		Thread.sleep(10);
//
		p.setDaemon(true);
		p.start();
	}

	 static class ioThread extends Thread {
		HttpGet request;
		HttpClient httpClient = new DefaultHttpClient();
		public ioThread(HttpGet request) {
			this.request = request;
		}

		@Override
		public void run() {
			System.out.println("io begin");


			String result = "";

			HttpResponse response = null;
			try {
				response = httpClient.execute(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("request");
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					result = EntityUtils.toString(response.getEntity(), "utf-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(result);
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		}
	}
}
