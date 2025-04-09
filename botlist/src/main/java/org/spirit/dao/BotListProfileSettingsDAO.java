/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import org.spirit.bean.impl.BotListProfileSettings;
import org.springframework.dao.DataAccessException;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListProfileSettingsDAO {

	public BotListProfileSettings readProfile(final int id) throws DataAccessException;
	
}
