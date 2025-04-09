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

public class BotListForumGroup extends BotListBeanBase 
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6720734191411418950L;
	private Long cityId;
	private String forumName;
	private Calendar createdOn;
	private String keywords;
	private String forumDescr;		

	private List topics;

	/**
	 * @return the cityId
	 */
	public Long getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
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
	 * @return the forumDescr
	 */
	public String getForumDescr() {
		return forumDescr;
	}
	/**
	 * @param forumDescr the forumDescr to set
	 */
	public void setForumDescr(String forumDescr) {
		this.forumDescr = forumDescr;
	}
	/**
	 * @return the forumName
	 */
	public String getForumName() {
		return forumName;
	}
	/**
	 * @param forumName the forumName to set
	 */
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * @return the topics
	 */
	public List getTopics() {
		return topics;
	}
	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List topics) {
		this.topics = topics;
	}
}
