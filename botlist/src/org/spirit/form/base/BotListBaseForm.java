/**
 * Berlin Brown
 * Nov 9, 2006
 */
package org.spirit.form.base;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public abstract class BotListBaseForm  implements Serializable  {
	
	private String viewName;
	private String uniqueId;
	private String userNameDisplay;
	private Calendar createdOn;
	private Calendar updatedOn;
	private String currentAction;
	private Long actionId;
	
	private Long id;

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
	public final String getViewName() {		
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public final void setViewName(String viewName) {
		this.viewName = viewName;
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
	 * @return the currentAction
	 */
	public String getCurrentAction() {
		return currentAction;
	}

	/**
	 * @param currentAction the currentAction to set
	 */
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	/**
	 * @return the actionId
	 */
	public Long getActionId() {
		return actionId;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}
}
