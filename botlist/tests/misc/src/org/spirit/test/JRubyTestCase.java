/**
 * Berlin Brown
 * Dec 15, 2006
 */
package org.spirit.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class JRubyTestCase extends TestCase {
	
	private ApplicationContext ac;
	
	public JRubyTestCase() {
		super();
	}
	
	public JRubyTestCase(String name) {
		super(name);
	}
	
	public JRubyTestCase(ApplicationContext ac) {
		this.ac = ac;
	}
	
	protected void setUp() {
		
	}	
	public Test suite() {
		TestSuite ts = new TestSuite(JRubyTestCase.class);		
		return ts; 
	}	
	public void testJRubyTest() {
		assertEquals("simple", "simple");
	}	
	public String toString() { 
		return "JRubyTestCase"; 
	}

	public ApplicationContext getAc() {
		return ac;
	}

	public void setAc(ApplicationContext ac) {
		this.ac = ac;
	}
}
