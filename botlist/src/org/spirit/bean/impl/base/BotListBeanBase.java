/**
 * Berlin Brown
 * Dec 25, 2006
 */
package org.spirit.bean.impl.base;

import java.util.Calendar;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public abstract class BotListBeanBase {

	private Calendar createdOn;
	private Calendar updatedOn;
	private Long id;
	
	public Calendar getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		if (createdOn == null) {
			createdOn = Calendar.getInstance();
		}
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
