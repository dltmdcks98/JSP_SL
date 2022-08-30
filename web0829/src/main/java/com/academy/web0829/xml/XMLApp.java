package com.academy.web0829.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLApp {
	SAXParserFactory factory = SAXParserFactory.newInstance();//싱글톤
	
	public XMLApp() {
		try {
			
			SAXParser saxParser =factory.newSAXParser();//파서 준비
			
			MyHandler handler = new MyHandler();
			saxParser.parse(new File("D:/OneDrive-LSC/SLAcademy/JSP_SL/web0829/src/main/java/com/academy/web0829/xml/member.xml"), handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new XMLApp();
	}
}
