package com.ibm.mdm.testtool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.dwl.base.exception.DWLResponseException;
import com.dwl.base.requestHandler.beans.DWLServiceController;
import com.dwl.base.requestHandler.beans.DWLServiceControllerHome;

public class KafkaProcessor {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
    	Properties props = new Properties();
//    	String kafkaIP = "192.168.3.29:9092";
//    	//kafkaIP = "169.254.202.116:9092";
//    	String requestTopicName = "mdmrequest";
//    	String responseTopicName = "mdmresponse";
//    	String mdmServerUrl = "corbaloc::127.0.0.1:2809";
    	
    	KafkaProcessorArgs kafkaArgs = checkArgs();
    	
    	if(kafkaArgs == null){
    		System.out.println("Insufficient input argument. Kafka server exit.");
    		System.exit(1);
    	}
    	
    	printArgs(kafkaArgs);
    	
        props.put("bootstrap.servers", kafkaArgs.getKafkaIP());
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(kafkaArgs.getRequestTopicName()));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        
        int readTimes = 10;
        int i = 0;
        String response = "";
        String message = "";
        
        KafkaProducer<String, String> producer = CreateProducer(kafkaArgs.getKafkaIP());
        
        try {
			DWLServiceController controller = createMDMController(kafkaArgs.getMdmServerUrl());

	        while (true) {
	            ConsumerRecords<String, String> records = consumer.poll(100);
	            for (ConsumerRecord<String, String> record : records) {
	                //buffer.add(record);
	            	//print first 10 characters of message:
	            	message = record.value();
	            	response = processMessage(message, producer, kafkaArgs.getResponseTopicName(), controller);
	            	System.out.println("offset :" + record.offset() + "; "+ record.key() + ":" + message.substring(0,10) + ", " + response);
	            }
	            consumer.commitSync();
//	            if (buffer.size() >= minBatchSize) {
//	                insertIntoDb(buffer);
//	                consumer.commitSync();
//	                buffer.clear();
//	            }
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private static void printArgs(KafkaProcessorArgs kafkaArgs) {
		System.out.println("Kafka processor started...");
    	System.out.println("Kafkaf server: " + kafkaArgs.getKafkaIP());
    	System.out.println("MDM server: " + kafkaArgs.getMdmServerUrl());
    	System.out.println("request topic: " + kafkaArgs.getRequestTopicName());
    	System.out.println("response topic: " + kafkaArgs.getResponseTopicName());
	}

	private static KafkaProcessorArgs checkArgs() {
		String propFileName = System.getProperty("propertyFile");
		System.out.println("curent properties file path: " + propFileName);
		if(propFileName == null){
			System.out.println("property file not provided.");
			return null;
		}

		Properties properties = new Properties();
		FileInputStream fs = null;
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		try {
			fs = new FileInputStream(propFileName);
			isr = new InputStreamReader(fs,"UTF-8");
			bufferedReader = new BufferedReader(new FileReader(propFileName));
			properties.load(bufferedReader);
			
			String kafkaIP = properties.getProperty("kafkaServerUrl");
	    	//kafkaIP = "169.254.202.116:9092";
	    	String requestTopicName = properties.getProperty("requestTopicName");
	    	String responseTopicName = properties.getProperty("responseTopicName");
	    	String mdmServerUrl = properties.getProperty("mdmServerUrl");
	    	
	    	if(kafkaIP == null){
	    		System.out.println("Please provide kafka server url:");
	    		return null;
	    	}
	    	else if(mdmServerUrl == null){
	    		System.out.println("Please provide mdm server url:");
	    		return null;
	    	}
	    	else if(requestTopicName == null){
	    		System.out.println("Please provide requestTopicName:");
	    		return null;
	    	}
	    	else if(responseTopicName == null){
	    		System.out.println("Please provide responseTopicName");
	    		return null;
	    	}
	    	else{
	    		KafkaProcessorArgs args = new KafkaProcessorArgs(kafkaIP, requestTopicName, responseTopicName, mdmServerUrl);
	    		return args;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static DWLServiceController createMDMController(String serverUrl) throws NamingException, RemoteException, CreateException {
		Hashtable<String, String> params = new Hashtable<>();
		params.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
		params.put("java.naming.provider.url", serverUrl);
		InitialContext initCtx = new InitialContext(params);
		Object object = initCtx.lookup( "com/dwl/base/requestHandler/beans/DWLServiceController");
		DWLServiceControllerHome ctrlHome = (DWLServiceControllerHome)PortableRemoteObject.narrow(object, DWLServiceControllerHome.class);
		DWLServiceController controller = ctrlHome.create();
		return controller;
	}

	private static KafkaProducer<String, String> CreateProducer(String bootstrapServer) {
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
		props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		return producer;
	}

	private static String processMessage(String message, KafkaProducer<String, String> producer, String topic, DWLServiceController controller) throws InterruptedException, ExecutionException, RemoteException, DWLResponseException {
		//write message to topic
		String mdmRespnose = processMessageMDM(message, controller);
		ProducerRecord<String, String> myRecord = new ProducerRecord<String, String>(topic,"mykey",mdmRespnose);
		RecordMetadata meta = producer.send(myRecord).get();
		String offset = String.valueOf(meta.offset());
		//producer.close();
		return "messasge sent, offset is: " + offset;
	}
	
	private static String processMessageMDM(String request, DWLServiceController controller) throws RemoteException{
		HashMap<String,String> aHashMap = new HashMap<String,String>();
		aHashMap.put("TargetApplication", "tcrm");
		aHashMap.put("RequestType", "standard");
		aHashMap.put("ResponseType", "standard");
		aHashMap.put("Parser", "TCRMService");
		aHashMap.put("Constructor", "TCRMService");
		aHashMap.put("OperationType", "all");
		aHashMap.put("requesterName", "cusadmin");
		aHashMap.put("requesterLanguage", "100");
		String response = "";
		try{
			response = (String)controller.processRequest(aHashMap, request);
		}
		catch(DWLResponseException ex){
			response = ex.getMessage();
		}
		return response;
	}

}
