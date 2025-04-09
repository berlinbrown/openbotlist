/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.List;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListUserCommentsDAO {
	
	public List listComments(final String queryStr);
	
}
