/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 14, 2007
 */
package org.spirit.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.bean.impl.BotListProfileSettings;
import org.spirit.dao.BotListProfileSettingsDAO;
import org.spirit.util.BotListSessionManager;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListContractManager {
	
	public static BotListCoreUsersContract getUserInfo(HttpServletRequest request) {				
		HttpSession cur_session = request.getSession(false);
		if (cur_session == null) {
			cur_session = request.getSession(true);
		}		
		BotListCoreUsersContract userInfo = (BotListCoreUsersContract) cur_session.getAttribute(BotListSessionManager.USER_INFO_OBJECT);
		if (userInfo == null) {
			// If a user info object is not found, the user
			// is logged out.
			return null;
		}
		return userInfo;
	}
	
	/**
	 * Create the user contract object based on information from the core user and
	 * profile settings objects.
	 * 
	 * Note: A similar utility exists in the ruby modules, 'core_users.rb'
	 * @return
	 */
	public static BotListCoreUsersContract createUserContract(BotListProfileSettingsDAO dao_profile, BotListCoreUsers user_bean) { 
		// Create the user contract and store in session
		BotListCoreUsersContract user = new BotListCoreUsersContract();
		user.setUserName(user_bean.getUserName());
		user.setUserEmail(user_bean.getUserEmail());
		user.setDateOfBirth(user_bean.getDateOfBirth());
		user.setCreatedOn(user_bean.getCreatedOn());
		user.setAccountNumber(user_bean.getAccountNumber());
		user.setId(user_bean.getId());
      
		// Also, load the user profile
		BotListProfileSettings profile = dao_profile.readProfile(user.getId().intValue()); 
		user.setLinkColor(profile.getLinkColor());
		user.setProfileId(profile.getId());
		return user;
	}
	
}
