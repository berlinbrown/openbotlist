/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.apps.system;

import java.io.Serializable;

import org.spirit.bean.impl.base.BotListEntity;

/**
 * This is class is used by botverse.
 * 
 * Fields included from the Entity base class.
 * -------------------------- 
 *  String generatedUniqueId;    
 *	Calendar createdOn;
 *	Calendar updatedOn;
 *	Long id;	
 *	String mainUrl;
 *	String keywords;
 *	String urlDescription;
 *	String urlTitle;	
 *
 * @author Berlin Brown
 * 
 */
public class SystemFeedItemsType extends BotListEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800491702537719907L;	                            	        
	
	private String urlSource;
	private String enumProcType;
	
	private final String defaultQuerySQL = "insert into system_feed_items (main_url,url_title,url_description,url_source,created_on,hostname) values(?,?,?,?,?,?)";

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("#SystemFeedItems");
		return buf.toString();
	}

	public String getUrlSource() {
		return urlSource;
	}

	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}

	public String getEnumProcType() {
		return enumProcType;
	}

	public void setEnumProcType(String enumProcType) {
		this.enumProcType = enumProcType;
	}

	public String getDefaultQuerySQL() {
		return defaultQuerySQL;
	}

}
