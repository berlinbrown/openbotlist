/**
 * Berlin Brown
 * Jan 4, 2007
 */
package org.spirit.servlet.tags;

import java.io.Serializable;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListEncodeHtml  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007269620924320835L;
	
	public static String encodeUrl(String input) {	
		
		String str = input;
		if (str == null) {
			return str;
		}
		
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

}
