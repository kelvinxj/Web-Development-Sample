package com.ibm.mdm.testtool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.dwl.base.exception.DWLResponseException;
import com.dwl.base.requestHandler.beans.DWLServiceController;
import com.dwl.base.requestHandler.beans.DWLServiceControllerHome;

public class GetMDMEJB {

	public static void main(String[] args) throws NamingException, CreateException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub

		String serverUrl = "";
		// serverUrl should be like "corbaloc::127.0.0.1:2809";
		serverUrl = System.getProperty("serverUrl");
		if(serverUrl == null){
			System.out.println("Please provide serverUrl system property");
			System.exit(1);
		}
		Hashtable<String, String> params = new Hashtable<>();
		params.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
		params.put("java.naming.provider.url", serverUrl);
		InitialContext initCtx = new InitialContext(params);
		Object object = initCtx.lookup( "com/dwl/base/requestHandler/beans/DWLServiceController");
		DWLServiceControllerHome ctrlHome = (DWLServiceControllerHome)PortableRemoteObject.narrow(object, DWLServiceControllerHome.class);
		DWLServiceController controller = ctrlHome.create();
		System.out.println("ejb class: " + controller.getClass().getCanonicalName());
		
		HashMap<String,String> aHashMap = new HashMap<String,String>();
		aHashMap.put("TargetApplication", "tcrm");
		aHashMap.put("RequestType", "standard");
		aHashMap.put("ResponseType", "standard");
		aHashMap.put("Parser", "TCRMService");
		aHashMap.put("Constructor", "TCRMService");
		aHashMap.put("OperationType", "all");
		aHashMap.put("requesterName", "cusadmin");
		aHashMap.put("requesterLanguage", "100");

		String requestXml = loadRequest();
		try{
			String response = (String)controller.processRequest(aHashMap, requestXml);
			System.out.println("Successful response:");
			System.out.println(response);
		}
		catch(DWLResponseException ex){
			System.out.println("Fatal response:");
			System.out.println(ex.getMessage());
		}
	}

	private static String loadRequest() throws SAXException, IOException, TransformerException {
		String result = "";
		String filePath = "";
		filePath = System.getProperty("requestFile");
		if(filePath == null)
			result = "";
		else{
			result = getXMLStringFromFile(filePath);
		}
		return result;
	}
	
	public static String getXMLStringFromFile(String path) throws SAXException, IOException, TransformerException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) { 
			e1.printStackTrace();
		}
		
		File xmlFile = new File(path);
		Document doc = db.parse(xmlFile);
		String xml = "";
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = transFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);

        //export string
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        transFormer.transform(domSource, new StreamResult(bos));
        xml = bos.toString("utf-8");
        
        return xml;
	}
}