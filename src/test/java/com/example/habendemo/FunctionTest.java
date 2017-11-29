package com.example.habendemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-19 12:59
 * @Version: 1.0
 **/
public class FunctionTest {
	@FunctionalInterface
	interface Uf{
		User create(int id);
	}

//	static Uf u = User::new;


	public static void main(String[] args) {


		String[] array = new String[] {"zhu", "wen", "tao"};
		// String数组转List集合
		List<String> mlist = Arrays.asList(array);
		// 输出List集合
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println("mlist-->" + mlist.get(i));
		}

		mlist.forEach((a) -> System.out.println(a));

//		Arrays.s
	}

	public class User{
		private int id;
		public User(int id){
			this.id=id;
		}

	}


}
