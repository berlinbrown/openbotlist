/**
 * Berlin Brown
 * Dec 22, 2006
 *
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
 *	    * Neither the name of the Botnode.com (Berlin Brown) nor 
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
package org.spirit.util.version;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListVersionReader {
	
	public static final int MAJOR = 0;
	public static final int MINOR = 1;
	public static final int BUILD = 2;
	public static final int REV = 3;
	public static final int MILESTONE = 4;
	
	public static String readLogFile(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader (filename));
		StringBuffer buf = new StringBuffer();
		try {
			String str;
			while ((str = in.readLine()) != null) {
				buf.append(str);
			}
		} finally {
			in.close();
		}
		return buf.toString().trim();
	}
	
	public static void writePropertyFile(PrintWriter out, String [] tuple) {
		// Write to the new property file
		out.println("# (dynamically generated, edit with caution)");
		out.println("versMajor=" + tuple[MAJOR]);
		out.println("versMinor=" + tuple[MINOR]);
		out.println("versBuild=" + tuple[BUILD]);
		out.println("versRev=" + tuple[REV]);
		out.println("versMilestone=" + tuple[MILESTONE]);			
		
	}
	
	public static void main(String [] args) throws Exception {
		
		if (args.length != 2) {
			System.out.println("usage: -f <Output Property File>");
			return;
		}
		  
		PropertyResourceBundle rb = (PropertyResourceBundle) ResourceBundle.getBundle("version");
		String major = rb.getString("versMajor").trim();
		String minor = rb.getString("versMinor").trim();
		String build = rb.getString("versBuild").trim();
		String revision = rb.getString("versRev").trim();
		String milestone = rb.getString("versMilestone").trim();
		
		// Read the svn version log file
		String svnrev = readLogFile("svnversion.log");
		
		// Read the input version file and write to
		// the class directory
		PrintWriter out = null;
		PrintWriter curOut = null;
		try {
			out = new PrintWriter(new BufferedWriter(
					   	new FileWriter(args[1])));
			curOut = new PrintWriter(new BufferedWriter(
				   	new FileWriter("version.properties")));
			
			// Increase the build number and write the
			// revision
			int buildVal = Integer.parseInt(build);
			buildVal++;
			build = "" + buildVal;
			revision = svnrev;
			
			writePropertyFile(out, new String [] {
					major, minor,
					build, revision, milestone,
			});
			writePropertyFile(curOut, new String [] {
					major, minor,
					build, revision, milestone,
			}); 
			
			System.out.println("done; wrote property file to =" + args[1]);
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {			
			if (out != null) {
				out.close();
			}
			if (curOut != null) {
				curOut.close();
			}
		
		}
		
	}
	
}
