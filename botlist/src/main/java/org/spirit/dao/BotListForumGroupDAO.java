/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.List;

import org.spirit.bean.impl.BotListForumGroup;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListForumGroupDAO {
	
	public List listForums(final String queryStr);
	public BotListForumGroup readForum(final int id);
	
}
