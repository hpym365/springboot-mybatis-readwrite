package com.example.habendemo;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-13 22:49
 * @Version: 1.0
 **/
public class TestVolatile {
	public static boolean flag=true;
	public static void main(String[] args) throws InterruptedException {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (flag){
					System.out.println("print");
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		Thread.sleep(2000);
		flag=false;
	}
}
