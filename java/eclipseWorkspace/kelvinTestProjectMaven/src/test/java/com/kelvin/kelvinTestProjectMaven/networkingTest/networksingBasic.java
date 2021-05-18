package com.kelvin.kelvinTestProjectMaven.networkingTest;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Test;

public class networksingBasic {

	@Test
	public void test() throws UnknownHostException {
		//get IP address of local machine
		InetAddress addr = InetAddress.getByName(null);
		System.out.println(addr);
		
		InetAddress[] addrArray = InetAddress.getAllByName(null);
		for(InetAddress a: addrArray){
			System.out.println(a);
		}
		
		//get IP address of a web site
		addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr);
	}

}
