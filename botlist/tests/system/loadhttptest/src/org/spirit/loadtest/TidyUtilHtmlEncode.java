/* 
 * TidyUtilHtmlEncode.java (Berlin Brown)
 * Apr 17, 2008
 * 
 *  Modified From Java HTML Tidy - JTidy
 *  HTML parser and pretty printer
 *  
 *  Copyright (c) 1998-2000 World Wide Web Consortium (Massachusetts
 *  Institute of Technology, Institut National de Recherche en
 *  Informatique et en Automatique, Keio University). All Rights
 *  Reserved.
 *
 *  COPYRIGHT NOTICE:
 *
 *  This software and documentation is provided "as is," and
 *  the copyright holders and contributing author(s) make no
 *  representations or warranties, express or implied, including
 *  but not limited to, warranties of merchantability or fitness
 *  for any particular purpose or that the use of the software or
 *  documentation will not infringe any third party patents,
 *  copyrights, trademarks or other rights.
 *
 *  The copyright holders and contributing author(s) will not be
 *  liable for any direct, indirect, special or consequential damages
 *  arising out of any use of the software or documentation, even if
 *  advised of the possibility of such damage.
 *
 *  Permission is hereby granted to use, copy, modify, and distribute
 *  this source code, or portions hereof, documentation and executables,
 *  for any purpose, without fee, subject to the following restrictions:
 *
 *  1. The origin of this source code must not be misrepresented.
 *  2. Altered versions must be plainly marked as such and must
 *     not be misrepresented as being the original source.
 *  3. This Copyright notice may not be removed or altered from any
 *     source or altered source distribution.
 *
 *  The copyright holders and contributing author(s) specifically
 *  permit, without fee, and encourage the use of this source code
 *  as a component for supporting the Hypertext Markup Language in
 *  commercial products. If you use this source code in a product,
 *  acknowledgment is not required but would be appreciated.
 */

package org.spirit.loadtest;

import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * @author bbrown
 */
public class TidyUtilHtmlEncode {

	private static Method encodeMethod14;
	static {
		// URLEncoder.encode(String) has been deprecated in J2SE 1.4.
		// Take advantage of the new method URLEncoder.encode(String, enc) if J2SE 1.4 is used.
		try {
			Class urlEncoderClass = Class.forName("java.net.URLEncoder");
			encodeMethod14 = urlEncoderClass.getMethod("encode", new Class[] { String.class, String.class });
		} catch (Throwable ex) {
			// encodeMethod14 will be null if exception
		}
	}
	private static final String[] ENTITIES = { 
			">", "&gt;", 
			"<", "&lt;", 
			"&", "&amp;", 
			"\"", "&quot;", 
			"'", "&#039;",
			"\\", "&#092;", 
			"\u00a9", "&copy;", 
			"\u00ae", "&reg;"
			
	};

	private static Hashtable entityTableEncode = null;

	protected static synchronized void buildEntityTables() {
		entityTableEncode = new Hashtable(ENTITIES.length);
		for (int i = 0; i < ENTITIES.length; i += 2) {
			if (!entityTableEncode.containsKey(ENTITIES[i])) {
				entityTableEncode.put(ENTITIES[i], ENTITIES[i + 1]);
			}
		}
	} // End of the Method

	/**
	 * Converts a String to HTML by converting all special characters to HTML-entities.
	 */
	public final static String encode(String s) {
		return encode(s, "\n", true);
	}

	/**
	 * Converts a String to HTML by converting all special characters to HTML-entities.
	 */
	public final static String encode(final String s, final String cr, final boolean ignore_unicode) {
		if (entityTableEncode == null) {
			buildEntityTables();
		}
		if (s == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer(s.length() * 2);
		char ch;
		for (int i = 0; i < s.length(); ++i) {
			ch = s.charAt(i);
			if ((ch >= 63 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch == ' ')) {
				sb.append(ch);
			} else if (ch == '\n') {
				// How to handle carriage returns
				sb.append(cr);
			} else {
				String chEnc = encodeSingleChar(String.valueOf(ch));
				if (chEnc != null) {
					sb.append(chEnc);
				} else {
					if (!ignore_unicode) {
						// 	Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(new Integer(ch).toString());
						sb.append(';');
					} else {
						sb.append(ch);
					} // End of the if - else
				}
			}
		}
		return sb.toString();
	}	
    /**
     * Converts a single character to HTML
     */
    private static String encodeSingleChar(String ch) {    
        return (String) entityTableEncode.get(ch);
    }
} // End of the Class
