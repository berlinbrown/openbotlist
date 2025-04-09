/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.Calendar;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListCoreUsersForm extends BotListBaseForm {

	private static final long serialVersionUID = 113928473079456583L;

	private Calendar lastLoginSuccess;
	private Calendar lastLoginFailure; 
	private Calendar updatedOn;	
	private Calendar dateOfBirth;
	
	private Long secretquesCode;
	private String secretAnswer;
	private String accountNumber;	
	private Long activeCode;
	private Long failedAttempts;
	
	private String userPassword;

	private Long karma;

	private String userRights;

	private Long experiencePoints;

	private String userName;

	private String userEmail;

	private String verifyPassword;
	
	private String userUrl;

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
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	 * @return the lastLoginFailure
	 */
	public Calendar getLastLoginFailure() {
		return lastLoginFailure;
	}

	/**
	 * @param lastLoginFailure the lastLoginFailure to set
	 */
	public void setLastLoginFailure(Calendar lastLoginFailure) {
		this.lastLoginFailure = lastLoginFailure;
	}

	/**
	 * @return the lastLoginSuccess
	 */
	public Calendar getLastLoginSuccess() {
		return lastLoginSuccess;
	}

	/**
	 * @param lastLoginSuccess the lastLoginSuccess to set
	 */
	public void setLastLoginSuccess(Calendar lastLoginSuccess) {
		this.lastLoginSuccess = lastLoginSuccess;
	}

	/**
	 * @return the secretAnswer
	 */
	public String getSecretAnswer() {
		return secretAnswer;
	}

	/**
	 * @param secretAnswer the secretAnswer to set
	 */
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	/**
	 * @return the secretquesCode
	 */
	public Long getSecretquesCode() {
		return secretquesCode;
	}

	/**
	 * @param secretquesCode the secretquesCode to set
	 */
	public void setSecretquesCode(Long secretquesCode) {
		this.secretquesCode = secretquesCode;
	}

	/**
	 * @return the updatedOn
	 */
	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
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
	 * @return the verifyPassword
	 */
	public String getVerifyPassword() {
		return verifyPassword;
	}

	/**
	 * @param verifyPassword the verifyPassword to set
	 */
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

}
