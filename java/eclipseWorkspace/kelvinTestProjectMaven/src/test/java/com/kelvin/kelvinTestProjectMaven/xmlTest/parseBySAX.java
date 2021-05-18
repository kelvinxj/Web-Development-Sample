package com.kelvin.kelvinTestProjectMaven.xmlTest;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class parseBySAX {

	
	public static class SAXLocalNameCount extends DefaultHandler {
	    
	    private Hashtable tags;
	    
	    private String currentTagName = "";
	    private String previousTagName = "";
    	private String text = "";
	    
	    private boolean isSameNode = false;

	    public void startDocument() throws SAXException {
	        tags = new Hashtable();
	    }

	    public void endDocument() throws SAXException {
	        Enumeration e = tags.keys();
	        while (e.hasMoreElements()) {
	            String tag = (String)e.nextElement();
	            int count = ((Integer)tags.get(tag)).intValue();
	            //System.out.println("Local Name \"" + tag + "\" occurs " 
	             //                  + count + " times");
	        }    
	    }
	    
	    @Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
	    	String nodeValue = new String(ch, start, length);
	    	if(nodeValue.trim().length() != 0){
		    	if(isSameNode){
		    		text += nodeValue.trim();
		    	}
		    	else{
		    		System.out.println(previousTagName + ":" + text);
		    		text = "";
		    	}
	    	}
		}

		public void startElement(String namespaceURI, String localName,String qName,Attributes atts) throws SAXException {
			currentTagName = localName;
			if(currentTagName.equals(previousTagName))
				isSameNode = true;
			else
				isSameNode = false;
			previousTagName = localName;
			
			String key = localName;
			Object value = tags.get(key);
			
			if (value == null) {
				tags.put(key, new Integer(1));
			} 
			else {
				int count = ((Integer)value).intValue();
				count++;
				tags.put(key, new Integer(count));
			}
		}
	}
    
    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser sp = spf.newSAXParser();
		XMLReader xmlReader = sp.getXMLReader();
		xmlReader.setContentHandler(new SAXLocalNameCount());
		xmlReader.parse(convertToFileURL("Suppression_Permission_Country_Medium_Status_Simple.xml"));
		//System.out.println(convertToFileURL("Suppression_Permission_Country_Medium_Status.xml"));

	}

}
