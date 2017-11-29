package com.example.habendemo;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-13 20:56
 * @Version: 1.0
 **/
public class TestInterrupt implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new TestInterrupt());

		t.start();
		Thread.sleep(50);
		t.interrupt();
		t.join();
	}

	@Override
	public void run() {

		while (true){
			if(Thread.currentThread().isInterrupted()){
				System.out.println("thread terminated");
				break;
			}else {
				try {
					System.out.println("睡觉100");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException");
//					e.printStackTrace();
					Thread.interrupted();
					Thread.currentThread().interrupted();
					Thread.currentThread().interrupt();
				}
			}
		}

	}
}
