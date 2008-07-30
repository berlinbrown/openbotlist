/* 
 * Created on Jul 20, 2007 
 */
package org.spirit.loadtest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** 
 * Sequence Format File should contain the following format:
 * 
 * METHOD_TYPE, URL, <DATA if POST enabled, separated by ';' semicolon [key=value]>
 * 
 */
public class LoadTestSequenceParser {
	
	private List sequenceData = new ArrayList();
	
	private static final String REQUEST_GET = "GET";
	private static final String REQUEST_POST = "POST";
	
	private static final String COMMAND_SLEEP = "SLEEP";
	
	private static final String TEMPLATE_COMMAND_RAND = "100";
	
	public static final String [][] TEMPLATE_COMMANDS = {
		{ "RAND", TEMPLATE_COMMAND_RAND }
	};
	
	private final String START_TOKEN = "{";
	private final String END_TOKEN = "}";
	
	private Random randObj = new Random();
	private final int MAX_RAND_INT = 10000;
	
	public List getSequenceData() {
		return sequenceData;
	}
	
	/**
	 * When certain template parameters are found within sequence scripts,
	 * parse them accordingly.
	 * 
	 * For example; http://www.yahoo{RAND#100}.com
	 * 
	 * Will replace the variable with a random integer number between 0 and 100.
	 * 
	 * @return
	 */
	public String processTemplateVars(final String var) {
		int startIndex = var.indexOf(START_TOKEN);
		int endIndex = var.indexOf(END_TOKEN);
		if ((endIndex > startIndex) && (startIndex >= 0)) {
			String templateVals = var.substring(startIndex, endIndex);
			String res = parseTemplateVars(templateVals);
			// Replace the template variable with a value.
			if (res != null) {				
				return var.substring(0, startIndex) + res + var.substring(endIndex + 1);
			}
		}
		return null;
	}
	
	/**
	 * Return the system command code based on the input template variable.
	 * 
	 * For example, RAND = system code 100 return value.
	 * 
	 * @param var
	 * @return
	 */
	public String parseTemplateVars(final String var) {		
		for (int i = 0; i < TEMPLATE_COMMANDS.length; i++) {
			String systemCommand = TEMPLATE_COMMANDS[i][0];
			String systemCode = TEMPLATE_COMMANDS[i][1];			
			String templateCommand = var.substring(1, var.length());
			if (systemCommand.equalsIgnoreCase(templateCommand)) {
				if (TEMPLATE_COMMAND_RAND.equalsIgnoreCase(systemCode)) {
					return "" + randObj.nextInt(MAX_RAND_INT);
				}
			} // End of if - system command / template command
		}
		return null;
	}
	
	public void parse(final String sequence_file) {		
		Object data [] = LoadTestManager.loadDataFile(sequence_file);
		for (int i = 0; i < data.length; i++) {
			String [] tokens = ((String) data[i]).split(",");
			if ((tokens.length == 2) || (tokens.length == 3)) {													
				SequenceModel model = new SequenceModel();
				String action = tokens[0].trim();
				model.setActionMethod(tokens[0].trim());
				model.setRequestUrl(tokens[1].trim());				
				if (action.equalsIgnoreCase(REQUEST_POST)) {
					// Load the POST content
					String postDataStr = tokens[2].trim();
					String postData [] = postDataStr.split(";");				
					for (int k = 0; k < postData.length; k++) {
						String key_val = postData[k];
						int eq_idx = key_val.indexOf("=");
						String key = key_val.substring(0, eq_idx).trim();				
						String val = key_val.substring(eq_idx + 1).trim();
						
						// Template (variable replace) the key and/or value string values
						String parseKey = processTemplateVars(key);
						String parseVal = processTemplateVars(val);
						key = (parseKey != null) ? parseKey : key;
						val = (parseVal != null) ? parseVal : val;					
						model.putPostData(key, val);
					}
				}
				// Add the sequence model to the current set of data
				sequenceData.add(model);
			} else {
				throw new RuntimeException("Invalid Sequence Data Format, format tokens=" + tokens.length);
			}
		} // End of the For
		
		System.out.println("INFO: " + sequenceData.size() + " requests found in sequence");
	}
	public void printSummary() {
		for (Iterator it = sequenceData.iterator(); it.hasNext();) {
			SequenceModel statement = (SequenceModel) it.next();
			System.out.println(statement);						
		}
	}
	
	private String [] handleGETRequest(SequenceModel statement, final boolean loadExistingCookies) {
		String responseTuple [] = { "" , "", "" };
		String url = statement.getRequestUrl();			
		if (url != null && url.toLowerCase().startsWith("https")) {
			// Connect with SSL connection
			System.out.println("INFO: attempting connect with SSL connection");
			responseTuple = LoadTestManager.connectURLSSL(url, loadExistingCookies);
		} else {
			responseTuple = LoadTestManager.connectURL(url, loadExistingCookies);
		} // End of the if
		return responseTuple;
	}
	
	private String [] handlePOSTRequest(SequenceModel statement, final boolean loadExistingCookies) throws Exception {
		String responseTuple [] = { "" , "", "" };
		String str_url = statement.getRequestUrl();			
		
		URL url = LoadTestManager.getSSLURL(str_url);
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();	
		LoadTestManager.postDataSSL(statement.getPostData(), conn, url, str_url, false, loadExistingCookies);
		System.out.println("done posting SSL data");
		
		return responseTuple;
	}
	
	private void handleSleepCommand(SequenceModel statement) {
		String time_ms = statement.getRequestUrl();
		int sleepTime = Integer.parseInt(time_ms);
		try {			
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {			
		}		
	}
	
	/**
	 * Handle all of the sequence statements and perform the according requests.	 
	 */
	public void handleSequence() {
		int sequenceNo = 0;
		for (Iterator it = this.sequenceData.iterator(); it.hasNext();) {			
			boolean loadExistingCookies = (sequenceNo == 0) ? false : true;  			
			String responseTuple [] = { "", "", "" };			
			SequenceModel statement = (SequenceModel) it.next();
			long tStart = System.currentTimeMillis();			
			if (statement.getActionMethod().equalsIgnoreCase(REQUEST_GET)) {
				System.out.println("seq=[" + sequenceNo + "] attempting GET request to=" + statement.getRequestUrl());				
				responseTuple = handleGETRequest(statement, loadExistingCookies);
				LoadTestManager.getTestClient().incNumberOfRequests();
			} else if (statement.getActionMethod().equalsIgnoreCase(REQUEST_POST)) {
				System.out.println("seq=[" + sequenceNo + "] attempting POST request to=" + statement.getRequestUrl());
				try {
					responseTuple = handlePOSTRequest(statement, loadExistingCookies);
				} catch(Exception e) {
					e.printStackTrace();
				}
	            LoadTestManager.getTestClient().incNumberOfRequests();
			} else if (statement.getActionMethod().equalsIgnoreCase(COMMAND_SLEEP)) {
				// Delay for X number of milliseconds when a sleep command is found
				handleSleepCommand(statement);
			} else {
				throw new RuntimeException("Action method not supported=" + statement.getActionMethod());
			}
			            			
			long tEnd = System.currentTimeMillis();
			long diff = tEnd - tStart;
			
			// For data keeping, increment total requests
            LoadTestManager.getTestClient().incTotalTime(diff);
			
			System.out.println("single request time=" + diff + " ms -- from thread:" + Thread.currentThread().getName());
			LoadTestManager.log(diff, responseTuple, statement.getRequestUrl());
			
			// The following code buildRequestSection, clearRequest are needed after writing the HTML content
            LoadTestManager.getTestClient().getHtmlOutput().buildRequestSection(diff + " ms, requests=" + sequenceNo);
            LoadTestManager.getTestClient().getHtmlOutput().clearRequest();
			sequenceNo++;
		}
	}
				
	public class SequenceModel {
		private String actionMethod = "GET";
		private String requestUrl = "http://localhost";
		private Map postData = new HashMap();		
		public String getActionMethod() {
			return actionMethod;
		}

		public Map getPostData() {
			return postData;
		}

		public String getRequestUrl() {
			return requestUrl;
		}
		public void setActionMethod(String string) {
			actionMethod = string;
		}
		public void putPostData(final String key, final String val) {
			this.postData.put(key, val);
		}
		public void setRequestUrl(String string) {
			requestUrl = string;
		}
		public String toString() {
			return "SequenceModel: action=[" + actionMethod + "], url=[" + requestUrl + "], data=[" + postData + "]";
		}

	}		
}
// End of File
