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
public class MainTestSuite  {
	
	public static void loadAllTestCases(String [] filenames, TestSuite suite) {
		
		GenericJRubyLoader loader = new GenericJRubyLoader();
		loader.setApplicationContext(new FileSystemXmlApplicationContext(filenames));		
		File f = new File("ruby");
		File all [] = f.listFiles();				
		for (int i = 0; i < all.length; i++) {
			// Ignore files without ruby extension.
			if (!all[i].getName().endsWith("rb")) continue;
			try {
				System.out.println("--> loading script=" + all[i].getAbsolutePath());
				long tStart = System.currentTimeMillis();
				Object rubyTest = loader.runRubyScript(all[i].getAbsolutePath());
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
	}	
	public static Test suite(String [] filenames) {
		TestSuite suite = new TestSuite();		
		loadAllTestCases(filenames, suite);
		System.out.println("Number of Test Cases: " + suite.countTestCases());		
		return suite;
	}

	public static void main(String args[]) {
		
		if (args.length != 3) {
			System.out.println("usage: -f <FILENAME>");
			return;
		}
		String [] springXMLConfigs = { args[1], args[2] };
		TestRunner.run(suite(springXMLConfigs));
		return;
	}
}
