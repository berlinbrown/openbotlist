/* 
 *** Notice Update: 8/14/2007
 *** Copyright 2007 Berlin Brown
 *** Copyright 2006-2007 Newspiritcompany.com
 *** 
 *** This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
 *** otherwise stated, use or distribution of this program 
 *** for commercial purpose is prohibited.
 *** 
 *** See LICENSE.BOTLIST for more information.
 ***
 *** The SOFTWARE PRODUCT and CODE are protected by copyright and 
 *** other intellectual property laws and treaties. 
 ***  
 *** Unless required by applicable law or agreed to in writing, software
 *** distributed  under the  License is distributed on an "AS IS" BASIS,
 *** WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 *** implied.
 * BotListUserSessionListener.java
 * Aug 15, 2007
 */
package org.spirit.spring;

/**
 * @author bbrown
 */
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.spirit.dao.BotListCoreSettings;
import org.springframework.context.ApplicationContext;

/**
 * When user sessions are created or destroyed, keep track of who is logged in
 * as well as other information on the total of active sessions.
 *  
 * See the web.xml for web application configuration.
 * 
 * @author bbrown
 */
public class BotListUserSessionListener implements HttpSessionListener {

	private final static String BEAN_CORE_SETTINGS = "coreSettings";
	public final static String CONTEXT_BOTLIST = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.botlistings";

	public void sessionCreated(HttpSessionEvent event) {
		final BotListCoreSettings coreSettings = getCoreSettings(event);			
		if (coreSettings != null) {
			coreSettings.incActiveSessions();
			coreSettings.incLoggedInUsers(event.getSession());			
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		final BotListCoreSettings coreSettings = getCoreSettings(event);
		if (coreSettings != null) {
			coreSettings.decActiveSessions();		
			coreSettings.decLoggedInUsers(event.getSession());
		}
	}

	private BotListCoreSettings getCoreSettings(HttpSessionEvent event) {
		final ApplicationContext wac = getWebApplicationContext(event);			
		return (BotListCoreSettings) wac.getBean(BEAN_CORE_SETTINGS);		
	}
	
	private ApplicationContext getWebApplicationContext(HttpSessionEvent httpSessionEvent) {		
		final HttpSession session = httpSessionEvent.getSession();
		final ServletContext sc = session.getServletContext();			
		return (ApplicationContext) sc.getAttribute(CONTEXT_BOTLIST);				
	}
	
}
