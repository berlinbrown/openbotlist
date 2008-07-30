/* 
 * LoadTestXMLValidate.java
 * Apr 15, 2008
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for 
 * social bookmarking and collecting online news content for use on the web.  
 * Multiple web front-ends exist for Django, Rails, and J2EE.  
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *	    * Redistributions of source code must retain the above copyright notice, 
 *	    this list of conditions and the following disclaimer.
 *	    * Redistributions in binary form must reproduce the above copyright notice, 
 *	    this list of conditions and the following disclaimer in the documentation 
 *	    and/or other materials provided with the distribution.
 *	    * Neither the name of the Newspiritcompany.com (Berlin Brown) nor 
 *	    the names of its contributors may be used to endorse or promote 
 *	    products derived from this software without specific prior written permission.
 *	
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE --
 */
package org.spirit.loadtest;

import java.io.StringReader;

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.InputSource;

/**
 * Simple validator to validate XHTML documents.
 * @author bbrown
 */
public class LoadTestXMLValidate {
	
	/**
	 * Hopefully, we can use a non-validating parser:	
	 * "Will try to retrieve all entities defined in the DTD, but will cease processing the 
	 * DTD content at the first entity it can't find, But this is not an error -- 
	 * the parser simply makes available the XML data (and the names of any unresolved entities) to the application."
	 * 
	 * Also see:
	 * 
	 * http://www.ibm.com/developerworks/library/x-tipent.html
	 * 
	 * @param   url
	 * @param   http_data
	 * @return  Tuple:(err_flag:Boolean, String Data) 
	 */
	public static Object [] validateXML(final String url, final String http_data) {		
		try {
			if ((http_data == null) || (http_data.length() == 0)) {
				throw new RuntimeException("Invalid HTTP data argument");
			}			
			final DOMParser parser = new DOMParser();
			parser.setEntityResolver(new EmptyExternalResolver());
			// Enable or disable validate
			parser.setFeature("http://xml.org/sax/features/validation", false);
			parser.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",
							"memory.xsd");
			LoadTestXMLDefaultHandler errors = new LoadTestXMLDefaultHandler(); 
			parser.setErrorHandler(errors);
			parser.parse(new InputSource(new StringReader(http_data)));
			Object tuple [] =  { new Boolean(true), "" };
			return tuple;
		} catch (Exception e) {
			final StringBuffer err_msg = new StringBuffer();
			err_msg.append("ERR: validateXML() - Error validating HTTP content.");
			err_msg.append("ERR: url=" + url + " error=" + e.getMessage());
			Object tuple [] =  { new Boolean(false), err_msg.toString() };
			return tuple;
		}
	}
	
	private static void main(String [] args) { 
		// Driver Test
		final String data_header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		final StringBuffer xml_buf_good = new StringBuffer();
		final StringBuffer xml_xhtml = new StringBuffer();
		
		xml_buf_good.append(data_header);
		xml_buf_good.append("<root><data value=\"3\" /></root>");
		LoadTestXMLValidate.validateXML("http://www.google.com", xml_buf_good.toString());
		
		xml_xhtml.append(data_header);
		xml_xhtml.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">");
		xml_xhtml.append("<head><title>The Title</title></head><body><p>Data</p></body></html>");
		Object res = LoadTestXMLValidate.validateXML("http://www.google.com", xml_xhtml.toString());
		System.out.println("Result XHTML parse ->" + res);
	}
	
} // End of Class
