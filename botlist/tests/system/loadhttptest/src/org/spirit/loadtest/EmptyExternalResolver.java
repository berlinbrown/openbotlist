/* 
 * LoadTestXMLValidate.java
 * Apr 15, 2008
 */
package org.spirit.loadtest;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * http://www.ibm.com/developerworks/library/x-tipent.html
 *
 */
public class EmptyExternalResolver implements EntityResolver {
	
    public InputSource resolveEntity(String publicID, String systemID)
        	throws SAXException {    	
    	//System.out.println("-->" + publicID + " -->" + systemID);
        if ((systemID != null)) {
        	if (systemID.equals("http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd")) {        
        		// Return local copy of the copyright.xml file
        		return new InputSource("data/dtd/xhtml1-transitional.dtd");
        	}
        }
        // If no match, returning null makes process continue normally
        return null;
    }
}