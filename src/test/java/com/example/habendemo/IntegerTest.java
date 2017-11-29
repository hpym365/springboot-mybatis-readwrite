package com.example.habendemo;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-25 15:08
 * @Version: 1.0
 **/
public class IntegerTest {

	public static void main(String[] args) {
		Integer a = 11;
		Integer b = 11;
		Integer c= new Integer(11);
		Integer d= new Integer(11);
		int zz = 11;

		System.out.println("zz==a"+(a==zz));

		System.out.println(a==b);
		System.out.println(c==d);
		System.out.println(b==c);

		System.out.println();

		String s1 = new String("1");
		s1.intern();

		String s2 = "1";

		System.out.println(s1==s1.intern());
		System.out.println(s1==s2);

		String s3 = new String("2")+new String("2");
		s3.intern();
		String s4 = "22";
		String s5 = new String("22");
		String s6 = "22";
		System.out.println(s3==s4);

		System.out.println(s4==s5);
		System.out.println(s6==s4);


		String ss = "123"+"456";
		String ss2 = "1"+"23456";

		String ss1 = "123456";
		System.out.println(ss==ss1);
		System.out.println(ss1==ss2);
		long free = Runtime.getRuntime().freeMemory()/1024/1024;
		long total = Runtime.getRuntime().totalMemory()/1024/1024;
		long max = Runtime.getRuntime().maxMemory()/1024/1024;
		System.out.println("free:"+free+"  total:"+total+" max:"+max);

//		new IntegerTest().test();

		System.out.println("=========================");

		Integer i1=10;
		Integer i2=10;
		Integer i3=200;
		Integer i11=new Integer(10);
		Integer i22=new Integer(10);
		Integer i33=new Integer(20);
		System.out.println(i1==i2);
		System.out.println(i1==i11);
		System.out.println(i11==i22);
		System.out.println(i3==(i1+i2));
		System.out.println(i3==(i11+i22));
		System.out.println(i33==(i1+i2));
		System.out.println(i33==(i11+i22));
		System.out.println((i3==(1+199))+"  "+((1+199)==i33)+ "   "+(i33==i3));

	}

	int count = 0;

	public void stackOverFlow (){

		count++;
		stackOverFlow();
	}


	public void test(){
		try{
			this.stackOverFlow();
		}catch (Throwable e){
			e.printStackTrace();
			System.out.println("current count:"+count);
			long free = Runtime.getRuntime().freeMemory()/1024/1024;
			long total = Runtime.getRuntime().totalMemory()/1024/1024;
			long max = Runtime.getRuntime().maxMemory()/1024/1024;
			System.out.println("free:"+free+"  total:"+total+" max:"+max);

		}
	}
}
