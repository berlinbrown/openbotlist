/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListProfileSettingsForm extends BotListBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2518028926034291261L;
	
	private String linkColor;
	private Long userId;
	
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
