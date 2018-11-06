package com.kelvin.test.jmxTest;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Main {

	public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		// TODO Auto-generated method stub
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("com.kelvin.test.jmxTest:type=Hello");
		
		Hello mbean = new Hello();
		mbs.registerMBean(mbean, name);
		
		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}

}
