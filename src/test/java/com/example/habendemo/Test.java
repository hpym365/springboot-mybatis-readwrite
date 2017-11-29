package com.example.habendemo;


import java.util.Arrays;
import java.util.List;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-09 13:09
 * @Version: 1.0
 **/
public class Test {

	public final int num;

	public static enum Msg{
		A,B,C
	}



	public Test(int num) {
		System.out.println("init");

		if(Msg.A==Msg.A){

		}
		this.num = num;
	}


	public static void main(String[] args) throws InstantiationException {

		Class t = Test.class;



		long now = System.currentTimeMillis();

		Integer total1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 6, 7, 9, 6, 7, 9, 6, 7, 9, 1).stream()
				.map(item -> item * 2).reduce(0, Test::test);

		System.out.println("stream:"+(System.currentTimeMillis() - now)+" res:"+total1);



		long now1 = System.currentTimeMillis();

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 6, 7, 9, 6, 7, 9, 6, 7, 9, 1);
		int total2 = 0;
		for (Integer i : list) {
			i = i * 2;
			total2 = test(total2,i);
		}


		System.out.println("for (Integer i : list):"+(System.currentTimeMillis() - now1)+" res:"+total2);

	}

	public static Integer test(int a, int b) {

		System.out.println(Thread.currentThread().getName());

		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a + b;
	}
}
