/* 
 * LoadTestXMLDefaultHandler.java
 * Apr 15, 2008
 */
package org.spirit.loadtest;

/**
 * @author bbrown
 */
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadTestXMLDefaultHandler extends DefaultHandler {

	public LoadTestXMLDefaultHandler() {
	}

	public void error(SAXParseException e) {
		System.out.println("ERR: Parsing error: " + e.getMessage());
	}

	public void warning(SAXParseException e) {
		System.out.println("WARN: Parsing warning: " + e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		System.out.println("ERR: <fatal> Parsing error: " + e.getMessage());
		System.out.println("ERR: <fatal> Cannot continue.");
	}
} // End of Class
