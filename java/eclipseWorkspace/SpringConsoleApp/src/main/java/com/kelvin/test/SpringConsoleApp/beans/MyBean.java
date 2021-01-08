package com.kelvin.test.SpringConsoleApp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kelvin.test.SpringConsoleApp.components.AppComponent;

public class MyBean {
	private String name;
	private MyBean1Interface myBean1Interface;

	public MyBean1Interface getMyBean1Interface() {
		return myBean1Interface;
	}

	//use autowired, no need to put the bean definition in MyBean definition in beans.xml
	@Autowired
	//if two bean of same type found, use qualifier to tell spring which one to use
	@Qualifier("impl2")
	public void setMyBean1Interface(MyBean1Interface myBean1) {
		this.myBean1Interface = myBean1;
	}

	public String getName() {
		return "Hello, " + name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private AppComponent appComponent;

	public AppComponent getAppComponent() {
		return appComponent;
	}

	@Autowired
	//this requires AppComponent should be a bean
	public void setAppComponent(AppComponent appComponent) {
		this.appComponent = appComponent;
	}
}
