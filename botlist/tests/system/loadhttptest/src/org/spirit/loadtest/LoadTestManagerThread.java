/*-----------------------------------------------
 * Berlin Brown
 * Created on Apr 13, 2007
 * LoadTestManagerThread.java
 *-----------------------------------------------
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
package org.spirit.loadtest;

/**
 * Thread launched to send out HTTP requests and if enabled, write response and status
 * information to file.
 */
public class LoadTestManagerThread implements Runnable {
    
    private LoadTestManager client;

    public LoadTestManagerThread(LoadTestManager client) {
        this.client = client;
    }
    public LoadTestManager getTestClient() {
        return this.client;
    }

    /**
     * Add additional message content to result tuple.
     * Length = 4
     * 4/18/2008
     */
    private String [] additionalHeadersResTuple(final String [] responseTuple, final String additional_msg, final boolean valid_xhtml) {
    	final String [] new_copy = new String [LoadTestManager.MAX_LOG_RESULT_TUPLE];
    	final Object obj_src = responseTuple;
    	final Object obj_dest = new_copy;
    	// Only copy 3 elements
    	System.arraycopy(obj_src, 0, obj_dest, 0, 3);
    	new_copy[3] = additional_msg;    	
    	new_copy[4] = "" + valid_xhtml;
    	return new_copy;
    }
    private void loadSingleURL(String url) {
        try {
            final long allStart = System.currentTimeMillis();
            for (int i = 0; i < getTestClient().getLinesWrite(); i++) {
                final long tStart = System.currentTimeMillis();
                System.out.println("attempting request to=" + url);
                String [] responseTuple = LoadTestManager.connectURL(url, false);                
                final String http_data = responseTuple[1];
                String additional_msg = "";
                boolean is_valid = false;
                if (this.getTestClient().isValidateXHTMLEnabled()) {
                	 final Object [] validate_res = LoadTestXMLValidate.validateXML(url, http_data);
                	 is_valid = ((Boolean) validate_res[0]).booleanValue();
                	 if (!is_valid) {
                		 // Append the XML validate text to the response tuple, for printing to the HTML document.
                		 additional_msg = (String) validate_res[1]; 
                	 }
                }
                responseTuple = additionalHeadersResTuple(responseTuple, additional_msg, is_valid);
                long tEnd = System.currentTimeMillis();
                long diff = tEnd - tStart;
                System.out.println("single request time=" + diff + " ms -- from " + Thread.currentThread().getName());
                //*********************
                // Log the file to the simple text document, also save for HTML output
                //*********************
                LoadTestManager.log(diff, responseTuple, url);
                // Move to next iteration.
                this.getTestClient().incNumberOfRequests();
                this.getTestClient().incTotalTime(diff);
                Thread.sleep(getTestClient().getThreadSleepTime());
            }
                        
            final long allEnd = System.currentTimeMillis();
            final long perThreadDiff = allEnd - allStart;
            System.out.println("All requests time=" + perThreadDiff + " ms");
            
            // The following code buildRequestSection, clearRequest are needed after writing the HTML content
            this.getTestClient().getHtmlOutput().buildRequestSection(perThreadDiff + " ms, requests=" + getTestClient().getLinesWrite());
            this.getTestClient().getHtmlOutput().clearRequest();            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadSequenceFile(final String script) {
        try {
            System.out.println("INFO: loading sequence script=" + script);
            LoadTestSequenceParser parser = new LoadTestSequenceParser();
            String realFilename = script.substring("script://".length());
        	parser.parse(realFilename);
        	parser.printSummary();
        	parser.handleSequence();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        if (this.getTestClient().isUseDataFile()) {
            Object [] data = LoadTestManager.loadDataFile(this.getTestClient().getDataFile());
            for (int i = 0; i < data.length; i++) {
                String url = (String) data[i];        		
                if (url.startsWith("script://")) {
                	loadSequenceFile(url);
                } else {
                	loadSingleURL(url);
                }
                	
            }
        } else {
            loadSingleURL(getTestClient().getTestURL());
        }
    }
}
// End of File