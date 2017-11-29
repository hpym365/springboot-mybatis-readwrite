package com.example.habendemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-26 19:28
 * @Version: 1.0
 **/
public class EscapeAnalysisTest {

	public static void main(String[] args) throws InterruptedException {
		EscapeAnalysisTest e = new EscapeAnalysisTest();

		long now = System.currentTimeMillis();
		for(int i=0;i<999999999;i++){
			Thread.sleep(100);
			e.test();
		}
		System.out.println(System.currentTimeMillis()-now);

		long now1 = System.currentTimeMillis();
		for(int i=0;i<999999999;i++){
			Thread.sleep(100);
			e.test1();
		}
		System.out.println(System.currentTimeMillis()-now1);
	}

	public byte[] test(){
//		byte[] bytes = new byte[1*1024*1024];
		List list = new ArrayList(32);
//		bytes=null;//30sec
		return null;
	}

	public void test1(){
//		byte[] bytes = new byte[1*1024*1024];
//		bytes=null;
		List list = new ArrayList(32);
		list=null;

	}
}
