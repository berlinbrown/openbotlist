/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 10, 2007
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
package org.spirit.spring.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.spirit.util.BotListUniqueId;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 * Command line application to generate the insert statement
 * for creating a new user.
 */
public class GenerateUserDDL {
	
	private static final String DDL_LOG_FILE = "user_ddl.sql";
	private static final String NEWLINE = "\r\n";
	
	public static void writeDDL(final String line) {
		
		BufferedWriter ddlout = null;
		try {
			ddlout = new BufferedWriter(new FileWriter(DDL_LOG_FILE, true));
			ddlout.write(NEWLINE + "-------------------------------");
			ddlout.write("New User Insert Statement, Created At=" + new Date() + NEWLINE);
			ddlout.write(line);
			ddlout.write(NEWLINE);
			System.out.println("Done writing SQL User Insert Statement.");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
            if (ddlout != null) {            	
            	try {
            		ddlout.flush();
					ddlout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }             
	}
	
	public static void main(String[] args) throws Exception {
		 
        System.out.println("Generating User DDL");
        if (args.length != 3) {
        	System.out.println("Usage: GenerateUserDDL <user> <password> <email>");
        	return;
        }                
        
        Md5PasswordEncoder encode = new Md5PasswordEncoder();
        String userName = args[0];
        String password = args[1];
        String email = args[2];
        String dob = "1981-01-01";
        
        String account = "";
    	String active = "1";
    	
    	// Hash the password.
    	password = encode.encodePassword(password, null);
    	account =  BotListUniqueId.getUniqueId() + userName;
        StringBuffer buf = new StringBuffer();
        
        buf.append("insert into core_users(");
        buf.append("user_name, ");
        buf.append("user_password, ");
        buf.append("user_email, ");
        buf.append("date_of_birth, ");    	
        buf.append("account_number, ");
        buf.append("active_code, ");    	
        buf.append("last_login_success, ");
        buf.append("last_login_failure, ");
        buf.append("created_on, "); 
        buf.append("updated_on) VALUES(\n    ");
        
        // Add the actual values.
        buf.append("'" + userName + "', ");
        buf.append("'" + password + "', ");
        buf.append("'" + email + "', ");
        buf.append("'" + dob + "', ");
        buf.append("'" + account + "', ");
        buf.append("" + active + ", ");
        
        // Set the Four dates to NOW()
        // last_login_success, last_login_failure, created_on, updated_on
        buf.append("NOW(), NOW(), NOW(), NOW()");
        buf.append(");\n");
            	
        System.out.println("generating=");
        System.out.println(buf.toString());
    	writeDDL(buf.toString());                       
	}	
}
