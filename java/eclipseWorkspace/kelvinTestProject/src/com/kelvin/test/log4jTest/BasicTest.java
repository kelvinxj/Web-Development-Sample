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
 * create a folder named "Log4JTest", put the jar as following:
 * 
 * lib/kelvinTestProject.jar
 * lib/log4j-1.2.17.jar
 * Log4J.properties
 * 
 * cd to "Log4JTest", run command(Windows):
 * java -cp .;lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * you will see log message written to Customer.log of this folder.
 * 
 * for linux, first change file Log4J.properties to log4j.properties, then run(do not forget the ":" after classpath):
 * java -cp .:lib/*: com.kelvin.test.log4jTest.BasicTest
 * or
 * java -cp lib/*: com.kelvin.test.log4jTest.BasicTest
 * 
 * pass parameter to log4j(change root logger):
 * java -Dhadoop.root.logger="INFO,file1" -cp .;lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * specify configuration file location:
 * java -Dlog4j.configuration=file:Log4J.properties.bak -cp lib/* com.kelvin.test.log4jTest.BasicTest
 * 
 * running in another directory:
 * java -Dlog4j.configuration=file:Log4JTest/Log4J.properties -cp Log4JTest/lib/* com.kelvin.test.log4jTest.BasicTest
 * Note: the Customer.log should be in another directory, not in "Log4JTest" folder.
 */


public class BasicTest {
	public static void main(String[] args) throws Exception{
		//if comment "PropertyConfigurator.configure" and "BasicConfigurator.configure()"
		//use default configuration file. if running in eclipse, log4j.properties is in src folder of this project.
		//, if running in command line, you need to specify log4j.properties location in classpath:
		//e.g.: java -cp .;lib/* com.kelvin.test.log4jTest.BasicTest //log4.properties in current folder, jar in ./lib folder
		
		//Log4j.properties should be in project root folder
		//PropertyConfigurator.configure("Log4j.properties");

		//using basic configuration, will write log to console
		//BasicConfigurator.configure();
		
		Logger logger = Logger.getRootLogger();
		logger.info("info. triggered from main()");
		logger.warn("warning. triggered from main()");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info. triggered from main()");
		thisClassLogger.warn("class logger warning. triggered from main()");
	}

	@Test
	public void test() {
		//use default configuration file. if running in eclipse, log4j.properties is in src folder of this project.
		//Customer.log is in project root folder
		Logger logger = Logger.getRootLogger();
		logger.info("Write info level message");
		logger.warn("Write wanring level message");
		
		Logger thisClassLogger = Logger.getLogger(BasicTest.class);
		thisClassLogger.info("class logger info");
		thisClassLogger.warn("class logger warning");
	}
	
	@Test
	public void testUsingPropertyFile(){
		//Log4j.properties should be in project root folder
		//Customer.log is in project root folder
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
