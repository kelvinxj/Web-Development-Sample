package com.kelvin.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.jms.Queue;

import org.junit.Test;


public class GetWebSphereResource {
	@Test
	public void getJMSObject() throws NamingException{
		Hashtable<String, String>params = new Hashtable<String,String>();
		params.put("org.omg.CORBA.ORBClass", "com.ibm.CORBA.iiop.ORB");
		params.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
		params.put("java.naming.provider.url", "corbaloc::myhost:2809");
		Context ctx = new InitialContext(params);
		
		//InitialContext context = new InitialContext(params);
		Object obj = ctx.lookup("MDM.PUBLISHCONTACT.TEST.SUB_QCF");
		
		//Queue destination = (Queue)obj;
	}
}
