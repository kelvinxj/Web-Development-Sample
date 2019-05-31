package com.kelvin.test.log4jTest;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestJDKLogging {
	
	private static Logger logger = Logger.getLogger("com.kelvin.test.jdklogging.TestJDKLogging");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.format("First message:%s%n", "Hello,world");
		logger.warning("warning message");
		logger.info("info log message");
		logger.fine("fine log message");
		System.out.println("Java home is:" + System.getProperty("java.home"));
	}
	
	public static Object run() {
		LogManager manager;
        String cname = null;
        try {
            cname = System.getProperty("java.util.logging.manager");
            if (cname != null) {
                try {
                    Class clz = ClassLoader.getSystemClassLoader().loadClass(cname);
                    manager = (LogManager) clz.newInstance();
                } catch (ClassNotFoundException ex) {
                    Class clz = Thread.currentThread().getContextClassLoader().loadClass(cname);
                    manager = (LogManager) clz.newInstance();
                }
            }
        } catch (Exception ex) {
            System.err.println("Could not load Logmanager \"" + cname + "\"");
            ex.printStackTrace();
        }

        return null;
    }

}
