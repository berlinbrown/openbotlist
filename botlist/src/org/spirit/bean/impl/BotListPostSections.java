/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListPostSections extends BotListBeanBase 
	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 329718294700098898L;
	private String generatedId;
	private Calendar createdOn;		
	private String sectionName;
	private List listings;
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
	 * @return the generatedId
	 */
	public String getGeneratedId() {
		return generatedId;
	}
	/**
	 * @param generatedId the generatedId to set
	 */
	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}

	/**
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}
	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public List getListings() {
		return listings;
	}
	public void setListings(List listings) {
		this.listings = listings;
	}
}
