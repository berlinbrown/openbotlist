/**
 * Berlin Brown
 * Dec 27, 2006
 */
package org.spirit.servlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.spirit.util.BotListCookieManager;
import org.spirit.util.BotListUniqueId;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListHomeFilter implements Filter {

	private FilterConfig config;
	
	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() { 
		this.config = null;
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request_, 
					ServletResponse response_, FilterChain chain) 
			throws IOException, ServletException {
		
		// Transform
		HttpServletRequest request = (HttpServletRequest) request_;
		HttpServletResponse response = (HttpServletResponse) response_;
		
		HttpSession session = request.getSession(false);
		if (session == null)
			session = request.getSession(true);
		
		//String cookieUserId = BotListCookieManager.getCookieParam(request, BotListCookieManager.USER_ID);
		//
		// If the user id cookie does not exist for this user,
		// set the cookie value.
		//if (cookieUserId == null) {
		/*
			String newUserId = "anon" + BotListUniqueId.getUniqueId();			
			Cookie cookie = new Cookie (BotListCookieManager.USER_ID, newUserId);
			cookieUserId = newUserId;			
			boolean cookieSecureFlag = false;
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath("/");
			cookie.setSecure(cookieSecureFlag);
			response.addCookie(cookie);
		*/			 
		//}			
		chain.doFilter(request, response);
		
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
