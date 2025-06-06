/* 
 * Created on Sep 26, 2006
 * 
 */
package org.spirit.bean.impl;

import java.util.Calendar;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This class is deprecated, @see BotListEntityLinks.
 */
public class BotListUserLink extends BotListBeanBase 
implements java.io.Serializable {

	private String source;
	private String mainUrl;
	private Calendar createdOn;
	private String description;
	private String keywords;

	private static final long serialVersionUID = 3487495895819393L;

	/**
	 * @return
	 */
	public String getMainUrl() {
		return mainUrl;
	}

	/**
	 * @return
	 */
	public String getSource() {
		return source;
	}	

	/**
	 * @param string
	 */
	public void setMainUrl(String string) {
		mainUrl = string;
	}

	/**
	 * @param string
	 */
	public void setSource(String string) {
		source = string;
	}

	/**
	 * @return
	 */
	public Calendar getCreatedOn() {
		if (createdOn == null) {
			createdOn = Calendar.getInstance();
		}
		return createdOn;
	}

	/**
	 * @param calendar
	 */
	public void setCreatedOn(Calendar calendar) {
		createdOn = calendar;
	}

	/**
	 * @return
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setKeywords(String string) {
		keywords = string;
	}

}
