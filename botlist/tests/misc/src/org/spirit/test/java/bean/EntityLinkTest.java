/* 
 * EntityLinkTest.java
 * Aug 19, 2007
 */
package org.spirit.test.java.bean;

import junit.framework.TestCase;

import org.spirit.bean.impl.BotListEntityLinks;

/**
 * @author bbrown
 */
public class EntityLinkTest extends TestCase {
	
	protected void setUp() {	
	}
	protected void tearDown() {	
	}
	
	public void testHostnameDisplay() {
		BotListEntityLinks link = new BotListEntityLinks();
		link.setHostname("http://www.test1.com/searchit/blah");
		String hostname = link.getHostnameDisplay();
		assertEquals(hostname, "http://www.test1.com/searchit/blah");
		
		link.setHostname(null);
		link.setMainUrl("http://www.test1.com/searchit/blah");
		hostname = link.getHostnameDisplay();		
		assertEquals(hostname, "http://www.test1.com");
				
		link.setHostname("http://www.test1.com/searchit/blah//searchit/blah/searchit/blah");
		hostname = link.getHostnameDisplay();
		assertEquals(hostname, "http://www.test1.com/searchit/blah//sea...");
						
	}

}
