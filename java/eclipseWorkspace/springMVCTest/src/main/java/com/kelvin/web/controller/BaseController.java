package com.kelvin.web.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseController implements ApplicationContextAware{	
	private ApplicationContext appContext = null;

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.setAppContext(applicationContext);
	}
}
