package com.kelvin.test.log4jTest;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Test;

public class BasicTest {

	@Test
	public void test() {
		//if no log4j setting, will write log to console
		Logger logger = Logger.getRootLogger();
		logger.info("Write info level message");
		logger.warn("Write wanring level message");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info");
		thisClassLogger.warn("class logger warning");
	}
	
	@Test
	public void testUsingPropertyFile(){
		PropertyConfigurator.configure("Log4j.properties");
		//if no log4j setting, will write log to console
		Logger logger = Logger.getRootLogger();
		logger.info("Write info level message");
		logger.warn("Write wanring level message");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info");
		thisClassLogger.warn("class logger warning");
	}
	
	@Test
	public void testOverwiteProperty(){
		//if you need to set property, set it before load configuration file
		System.setProperty("hadoop.root.logger", "INFO,file1");
		PropertyConfigurator.configure("Log4j.properties");
		//if no log4j setting, will write log to console
		Logger logger = Logger.getRootLogger();
		logger.info("Write info level message");
		logger.warn("Write wanring level message");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info");
		thisClassLogger.warn("class logger warning");
	}

}
