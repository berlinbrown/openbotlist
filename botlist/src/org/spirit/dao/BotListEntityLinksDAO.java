/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.Calendar;
import java.util.List;

import org.spirit.bean.impl.BotListEntityLinks;
import org.springframework.dao.DataAccessException;


/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public interface BotListEntityLinksDAO  {

	public List listEntityLinks(final String queryStr);
	public List pageEntityLinks(final String queryStr, final int page, final int pageSize);
	public List pageEntityLinksUsers(final String queryStr, final int page, final int pageSize);
	
	public List readListingOnDate(final Calendar calendar);
	public List readListingUpToCurrentDate(final Calendar calendar);
	
	public BotListEntityLinks readLinkListing(final int id) throws DataAccessException;
	public BotListEntityLinks readLinkListing(final String id) throws DataAccessException;
	public long getLinkCommentCount(final int id);
	public long getLinkCount();
	public long getLinkCommentCountByForum(final int id);
}
