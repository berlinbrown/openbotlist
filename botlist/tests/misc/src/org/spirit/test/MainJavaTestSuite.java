/**
 * Berlin Brown
 * Dec 15, 2006
 */
package org.spirit.test;

//import junit.framework.Test;
//import junit.framework.TestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.spirit.test.java.bean.EntityLinkTest;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 */
public class MainJavaTestSuite  {

	public static Test suite() {
		TestSuite suite = new TestSuite();		
		suite.addTestSuite(EntityLinkTest.class);
		System.out.println("Number of Test Cases: " + suite.countTestCases());		
		return suite;
	}

	public static void main(String args[]) {			
		TestRunner.run(suite());
		return;
	}
}
