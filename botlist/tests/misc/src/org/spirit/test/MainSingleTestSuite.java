/**
 * Berlin Brown
 * Dec 15, 2006
 */
package org.spirit.test;

//import junit.framework.Test;
//import junit.framework.TestSuite;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.bsf.BSFException;
import org.spirit.util.GenericJRubyLoader;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 */
public class MainSingleTestSuite  {
	
	public static void loadAllTestCases(boolean loadSpring, String [] filenames, TestSuite suite, String testFileName) {
		
		GenericJRubyLoader loader = new GenericJRubyLoader();
		if (loadSpring)
			loader.setApplicationContext(new FileSystemXmlApplicationContext(filenames));		
		File f = new File("ruby/" + testFileName + ".rb");				
			// Ignore files without ruby extension.			
			try {
				System.out.println("--> loading script=" + f.getAbsolutePath());
				long tStart = System.currentTimeMillis();
				Object rubyTest = loader.runRubyScript(f.getAbsolutePath());
				System.out.println("Object Returned --> " + rubyTest);
				if (rubyTest instanceof Exception) {
					((Exception) rubyTest).printStackTrace();
				}
				long tEnd = System.currentTimeMillis();
				double tDiff = (tEnd - tStart) / 1000.0;
				System.out.println("Test run in=" + tDiff  + " ms");
				//suite.addTest(rubyTest.suite());
			} catch (BSFException e) {	
				e.printStackTrace();
			}		
	}	
	public static Test suite(boolean loadSpring, String [] filenames, String testFileName) {
		TestSuite suite = new TestSuite();		
		loadAllTestCases(loadSpring, filenames, suite, testFileName);
		System.out.println("Number of Test Cases: " + suite.countTestCases());		
		return suite;
	}

	public static void main(String args[]) {
		
		if (args.length != 4) {
			System.out.println("usage: <boolean-load-spring> <FILENAME> <FILENAME2> <LOAD TEST FILE>");
			return;
		}		
		boolean loadSpring = Boolean.parseBoolean(args[0]);
		String [] springXMLConfigs = { args[1], args[2] };
		TestRunner.run(suite(loadSpring, springXMLConfigs, args[3]));
		return;
	}
}
