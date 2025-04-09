/**
 * Berlin Brown
 * Dec 25, 2006
 */
package org.spirit.bean.impl.base;

import java.io.Serializable;
import java.util.Calendar;

import org.spirit.servlet.bean.BotListConcatValue;
import org.spirit.util.BotListUniqueId;
import org.spirit.util.text.TextUtils;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public abstract class BotListEntity extends BotListBeanBase 
		implements Serializable { 
	
	public static final int MAX_LEN_HOSTNAME = 40;
	
    /**
     * <property name="generatedUniqueId" column="generated_obj_id" not-null="false" />
     */    
    private String generatedUniqueId;
    
	private Calendar createdOn;
	private Calendar updatedOn;
	private Long id;	
	private String mainUrl;
	private String keywords;
	private String urlDescription;
	private String urlTitle;	
	
	private String hostname;
	private String hostnameDisplay;
	private String hostnameDisplayUrl;
		
	public String getGeneratedUniqueId() {
		return generatedUniqueId;
	}
	/**
	 * Automatically generate the unique id.
	 * @return
	 */
	public String getGeneratedUniqueIdAuto() {
		this.generatedUniqueId = BotListUniqueId.getUniqueId();
		return this.generatedUniqueId;
	}
	public void setGeneratedUniqueId(String generatedUniqueId) {
		this.generatedUniqueId = generatedUniqueId;
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
	
}
