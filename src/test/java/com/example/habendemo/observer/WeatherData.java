package com.example.habendemo.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-19 22:03
 * @Version: 1.0
 **/
public class WeatherData extends Observable {

//	List<ObServerTest.Template> list = new ArrayList<>();
//
//
//	@Override
//	public void register(ObServerTest.Template template) {
//		list.add(template);
//	}
//
//	@Override
//	public void remove(ObServerTest.Template template) {
//		list.remove(template);
//	}
//
//	@Override
//	public void notifyObServer(int wd, int sd, int df) {
//		list.forEach((ob) -> ob.update(wd, sd, df));
//	}
//
	public void change(int wd, int sd, int df) {
		tq.setWd(wd);
		tq.setSd(sd);
		tq.setDf(df);
//		notifyObServer(wd, sd, df);
		this.setChanged();
		this.notifyObservers();
	}

	private Tq tq = new Tq();

	public Tq getTq() {
		return tq;
	}

	public void setTq(Tq tq) {
		this.tq = tq;
	}
}
