/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 24, 2007
 */
package org.spirit.form.ext;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListMapEntityLink 
	extends HashMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1902859165498400016L;
	
	private String viewName;
	private String uniqueId;
	private String userNameDisplay;
	private Calendar createdOn;
	
	private String operation;
	private String ratingId;
	
	/**
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	/**
	 * @return the userNameDisplay
	 */
	public String getUserNameDisplay() {
		return userNameDisplay;
	}
	/**
	 * @param userNameDisplay the userNameDisplay to set
	 */
	public void setUserNameDisplay(String userNameDisplay) {
		this.userNameDisplay = userNameDisplay;
	}
	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}
	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return the ratingId
	 */
	public String getRatingId() {
		return ratingId;
	}
	/**
	 * @param ratingId the ratingId to set
	 */
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	
}
