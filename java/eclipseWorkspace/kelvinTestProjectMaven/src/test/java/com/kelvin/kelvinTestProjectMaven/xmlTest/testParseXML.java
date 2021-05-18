package com.kelvin.kelvinTestProjectMaven.xmlTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.After;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class testParseXML {
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
        xml = bos.toString();
        
        return xml;
	}
	
	public static Document getDocument(String response) throws SAXException, IOException{		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) { 
			e1.printStackTrace();
		}
		Document doc = db.parse(new InputSource(new StringReader(response)));
		return doc;
		
	}
	
	public XPath getXpath(){
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(new NamespaceContextExt());
		return xpath;
	}
	
	@Test
	public void processXML() throws SAXException, IOException, TransformerException, XPathExpressionException{
		String xml = getXMLStringFromFile("Suppression_Permission_Country_Medium_Status.xml");
		Document doc = getDocument(xml);
		XPath xpath = this.getXpath();
		String sqlTemplate = "insert into TEMP_XCDCTRYOPTINOUT values(''no name'',{4},{5},''{1}'',''{2}'',''{3}'',''{0}'');";
		NodeList nodes = (NodeList)xpath.evaluate("//SuppressionPermissionCountryMediumStatus", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			NodeList childNodes = node.getChildNodes();

			String countryCode = "";
			String email = "";
			String phone = "";
			String addr = "";
			String sql = "";
			String effective_date = "";
			String change_date = "";
			
			for(int j = 0;j<childNodes.getLength();j++){
				Node subNode = childNodes.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE){
					if(StringUtils.compareIgnoreCaseWithTrim("mo:SuppressionPermissionCountryMediumStatus", subNode.getNodeName()))
						countryCode = subNode.getTextContent().trim();
					else if(StringUtils.compareIgnoreCaseWithTrim("BDS_AV_QualifingValue1", subNode.getNodeName()))
						email = subNode.getTextContent();
					else if(StringUtils.compareIgnoreCaseWithTrim("BDS_AV_QualifingValue2", subNode.getNodeName()))
						phone = subNode.getTextContent();
					else if(StringUtils.compareIgnoreCaseWithTrim("BDS_AV_QualifingValue4", subNode.getNodeName()))
						addr = subNode.getTextContent();
					else if(StringUtils.compareIgnoreCaseWithTrim("BDS_AV_EffectiveDate", subNode.getNodeName()))
						effective_date = subNode.getTextContent();
					else if(StringUtils.compareIgnoreCaseWithTrim("BDS_AV_ChangeDeleteDate", subNode.getNodeName()))
						change_date = subNode.getTextContent();
					else
						;
				}
			}
			
			if(effective_date == "")
				effective_date = "null";
			else
				effective_date = "'" + effective_date + "'";
			if(change_date == "")
				change_date= "null";
			else
				change_date = "'" + change_date + "'";
			if(countryCode != ""){
				sql = MessageFormat.format(sqlTemplate, countryCode, email, phone, addr, effective_date, change_date);
				System.out.println(sql);
			}
		}
	}
}
