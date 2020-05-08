package com.kelvin.test.log4jTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

/*
 * run this porgram in console:
 * export project kelvinTestProject as jar file(no need to be runnable jar)
 * create a new folder, put the jar as following:
 * 
 * lib/kelvinTestProject.jar
 * lib/log4j-1.2.17.jar
 * Log4J.properties
 * 
 * cd to this folder, run command(Windows):
 * java -cp lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * you will see log message written to Customer.log
 * 
 * for linux, first change file Log4J.properties to Log4j.properties, then run(do not forget the ":" after classpath):
 * java -cp lib/*: com.kelvin.test.log4jTest.BasicTest
 * 
 * pass parameter to log4j(change root logger):
 * java -Dhadoop.root.logger="INFO,file1" -cp lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * specify configuration file location:
 * java -Dlog4j.configuration=file:Log4J.properties.bak -cp lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * improvement: commands above only works when in current folder. if you are in another folder, still not work.
 * 
 */


public class BasicTest {
	public static void main(String[] args) throws Exception{
		PropertyConfigurator.configure("Log4j.properties");
		//if no log4j setting, will write log to console
		Logger logger = Logger.getRootLogger();
		logger.info("info. triggered from main()");
		logger.warn("warning. triggered from main()");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info. triggered from main()");
		thisClassLogger.warn("class logger warning. triggered from main()");
	}

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
