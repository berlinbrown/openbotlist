/**
 * Berlin Brown
 * Nov 17, 2006
 */
package org.spirit.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.contract.BotListCoreUsersContract;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListSessionManager {

	public final static String CURRENT_CITY_SETTING = "session.city.setting";
	public final static String CURRENT_SECTION_SETTING = "session.section.setting";
	
	public final static String CURRENT_SEARCH_TERM = "session.search.term";	
	public final static String CURRENT_LINK_VIEW = "session.link.view";
	public final static String CURRENT_FORUM = "session.current.forum";
	public final static String CURRENT_COMMENT_REPLY = "session.comment.reply";
	
	public final static String CALC_VERIFY_OBJECT = "session.calc.verify";
	
	public final static String POST_UNIQUE_ID = "session.unique.id";
			
	/**
	 * @see BotListCoreUsers
	 */
	public final static String USER_INFO_OBJECT = "session.coreuser"; 
	
	/**
	 * Safe create a new session.  This method will help manage where
	 * sessions are created.
	 * 
	 * (same logic as: #request.getSession() )
	 */
	public static HttpSession safeCreateSession(HttpServletRequest request) {
		// Only return new session when there is no existing session.
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession(true);
		}
		return session;
		
	}
	
	/**
	 * Get the session even if it is empty.
	 * 
	 * (same logic as: #request.getSession(false)
	 */
	public static HttpSession safeCheckSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session;
	}
	
	/**
	 * Java, is user logged in.
	 */
	public static boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = safeCheckSession(request);
		if (session != null) {
			BotListCoreUsersContract userContract = (BotListCoreUsersContract) session.getAttribute(USER_INFO_OBJECT);
			if (userContract != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Java, if user is logged in, return the user id.  Return null if 
	 * the user is not logged in.
	 */
	public static Long getIdUserLoggedIn(HttpServletRequest request) {
		boolean loggedIn = isUserLoggedIn(request);
		if (loggedIn) {
			HttpSession session = safeCheckSession(request);
			BotListCoreUsersContract userContract = (BotListCoreUsersContract) session.getAttribute(USER_INFO_OBJECT);
			return userContract.getId();			
		}
		return null;
	}
	
}
