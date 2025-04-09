/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.Calendar;

import org.spirit.bean.impl.BotListUserComments;
import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public class BotListUserCommentsForm extends BotListBaseForm {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3369285765294416417L;
	
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
	private Long firstInput;
	private Long secondInput;
	private Long solution;
	private Long userSolution;
	private Long prevSolution;
	private String roverVerify;
	private BotListUserComments userComment;
	
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
	 * @return the firstInput
	 */
	public Long getFirstInput() {
		return firstInput;
	}
	/**
	 * @param firstInput the firstInput to set
	 */
	public void setFirstInput(Long firstInput) {
		this.firstInput = firstInput;
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
	 * @return the prevSolution
	 */
	public Long getPrevSolution() {
		return prevSolution;
	}
	/**
	 * @param prevSolution the prevSolution to set
	 */
	public void setPrevSolution(Long prevSolution) {
		this.prevSolution = prevSolution;
	}
	/**
	 * @return the secondInput
	 */
	public Long getSecondInput() {
		return secondInput;
	}
	/**
	 * @param secondInput the secondInput to set
	 */
	public void setSecondInput(Long secondInput) {
		this.secondInput = secondInput;
	}
	/**
	 * @return the solution
	 */
	public Long getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(Long solution) {
		this.solution = solution;
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
	 * @return the userSolution
	 */
	public Long getUserSolution() {
		return userSolution;
	}
	/**
	 * @param userSolution the userSolution to set
	 */
	public void setUserSolution(Long userSolution) {
		this.userSolution = userSolution;
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
	 * @return the roverVerify
	 */
	public String getRoverVerify() {
		return roverVerify;
	}
	/**
	 * @param roverVerify the roverVerify to set
	 */
	public void setRoverVerify(String roverVerify) {
		this.roverVerify = roverVerify;
	}
	/**
	 * @return the userComment
	 */
	public BotListUserComments getUserComment() {
		return userComment;
	}
	/**
	 * @param userComment the userComment to set
	 */
	public void setUserComment(BotListUserComments userComment) {
		this.userComment = userComment;
	}

}
