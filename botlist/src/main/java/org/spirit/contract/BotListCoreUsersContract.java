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
 */
package org.spirit.contract;

import java.util.Calendar;

import org.spirit.form.base.BotListBaseForm;

/**
 * The user contract manager contains settings specific to a particular user.
 * This user session object also is an indicator on if the user is logged in or not.
 * 
 * See the session key, BotListSessionManager.USER_INFO_OBJECT (session.coreuser) for access.
 * Also, see "web/users.rb"
 * 
 * @author Berlin Brown
 */
public class BotListCoreUsersContract extends BotListBaseForm {

	private static final long serialVersionUID = 113928473079456583L;

	private Calendar dateOfBirth;	
	private String accountNumber;	
	private Long activeCode;
	private Long failedAttempts;	
	private Long karma;
	private String userRights;
	private Long experiencePoints;
	private String userName;
	private String userEmail;	
	private String location;	
	private String userUrl;	
	private String linkColor;
	private Long profileId;
	private boolean rememberMe = true;
			

	/**
	 * @return the experiencePoints
	 */
	public Long getExperiencePoints() {
		return experiencePoints;
	}

	/**
	 * @param experiencePoints the experiencePoints to set
	 */
	public void setExperiencePoints(Long experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	/**
	 * @return the karma
	 */
	public Long getKarma() {
		return karma;
	}

	/**
	 * @param karma the karma to set
	 */
	public void setKarma(Long karma) {
		this.karma = karma;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userRights
	 */
	public String getUserRights() {
		return userRights;
	}

	/**
	 * @param userRights the userRights to set
	 */
	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the activeCode
	 */
	public Long getActiveCode() {
		return activeCode;
	}

	/**
	 * @param activeCode the activeCode to set
	 */
	public void setActiveCode(Long activeCode) {
		this.activeCode = activeCode;
	}

	/**
	 * @return the failedAttempts
	 */
	public Long getFailedAttempts() {
		return failedAttempts;
	}

	/**
	 * @param failedAttempts the failedAttempts to set
	 */
	public void setFailedAttempts(Long failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	
	/**
	 * @return the dateOfBirth
	 */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the userUrl
	 */
	public String getUserUrl() {
		return userUrl;
	}

	/**
	 * @param userUrl the userUrl to set
	 */
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	/**
	 * @return the linkColor
	 */
	public String getLinkColor() {
		return linkColor;
	}

	/**
	 * @param linkColor the linkColor to set
	 */
	public void setLinkColor(String linkColor) {
		this.linkColor = linkColor;
	}

	/**
	 * @return the profileId
	 */
	public Long getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
