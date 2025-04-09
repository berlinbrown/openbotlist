/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 19, 2007
 */
package org.spirit.cache;

import java.io.Serializable;
import java.util.Date;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListCacheEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3472432421060313785L;

	private String filenameKey = "";
	private String rubyCodeData = "";
	private Date timeAddedCache = new Date();
	
	private Date lastModified;
	
	/**
	 * @return the filenameKey
	 */
	public String getFilenameKey() {
		return filenameKey;
	}
	/**
	 * @param filenameKey the filenameKey to set
	 */
	public void setFilenameKey(String filenameKey) {
		this.filenameKey = filenameKey;
	}
	/**
	 * @return the rubyCodeData
	 */
	public String getRubyCodeData() {
		return rubyCodeData;
	}
	/**
	 * @param rubyCodeData the rubyCodeData to set
	 */
	public void setRubyCodeData(String rubyCodeData) {
		this.rubyCodeData = rubyCodeData;
	}
	
	public String toString() {
		return "CacheEntity: filename-key=" + this.filenameKey;
	}
	/**
	 * @return the timeAddedCache
	 */
	public Date getTimeAddedCache() {
		return timeAddedCache;
	}
	/**
	 * @param timeAddedCache the timeAddedCache to set
	 */
	public void setTimeAddedCache(Date timeAddedCache) {
		this.timeAddedCache = timeAddedCache;
	}
	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
}
