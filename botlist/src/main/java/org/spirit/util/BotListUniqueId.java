/**
 * Berlin Brown
 * Dec 6, 2006
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
package org.spirit.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is class is used by botverse.
 * 
 * Usage: BotListUniqueId.getUniqueId())
 * @author Berlin Brown
 *
 */
public class BotListUniqueId {
	
	private static Log log = LogFactory.getLog(BotListUniqueId.class);
	
	private static final String toHexString(byte[] bytes) {

        char[] ret = new char[bytes.length * 2];
        for (int i = 0, j = 0; i < bytes.length; i++) {         
            int c = (int) bytes[i];
            if (c < 0) {
                c += 0x100;
            }           
            ret[j++] = Character.forDigit(c / 0x10, 0x10);
            ret[j++] = Character.forDigit(c % 0x10, 0x10);

        }

        return new String(ret);
	}
	
	public static final String getUniqueId() {
		
		String digest = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			String timeVal = "" + (System.currentTimeMillis() + 1);
			String localHost = "";;
			try {
				localHost = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				// If an error, we can use other values.
				log.error("Error trying to get localhost" + e.getMessage());
			}
			
			String randVal = "" + new Random().nextInt();
			String val = timeVal + localHost + randVal;
			md.reset();
			md.update(val.getBytes());

			// Generate the digest.
			digest = toHexString(md.digest());
		} catch(NoSuchAlgorithmException e) {
			log.error("Error trying to generate unique Id" + e.getMessage());
		} // End of the Try - Catch
		
		return digest;
	}		
	
}
