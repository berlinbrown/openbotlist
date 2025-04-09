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

public interface BotListSearchQueryFiltersDAO {
	public List listHotTopics();
	public List listHotTopics(final int maxResults);
}
