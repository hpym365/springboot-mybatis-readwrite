package com.example.habendemo;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-28 16:42
 * @Version: 1.0
 **/
public class StringToFloatTest {
	public static void main(String[] args) {
		String str = "34.53";

		char[] c = str.toCharArray();

		for (char c1 : c) {
			System.out.println(c1);
		}

		float f = (float) c[0];

		char ca = c[0];
		System.out.println(ca);


		System.out.println((int)ca);


		char cc = 34;

		System.out.println((float)cc);
	}
}
