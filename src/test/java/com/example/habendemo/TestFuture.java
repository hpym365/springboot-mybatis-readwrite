package com.example.habendemo;

import java.util.concurrent.*;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-15 10:55
 * @Version: 1.0
 **/
public class TestFuture {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<Integer> f = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(10000);
				System.out.println("123123");
				return 123123;
			}
		});

		FutureTask<Integer> f2 = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(10000);
				System.out.println("456456");
				return 456456;
			}
		});
		Future<Integer> f3 = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(10000);
				System.out.println("789789");
				return 789789;
			}
		});

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.execute(f);
//		executorService.

		while (!f.isDone()){
			System.out.println("getint");
		}
		Integer integer = f.get();
		System.out.println("future finished");
	}
}
