/*=========================================================
 * Create file LoadTestCookieManager.java
 * Created on Jul 20, 2007
 *=========================================================
 */
package org.spirit.loadtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Simple cookie management; read Set-Cookie cookie key values from response and
 * utilities for sending cookies in client requests.
 */
public class LoadTestCookieManager {

	private Map incomingCookieData = new HashMap();

	private Map outgoingCookieData = new HashMap();

	private static List referer = new ArrayList();

	public LoadTestCookieManager() {
	}

	public final static String filterAlphaNumeric(String value) {
		if (value == null) {
			return value;
		}
		// Filter out non alphanumeric chars
		String output = value.replaceAll("[^\\s0-9a-zA-Z]", "");
		return output.trim();
	}

	protected List getRefererQueue() {
		return referer;
	}

	/**
	 * Normally used for 302 redirects, set the previous or referer URL.
	 */
	public void queueRefererUrl(String referer_str) {
		getRefererQueue().add(referer_str);
		System.out.println("INFO: Setting referering URL=" + referer_str + " / sz=" + getRefererQueue().size());
	}

	public String getRefererUrl() {
		System.out.println("($) Referer queue size=" + getRefererQueue().size());
		if (referer.size() < 1)
			return null;
		if (true)
			return null;
		// There must be at least two values in the
		int cur_size = getRefererQueue().size();
		int query_idx = cur_size - 1;
		System.out.println("($) Referer=" + getRefererQueue().get(query_idx));
		return (String) getRefererQueue().get(query_idx);
	}

	public static void loadExistingCookies(final String currentHost, final Map siteCookieData) {
		String filename_cookie = "cookies/" + filterAlphaNumeric(currentHost) + ".dat";
		BufferedReader in = null;
		String feed = null;
		try {
			in = new BufferedReader(new FileReader(filename_cookie));
			while ((feed = in.readLine()) != null) {
				feed = feed.trim();
				if ((feed != null) && (feed.length() > 2)) {
					// Parse the cookie content in the cookie store
					int eq_index = feed.indexOf("=");
					if (eq_index > 0) {
						String key = feed.substring(0, eq_index);
						String val = feed.substring(eq_index + 1);
						System.out.println("[+] Loading existing cookie=" + key);
						siteCookieData.put(key.trim(), val.trim());
					} // End of the if
				}
			}
		} catch (Exception e) {
			System.out.println("INFO: no existing cookie data");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException ie) {
				}
		}
	}

	private void parseCookieValue(final String currentHost, final String cookieContent, final Map siteCookieData) {
		if (cookieContent == null) {
			return;
		}
		String entries[] = cookieContent.split(";");
		for (int i = 0; i < entries.length; i++) {
			int eq_index = entries[i].indexOf("=");
			if (eq_index > 0) {
				String entry = entries[i];
				String key = entry.substring(0, eq_index);
				String val = entry.substring(eq_index + 1);
				System.out.println("[*] Setting cookie=" + key);
				siteCookieData.put(key.trim(), val.trim());
			} // End of the if

			// Ignore the cookie meta data for now
			break;
		}
	}

	private Map getIncomingCookieDataInstance(final String currentHost) {
		Map hostCookieData = null;
		if (this.getIncomingCookieData(currentHost) == null) {
			// Create new cookie value
			hostCookieData = new HashMap();
			incomingCookieData.put(currentHost, hostCookieData);
			return hostCookieData;
		} else {
			// Load the existing cookie data for this post
			hostCookieData = (Map) incomingCookieData.get(currentHost);
			return hostCookieData;
		}
	}

	public void readCookieData(final String currentHost) {
		Map siteCookieData = getIncomingCookieDataInstance(currentHost);
		loadExistingCookies(currentHost, siteCookieData);
	}

	/**
	 * Load data from the response header fields; in particular the
	 * value from the "Set-Cookie".  It is possible to have multple Set-Cookie values.
	 *
	 * After data is loaded into the map data structure, set the Map data for this particular host in
	 * the outgoing data store.
	 *
	 * (Note: a new map datastore is each time this method is called)
	 *
	 * Updated: 7/23/2007 - updated with "load existing" switch
	 *
	 * @param conn
	 * @param currentHost
	 */
	public void parseCookieData(HttpURLConnection conn, final String currentHost, final boolean loadExisting) {
		Map siteCookieData = new HashMap();
		// Load pre-existing cookie data from file.
		if (loadExisting) {
			loadExistingCookies(currentHost, siteCookieData);
		}

		for (int i = 1; i < 50; i++) {
			// Note: 50 = arbitrary value to invalid headers
			if (conn.getHeaderFieldKey(i) == null)
				break;
			String headerName = conn.getHeaderFieldKey(i);
			if (headerName.equalsIgnoreCase("Set-Cookie")) {
				String cookieContent = conn.getHeaderField(i);
				parseCookieValue(currentHost, cookieContent, siteCookieData);
			}
		}
		outgoingCookieData.put(currentHost, siteCookieData);
	}

	public static String getCookieDataUtil(final Map hostCookieData) {
		StringBuffer buf = new StringBuffer();
		for (Iterator it = hostCookieData.entrySet().iterator(); it.hasNext();) {
			Map.Entry set = (Map.Entry) it.next();
			String key = (String) set.getKey();
			String val = (String) set.getValue();
			buf.append(key + "=" + val + "; ");
		}
		System.out.println("For client request, cookie data(Cookie:)=" + buf.toString());
		return buf.toString();
	}

	public String getIncomingCookieData(final String currentHost) {
		if (!this.incomingCookieData.containsKey(currentHost))
			return null;
		Map host_data = (Map) this.incomingCookieData.get(currentHost);
		return getCookieDataUtil(host_data);
	}

	/**
	 * Write the simple cookie database as so; one file per host.
	 */
	public void writeCookieData() throws IOException {
		BufferedWriter cookie_out = null;
		try {
			for (Iterator it = outgoingCookieData.entrySet().iterator(); it.hasNext();) {
				Map.Entry set = (Map.Entry) it.next();
				System.out.println("INFO: writing cookie=(" + set.getKey() + " [" + set.getValue() + "])");

				String filename_cookie = "cookies/" + filterAlphaNumeric((String) set.getKey()) + ".dat";
				File cur_file = new File(filename_cookie);
				System.out.println("INFO: writing cookie file=" + cur_file.getAbsolutePath());
				cookie_out = new BufferedWriter(new FileWriter(filename_cookie, false));

				// Write each key value on a line
				Map host_data = (Map) set.getValue();
				for (Iterator host_it = host_data.entrySet().iterator(); host_it.hasNext();) {
					Map.Entry host_set = (Map.Entry) host_it.next();
					cookie_out.write(host_set.getKey() + "=" + host_set.getValue() + "\n");
				} // End of the for
			}
		} finally {
			if (cookie_out != null) {
				cookie_out.flush();
				cookie_out.close();
			}
		} // End of the try - finally
	}
}
//=========================================================
//End of File
//=========================================================