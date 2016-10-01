package com.Radcliffe.copbuilder_app;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParserXML  {
	
	
	public ParserXML() {
		
	}
	
	
	private void Parse()throws ParserConfigurationException, SAXException, IOException{
		String xmlFile = "closeoutpackagetypes2.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document xmlDoc = builder.parse(new File(xmlFile));
		String siteID = "5GW1008A";
		NodeList nl = xmlDoc.getDocumentElement().getChildNodes();
	
		for(int i=0; i<nl.getLength(); i++){
			Node node = nl.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				
				Element element = (Element) node;
				
				System.out.println(node.getAttributes().getNamedItem("type").getNodeValue());
				NodeList nl2 = element.getElementsByTagName("item");
				for(int x = 0; x < nl2.getLength(); x++){
					if(nl2 != null){
						System.out.println(x +1  + "_" + siteID + "_" + nl2.item(x).getTextContent());
					}
				}
			}
		}//end for
	}
	public static void main(String[] a){
	
	
	}
	
}