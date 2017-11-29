package com.example.habendemo;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-23 00:11
 * @Version: 1.0
 **/
public class SigtonTest {

	private static SigtonTest sigtonTest;


	private SigtonTest() {

	}

	public static SigtonTest getInstance(String string) throws InterruptedException {
		Thread.sleep(100);

		if (sigtonTest == null) {
			Thread.sleep(100);
			synchronized (SigtonTest.class) {
				Thread.sleep(100);
				if (sigtonTest == null) {
					Thread.sleep(100);
					sigtonTest = new SigtonTest();
					System.out.println(string);
				}

			}
		}
		System.out.println("return");
		return sigtonTest;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		CompletableFuture<SigtonTest> future2 = CompletableFuture.supplyAsync(new Supplier<SigtonTest>() {

			@Override
			public SigtonTest get() {
				SigtonTest instance = null;
				try {
					instance = getInstance("future2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return instance;
			}
		});
		Thread.sleep(10);

		Future future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					getInstance("future");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
//		Future future1 = executorService.submit(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					getInstance("future1");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});



		SigtonTest sigtonTest2 = future2.get();


//
//		SigtonTest sigtonTest = (SigtonTest) future.get();
//		SigtonTest sigtonTest1 = (SigtonTest) future1.get();
//		System.out.println("gggg");
//		executorService.shutdown();
	}

}
