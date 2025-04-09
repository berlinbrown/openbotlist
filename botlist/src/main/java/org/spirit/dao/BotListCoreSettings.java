/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * May 13, 2007
 */
package org.spirit.dao;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.spirit.contract.BotListCoreUsersContract;
import org.spirit.util.BotListSessionManager;

/**
 * The core settings bean contains general setting variables that can
 * be accessed by any module.  For example, the search index directory
 * locations are stored in this bean.  Other utilities include session
 * tracking.
 * 
 * @author Berlin Brown 
 */
public class BotListCoreSettings implements Serializable {

	private static final long serialVersionUID = 125243403721134912L;

	private String searchIndexDir;
	private String globalIndexDir;

	private boolean mediaEnabled = false;

	private int activeSessions = 0;
	private int loggedInUsers = 0;

	/**
	 * @return the searchIndexDir
	 */
	public String getSearchIndexDir() {
		return searchIndexDir;
	}

	/**
	 * @param searchIndexDir the searchIndexDir to set
	 */
	public void setSearchIndexDir(String searchIndexDir) {
		this.searchIndexDir = searchIndexDir;
	}

	/**
	 * @return the globalIndexDir
	 */
	public String getGlobalIndexDir() {
		return globalIndexDir;
	}

	/**
	 * @param globalIndexDir the globalIndexDir to set
	 */
	public void setGlobalIndexDir(String globalIndexDir) {
		this.globalIndexDir = globalIndexDir;
	}

	public boolean isMediaEnabled() {
		return mediaEnabled;
	}

	public void setMediaEnabled(boolean mediaEnabled) {
		this.mediaEnabled = mediaEnabled;
	}

	public int getActiveSessions() {
		return activeSessions;
	}

	public void incActiveSessions() {
		this.activeSessions++;
	}
	public void decActiveSessions() {		
		if (this.activeSessions > 0)
			this.activeSessions--;
	}

	public int getLoggedInUsers() {
		return loggedInUsers;
	}

	/** 
	 * Increase the count of how many users are logged in based on if the
	 * user management object is available.
	 * 
	 * @param session
	 */
	public void incLoggedInUsers(final HttpSession session) {
		if (session != null) {
			BotListCoreUsersContract coreUser = (BotListCoreUsersContract) session.getAttribute(BotListSessionManager.USER_INFO_OBJECT);
			if (coreUser != null) {
				this.loggedInUsers++;
			}
		} // End of if - check session null
	}
	/** 
	 * Decrease the count of how many users are logged in based on if the
	 * user management object is available.
	 * 
	 * @param session
	 */
	public void decLoggedInUsers(final HttpSession session) {
		if (session != null) {
			BotListCoreUsersContract coreUser = (BotListCoreUsersContract) session.getAttribute(BotListSessionManager.USER_INFO_OBJECT);
			if (coreUser != null) {
				if (this.loggedInUsers > 0)
					this.loggedInUsers--;
			}
		} // End of if - check session null
	}
}
