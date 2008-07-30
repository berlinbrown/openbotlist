/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;

import org.spirit.bean.impl.base.BotListBeanBase;
import org.spirit.servlet.bean.BotListConcatValue;
import org.spirit.util.text.TextUtils;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public class BotListFeedItems extends BotListBeanBase 
		implements Serializable {          
	/**
	 * 
	 */
	private static final long serialVersionUID = -2504255702163561930L;
	public static final int MAX_LEN_HOSTNAME = 40;
	
	private String mainUrl;
	
	private String urlDescription;
	private String urlTitle;		
	private String urlSource;
	private Long processCount;
	private String hostname;
	private Long views;
	private Long rating;
	private String fullName;
	private Long userId;	
	private String hostnameDisplay;
	private String hostnameDisplayUrl;

	private String searchScore;

	/**
	 * @return the views
	 */
	public Long getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(Long views) {
		this.views = views;
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
	 * @return the rating
	 */
	public Long getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(Long rating) {
		this.rating = rating;
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
	/**
	 * @return the searchScore
	 */
	public String getSearchScore() {
		return searchScore;
	}
	/**
	 * @param searchScore the searchScore to set
	 */
	public void setSearchScore(String searchScore) {
		this.searchScore = searchScore;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	/**
	 * Print the hostname, use the hostname field or create the hostname display
	 * from the main URL field.
	 * 
	 * @see org.spirit.test.java.bean.EntityLinkTest
	 * 
	 * @return
	 */
	public String getHostnameDisplay() {
		this.hostnameDisplay = "";
		String urlHostname = this.getHostname();
		if ((urlHostname != null) && (urlHostname.length() > 0)) {
			this.hostnameDisplay = urlHostname;
		} else {
			this.hostnameDisplay = TextUtils.getHTTPHostname(this.getMainUrl()); 
		}
		this.hostnameDisplay = BotListConcatValue.getMaxWord(this.hostnameDisplay, new Integer(MAX_LEN_HOSTNAME));
		return this.hostnameDisplay;
	}
	public String getHostnameDisplayUrl() {
		this.hostnameDisplayUrl = "";
		String urlHostname = this.getHostname();
		if ((urlHostname != null) && (urlHostname.length() > 0)) {
			this.hostnameDisplayUrl = urlHostname;
		} else {
			this.hostnameDisplayUrl = TextUtils.getHTTPHostname(this.getMainUrl()); 
		}		
		return this.hostnameDisplayUrl;
	}
	public String getUrlSource() {
		return urlSource;
	}
	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}
	public Long getProcessCount() {
		return processCount;
	}
	public void setProcessCount(Long processCount) {
		this.processCount = processCount;
	}	

}
