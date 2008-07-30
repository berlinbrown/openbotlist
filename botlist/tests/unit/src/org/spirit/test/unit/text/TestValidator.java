/* 
 * EntityLinkTest.java
 * Aug 19, 2007
 */
package org.spirit.test.unit.text;

import junit.framework.TestCase;

import org.spirit.spring.validate.BotListEntityLinksValidator;

/**
 * @author bbrown
 */
public class TestValidator extends TestCase {
	
	private static final String GENERATORS [] = {
		"",
		"http://www.google.com",
		"abc://abc.com"
	};
	
	protected void setUp() {	
	}
	protected void tearDown() {	
	}
	
	public void testValidateURL() {
		for (int i = 0; i < GENERATORS.length; i++) {
			System.out.println("---->" + BotListEntityLinksValidator.isValidUrl(GENERATORS[i]));
		}
	}
}
