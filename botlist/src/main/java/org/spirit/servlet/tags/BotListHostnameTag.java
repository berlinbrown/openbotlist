/**
 * Berlin Brown
 * Dec 26, 2006
 */
package org.spirit.servlet.tags;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListHostnameTag extends BotListExpressionBaseTag  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1478472796158005066L;
	
	public BotListHostnameTag() {
		super();
	}
	
	/**
	 * getHostname	
	 * @throws MalformedURLException 
	 */
	private String getHostname(String urlStr) {	
		URL url;
		try {
			url = new URL(urlStr);
			String curHostname = url.getHost();
			String scheme = url.getProtocol();
			String fullNewURL = "";
			if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https")) {
				fullNewURL = scheme + "://" + curHostname;
				// Also add a link for users to get to that particular host
				fullNewURL += " <a href=\"" + fullNewURL + "\" class=\"linklist_comments_host\">+</a>";
			} else {
				fullNewURL = curHostname;
			}
			return fullNewURL;
		} catch (MalformedURLException e) {
			return "invalid-hostname";
		}
		
	}
	
	protected String getActionResult(String input) {
		return getHostname(input);
	}
}
