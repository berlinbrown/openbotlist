/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListActiveMediaFeedsDAO {

	public List readActiveMediaList(final String type, final int maxFeeds) throws DataAccessException;
	
}
