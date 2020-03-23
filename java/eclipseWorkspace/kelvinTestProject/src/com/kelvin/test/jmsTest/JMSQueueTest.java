package com.kelvin.test.jmsTest;

import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.TextMessage;

public class JMSQueueTest {
	public JMSQueueTest() {
		super(); 
	}
	private Context ctx;
	private InitialContext initialContext;
	private QueueConnectionFactory queueCF;
	private QueueConnection queueConn;
	private QueueSession queueSession;
	private Queue queue;
	private QueueSender queueSender;
	private final static String JNDI_FACTORY = "com.ibm.websphere.naming.WsnInitialContextFactory";
	private final static String JMS_FACTORY = "com/ibm/idm/mdm/messaging/IDMPublishContact_TEST_QCF";
	private final static String QUEUE = "MDB_QUEUE";
	private final static String URL = "corbaloc::localhost:2809";
	private TextMessage txtMessage;
	private static String USER = "wasadmin";
	private static String PASSWORD = "Lihui2020";
	private static String messageText = "Hello!";
	
	public InitialContext getInitialContext(String url) throws Exception 
	{
		Hashtable envHash = new Hashtable();
		envHash.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		envHash.put(Context.PROVIDER_URL, url);
		envHash.put(Context.SECURITY_PRINCIPAL, USER);
		envHash.put(Context.SECURITY_CREDENTIALS, PASSWORD);
		try {
		return new InitialContext(envHash);
		} catch (NamingException e) {
		e.printStackTrace(); }
		return new InitialContext(envHash); 
	}
	
	public void init(String queueName) 
	{
		try {
		Context ctx = getInitialContext(URL);
		queueCF = (QueueConnectionFactory)ctx.lookup(JMS_FACTORY);
		queueConn = queueCF.createQueueConnection();
		queueSession = queueConn.createQueueSession(false,Session.SESSION_TRANSACTED);
		queue = (Queue)ctx.lookup(queueName);
		queueSender = queueSession.createSender(queue);
		txtMessage = queueSession.createTextMessage();
		queueConn.start();
		} catch (Exception e) {
		e.printStackTrace(); } 
	}
	
	public void close() throws JMSException 
	{
		queueSender.close();
		queueSession.close();
		queueConn.close(); 
	}
	
	public void sendMessage(String message) throws JMSException 
	{
		txtMessage.setText(messageText);
		queueSender.send(txtMessage); 
	}
//public static void main(String[] args) throws Exception {
//	JMSQueueTest jmsq = new JMSQueueTest();
//	InitialContext ico = jmsq.getInitialContext(URL);
//	try {
//		jmsq.init(ico, QUEUE);
//		jmsq.sendMessage(messageText);
//	} catch (JMSException jmse) {
//		jmse.printStackTrace();
//	} finally {
//	jmsq.close(); }
//}
}
