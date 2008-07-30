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

public class BotListUserComments extends BotListBeanBase 
	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6382166190547017958L;

	private String mainUrl;
	private String zipcode;
	private Long linkId;
	private String email;
	private Calendar createdOn;
	private Long adlistId;
	private Long forumId;
	private String fullName;
	private String keywords;	
	private String subject;
	private String message;
	private Long commentId;
	private String generatedId;
	private List childComments;
	
	private BotListEntityLinks entityLink;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the adlistId
	 */
	public Long getAdlistId() {
		return adlistId;
	}

	/**
	 * @param adlistId the adlistId to set
	 */
	public void setAdlistId(Long adlistId) {
		this.adlistId = adlistId;
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return the linkId
	 */
	public Long getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return the mainUrl
	 */
	public String getMainUrl() {
		return mainUrl;
	}

	/**
	 * @param mainUrl the mainUrl to set
	 */
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the forumId
	 */
	public Long getForumId() {
		return forumId;
	}

	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * @return the commentId
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the childComments
	 */
	public List getChildComments() {
		return childComments;
	}

	/**
	 * @param childComments the childComments to set
	 */
	public void setChildComments(List childComments) {
		this.childComments = childComments;
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

	public BotListEntityLinks getEntityLink() {
		return entityLink;
	}

	public void setEntityLink(BotListEntityLinks entityLink) {
		this.entityLink = entityLink;
	}
}
