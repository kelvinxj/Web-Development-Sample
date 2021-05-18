package com.kelvin.kelvinTestProjectMaven.xmlTest;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class NamespaceContextExt implements NamespaceContext{
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("ns")) {
			return "http://www.ibm.com/mdm/schema";
		}
		return null;
	}

	public String getPrefix(String namespaceURI) {
		if (namespaceURI.equals("http://www.ibm.com/mdm/schema")) {
			return "ns";
		}
		return null;
	}

	public Iterator getPrefixes(String namespaceURI) {
		ArrayList list = new ArrayList();

		if (namespaceURI.equals("http://www.ibm.com/mdm/schema")) {
			list.add("ns");
		}

		return list.iterator();
	}
}