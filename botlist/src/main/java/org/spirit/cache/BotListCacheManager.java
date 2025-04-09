/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 19, 2007
 */
package org.spirit.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListCacheManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2429448168123373618L;
	
	private boolean enableCaching = false;
	private Map cacheStore = new HashMap();

	/**
	 * @return the enableCaching
	 */
	public boolean isEnableCaching() {
		return enableCaching;
	}

	/**
	 * @param enableCaching the enableCaching to set
	 */
	public void setEnableCaching(boolean enableCaching) {
		this.enableCaching = enableCaching;
	}
	
	public String toString() {
		return "BotListCacheManager: Cache Enabled=" + this.enableCaching;
	}

	/**
	 * @return the cacheStore
	 */
	public Map getCacheStore() {
		return cacheStore;
	}
}
