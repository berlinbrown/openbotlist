/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 19, 2007
 */
package org.spirit.cache;

import java.io.File;
import java.util.Date;

import org.springframework.context.ApplicationContext;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListCacheController {

	/**
	 * Time in seconds on when to reload cache between 
	 * when file is last modified and what is stored in the cache entity.
	 */
	public static final long CACHE_FREE_TIME = 14;
	
	private ApplicationContext context;
	
	private boolean enableCaching = false;
	private BotListCacheManager manager;
	
	/**
	 * @return the context
	 */
	public ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * Create Cache Entity.
	 */
	public static BotListCacheEntity createCacheEntity(String filename, String rubyCode) {		
		File rFile = new File(filename);
		BotListCacheEntity cacheObj = new BotListCacheEntity();
		cacheObj.setFilenameKey(filename);
		cacheObj.setRubyCodeData(rubyCode);
		cacheObj.setTimeAddedCache(new Date());				
		cacheObj.setLastModified(new Date(rFile.lastModified()));
		return cacheObj;
	}
	
	/**
	 * @param context the context to set
	 */
	public void setContext(ApplicationContext context) {
		this.context = context;		
		// Once the context is set, also set the cache enabled flag, driven
		// by the spring config file.
		if (this.context != null) {
			manager = (BotListCacheManager) this.context.getBean("cacheManager");
			if (manager != null) {
				enableCaching = manager.isEnableCaching();
			}
		}
	}

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
		return "BotListCacheController: =" + manager; 
	}

	/**
	 * @return the manager
	 */
	public BotListCacheManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(BotListCacheManager manager) {
		this.manager = manager;
	}
}
