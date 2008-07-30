/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import org.spirit.bean.impl.BotListAdminMainBanner;
import org.springframework.dao.DataAccessException;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListAdminMainBannerDAO {

	public BotListAdminMainBanner readBanner(final String section) throws DataAccessException;
}
