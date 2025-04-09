/**
 * Berlin Brown
 * Dec 27, 2006
 */
package org.spirit.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.contract.BotListContractManager;
import org.spirit.contract.BotListCoreUsersContract;
import org.spirit.dao.BotListCoreUsersDAO;
import org.spirit.dao.BotListProfileSettingsDAO;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 */
public class BotListCookieManager {
	
	/**
	 * Also see "CoreUsers.marshallData" in the "users.rb" source.
	 */
	public final static String COOKIE_REMEMBER_ME = "botlist_remember_me";
	public final static String COOKIE_LOGIN_ID = "botlist_lgn_id";
	public final static String COOKIE_USER_NAME = "botlist_lgn_name";
	
	public final static String getCookieParam(HttpServletRequest request, String cookieKey) {		
		String cookieVal = null;
		Cookie cookies[] = request.getCookies();		
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieKey)) {
				cookieVal = cookies[i].getValue();
				break;
			}
		}
		return cookieVal;
	}
	
	/**
	 * Find a cookie based on a key.
	 * @param request
	 * @param cookieKey
	 * @return
	 */
	public final static Cookie getCookie(HttpServletRequest request, String cookieKey) {			
		Cookie cookies[] = request.getCookies();		
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieKey)) {
				return cookies[i];				
			}
		}
		return null;
	}
	
	/**
	 * Find a cookie based on a key and set the max age to zero.
	 * 
	 * @param request
	 * @param cookieKey
	 * @return
	 */
	public final static boolean deleteCookieParam(HttpServletRequest request, HttpServletResponse response, String cookieKey) {
		Cookie cookie = getCookie(request, cookieKey);
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);			
			return true;
		}
		return false;
	}
	
	public final static void setCookieParam(HttpServletResponse response, String key, String val) {
		if (val != null) {
			Cookie cookie = new Cookie(key, val);		
			boolean cookieSecureFlag = false;
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath("/");
			cookie.setSecure(cookieSecureFlag);
			response.addCookie(cookie);
		}
	}
	
	/**
	 * Write some user object info to cookie values.
	 */
	public final static void setRememberMeCookieParams(HttpServletResponse response, BotListCoreUsersContract userContract) {
		if ((userContract == null) || (response == null))
			return;
		
		String acctNumber = userContract.getAccountNumber();
		String userName = userContract.getUserName();
		String rememberMe = "" + userContract.isRememberMe();
		
		setCookieParam(response, COOKIE_LOGIN_ID, acctNumber);
		setCookieParam(response, COOKIE_USER_NAME, userName);
		setCookieParam(response, COOKIE_REMEMBER_ME, rememberMe);
	}
	
	public final static void deleteRememberMeCookieParams(HttpServletRequest request, HttpServletResponse response) {
		if ((request == null) || (response == null))
			return;
				
		deleteCookieParam(request, response, COOKIE_LOGIN_ID);
		deleteCookieParam(request, response, COOKIE_USER_NAME);
		deleteCookieParam(request, response, COOKIE_REMEMBER_ME);
	}

	
	/**
	 * Access the system cookie user contract settings.  If the cookie
	 * values are available, set the session user contract.
	 */
	public final static void systemGetUserCookieParams(HttpServletRequest request, final BotListCoreUsersDAO daoUser, 
					final BotListProfileSettingsDAO daoProfile) {
		String acctNumber = getCookieParam(request, COOKIE_LOGIN_ID);
		String userName = getCookieParam(request, COOKIE_USER_NAME);
		String rememberMe = getCookieParam(request, COOKIE_REMEMBER_ME);
		boolean validValues = true;
		if ((acctNumber == null) || (userName == null) || (rememberMe == null)) {
			validValues = false;
		}
		if (validValues) {
			// If the user is already logged in, no need to reset user session value 
			boolean isLoggedIn = BotListSessionManager.isUserLoggedIn(request);
			if (isLoggedIn) return; 
					
			// Attempt to get the user contract object
			BotListCoreUsers coreUser = daoUser.readUserAcctVerify(userName, acctNumber);
			if (coreUser != null) {
				BotListCoreUsersContract userContract = BotListContractManager.createUserContract(daoProfile, coreUser);
				if (userContract != null) {
					 // Set the session object
					 HttpSession session = BotListSessionManager.safeCreateSession(request);
					 if (session != null) {
						 // Note: this value will be overridden by "login" or "register"
						 session.setAttribute(BotListSessionManager.USER_INFO_OBJECT, userContract);						 						
					 }
				} // End if - user contract check 				 
			}
		} // End of if - valid value
	}	
}
