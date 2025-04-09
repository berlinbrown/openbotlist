/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListProfileSettings extends BotListBeanBase 
			implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2609558699187914908L;
	
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
