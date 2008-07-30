/* 
 * TestSuite.java
 * Mar 16, 2008
 */
package org.spirit.test.unit;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.spirit.test.unit.text.TestValidator;

/**
 * @author bbrown
 */
public class IntegrationTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite();		
		suite.addTestSuite(TestValidator.class);
		System.out.println("Number of Test Cases: " + suite.countTestCases());		
		return suite;
	}

	public static void main(String args[]) {			
		TestRunner.run(suite());
		return;
	}
}
