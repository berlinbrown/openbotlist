/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Jan 12, 2007
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
package org.spirit.spring.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The generic handler allows you to use any URI wildcard.
 * 
 * @author Berlin Brown
 *
 */
public class BotListGenericHandler {

	private String springServletContext = "";
	
	public BotListGenericHandler(String springContext) {
		this.springServletContext = springContext;
	}
	
	/**
	 * Get JSP Path Pos.
	 * i.e. extract 'mypath/file.html' from '/webapp/b/mypath/file.html'
	 */
	private int getJspPathPos(String uri, String contextPath) {
		
		// Example, /webapp/spring
		String target = contextPath + "/" + this.getSpringServletContext();		
		int len = target.length();
		return uri.startsWith(target) ? len : -1;		
	}
	
	/**
	 * This function is quite similar to the Jobster internal PathToViewController.
	 * Duplicated here so that the RAD module can be self contained.
	 */
	public String getViewNameFromServletPath(String servletPath, String uri, String contextPath) {

		String viewName = servletPath;
		int beg = 0, end = viewName.length();		
		if (end > 0 && viewName.charAt(0) == '/') {
			beg = 1;
		} else {
			// We are now assuming incoming servletPath is actually a full URL
			// beg = servletPath.lastIndexOf('/');
			if (getJspPathPos(uri, contextPath) != -1) {
				beg = getJspPathPos(uri, contextPath);
			}
		}
		
		int dot = viewName.lastIndexOf('.');
		if (dot >= 0) {
			end = dot;
		}
		viewName = viewName.substring(beg, end);		
		return viewName;

	}
	
	public String getDefaultViewNameFromRequest(HttpServletRequest request) {	
		return getViewNameFromServletPath(request.getRequestURI().substring(1),
				request.getRequestURI(), request.getContextPath());
	}

	/**
	 * @return the springServletContext
	 */
	public String getSpringServletContext() {
		return springServletContext;
	}

	/**
	 * @param springServletContext the springServletContext to set
	 */
	public void setSpringServletContext(String springServletContext) {
		this.springServletContext = springServletContext;
	}
	
	
}
