/*
 * Create file LoadTestWriteHtmlDoc.java
 * Created on Jul 23, 2007
 */

package org.spirit.loadtest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for when writing the HTML document to the client filesystem.
 */
public class LoadTestWriteHtmlDoc {

	public static String generatedHtmlFilename(final String request_url) {
		String filename = LoadTestCookieManager.filterAlphaNumeric(request_url);
		return filename;
	}

	public static void writeOutput(final String filename, final String alldata) {		
		String outfilename = filename;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(outfilename, false));
			out.write(alldata);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
			}
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}
}
//=========================================================
//End of File
//========================================================= 