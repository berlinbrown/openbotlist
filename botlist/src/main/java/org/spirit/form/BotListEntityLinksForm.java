/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.Calendar;

import org.spirit.form.base.BotListBaseCalcVerify;
import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListEntityLinksForm extends BotListBaseForm implements
		BotListBaseCalcVerify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8658902729040659129L;

	private String mainUrl;	

	private Calendar createdOn;

	private String keywords;

	private String urlDescription;

	private String urlTitle;

	private Long firstInput;

	private Long secondInput;

	private Long solution;

	private Long userSolution;

	private Long prevSolution;

	private String roverVerify;
	
	private String generatedId;
	
	private String fullName;
	
	private boolean isGroupLink;
	
	public String getRoverVerify() {
		return roverVerify;
	}

	public void setRoverVerify(String roverVerify) {
		this.roverVerify = roverVerify;
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

	public Calendar getCreatedOn() {
		if (createdOn == null) {
			createdOn = Calendar.getInstance();
		}
		return createdOn;
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
	 * @return the urlDescription
	 */
	public String getUrlDescription() {
		return urlDescription;
	}

	/**
	 * @param urlDescription the urlDescription to set
	 */
	public void setUrlDescription(String urlDescription) {
		this.urlDescription = urlDescription;
	}

	/**
	 * @return the urlTitle
	 */
	public String getUrlTitle() {
		return urlTitle;
	}

	/**
	 * @param urlTitle the urlTitle to set
	 */
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
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
	 * @return the isGroupLink
	 */
	public boolean isGroupLink() {
		return isGroupLink;
	}

	/**
	 * @param isGroupLink the isGroupLink to set
	 */
	public void setGroupLink(boolean isGroupLink) {
		this.isGroupLink = isGroupLink;
	}

}
