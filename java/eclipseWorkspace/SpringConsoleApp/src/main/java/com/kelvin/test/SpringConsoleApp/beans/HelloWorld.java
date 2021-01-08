package com.kelvin.test.SpringConsoleApp.beans;

import org.springframework.stereotype.Component;

public class HelloWorld {
	private String message;

	public String getMessage() {
		return "Your message:" + message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void start() {
		System.out.println("start Hello World bean");
	}
	
	public void cleanUp() {
		System.out.println("destroy Hello world bean");
	}
	
}
