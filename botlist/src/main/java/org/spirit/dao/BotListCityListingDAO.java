/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.io.Serializable;
import java.util.Collection;

import org.spirit.bean.impl.BotListCityListing;
import org.springframework.dao.DataAccessException;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListCityListingDAO extends Serializable {
	
	public Collection findCityListings();
	
	public BotListCityListing readCityListing(int id) throws
		DataAccessException;
	
	public long getCityPostCount(final int id);
	
}
