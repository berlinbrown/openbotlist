/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import org.spirit.bean.impl.BotListCoreUsers;
import org.springframework.dao.DataAccessException;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListCoreUsersDAO {
	
	public BotListCoreUsers readUserAcctVerify(final String userName, final String acctNumber) throws DataAccessException;
	public BotListCoreUsers readUserId(final Long userId) throws DataAccessException;
	
}
