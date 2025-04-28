/*
 * Created on Nov 6, 2006
 * 
 */
package org.spirit.dao;

import java.util.List;

import org.spirit.bean.impl.BotListPostListing;

/**
 */
public interface BotListPostListingDAO {
	
	public void createPostListing(BotListPostListing listing);
	
	public List listPostListings(final String queryStr);

}
