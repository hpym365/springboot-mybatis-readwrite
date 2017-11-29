package com.example.habendemo.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-19 21:42
 * @Version: 1.0
 **/
public class ObServerTest {

	public static void main(String[] args) {
		Observer template1 = new Tqa();
		Observer template2 = new Tqb();

		WeatherData weatherData = new WeatherData();
		weatherData.addObserver(template1);
		weatherData.addObserver(template2);

		weatherData.change(1, 2, 3);
		weatherData.change(4, 5, 6);
	}


	interface Template {
		void update(int wd, int sd, int df);
	}

	interface Display {
		void display();
	}


	static class Tqa extends Tq implements Observer, Display {

//		@Override
//		public void update(int wd, int sd, int df) {
//			this.setWd(wd);
//			this.setSd(sd);
//			this.display();
//		}

		@Override
		public void display() {
			System.out.println("模板A的温度:" + this.getWd() + " 湿度:" + this.getSd());
		}

		@Override
		public void update(Observable o, Object arg) {

			WeatherData weatherData = (WeatherData) o;
			Tq tq = weatherData.getTq();
			this.setWd(tq.getWd());
			this.setSd(tq.getSd());
			this.display();
		}
	}

	public static class Tqb extends Tq implements Observer, Display {

//		@Override
//		public void update(int wd, int sd, int df) {
//			this.setWd(wd);
//			this.setDf(df);
//			this.display();
//		}

		@Override
		public void display() {
			System.out.println("模板B的温度:" + this.getWd() + " 风力:" + this.getDf());
		}

		@Override
		public void update(Observable o, Object arg) {
//			this.setWd(wd);
//			this.setDf(df);
			WeatherData weatherData = (WeatherData) o;
			Tq tq = weatherData.getTq();
			this.setWd(tq.getWd());
			this.setDf(tq.getDf());
			this.display();
		}
	}
}
