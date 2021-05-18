package com.kelvin.kelvinTestProjectMaven.jmsTest;

import static org.junit.Assert.*;

import javax.jms.JMSException;
import javax.naming.InitialContext;

import org.junit.Test;

public class testJMS {

	@Test
	public void testSendMesssage() throws Exception {
		JMSQueueTest jmsq = new JMSQueueTest();
		String quename = "queneName";
		String messageText = "I send a message!";
		
		try {
			jmsq.init(quename);
			jmsq.sendMessage(messageText);
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		} finally {
		jmsq.close(); }
	}

}
