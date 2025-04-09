/**
 * Berlin Brown
 * Nov 9, 2006
 */
package org.spirit.form;

import java.util.Calendar;

import org.spirit.bean.impl.BotListCityListing;
import org.spirit.bean.impl.BotListPostSections;
import org.spirit.form.base.BotListBaseCalcVerify;
import org.spirit.form.base.BotListBaseForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public class BotListPostListingForm 
		extends BotListBaseForm implements BotListBaseCalcVerify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1694670013390019141L;
		
	private String email;
	private String location;
	private String title;
	private String message;
	private Long age;
	
	private Calendar createdOn;
	
	private Long firstInput;
	private Long secondInput;
	private Long solution;
	private Long userSolution;
	private Long prevSolution;
	
	private String mainUrl;
	private String keywords;
	
	private BotListCityListing cityListing;
	private BotListPostSections postSection;
	
	private MultipartFile uploadFilenameFirst;
	private MultipartFile uploadFilenameSecond;
	

	/**
	 * @return the cityListing
	 */
	public BotListCityListing getCityListing() {
		return cityListing;
	}

	/**
	 * @param cityListing the cityListing to set
	 */
	public void setCityListing(BotListCityListing cityListing) {
		this.cityListing = cityListing;
	}

	/**
	 * @return the postSection
	 */
	public BotListPostSections getPostSection() {
		return postSection;
	}

	/**
	 * @param postSection the postSection to set
	 */
	public void setPostSection(BotListPostSections postSection) {
		this.postSection = postSection;
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
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the uploadFilenameFirst
	 */
	public MultipartFile getUploadFilenameFirst() {
		return uploadFilenameFirst;
	}

	/**
	 * @param uploadFilenameFirst the uploadFilenameFirst to set
	 */
	public void setUploadFilenameFirst(MultipartFile uploadFilenameFirst) {
		this.uploadFilenameFirst = uploadFilenameFirst;
	}

	/**
	 * @return the uploadFilenameSecond
	 */
	public MultipartFile getUploadFilenameSecond() {
		return uploadFilenameSecond;
	}

	/**
	 * @param uploadFilenameSecond the uploadFilenameSecond to set
	 */
	public void setUploadFilenameSecond(MultipartFile uploadFilenameSecond) {
		this.uploadFilenameSecond = uploadFilenameSecond;
	}


}
