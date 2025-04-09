/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import org.spirit.bean.impl.BotListSessionRequestLog;


/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListSessionRequestLogDAO {
	
	public void createSessionLog(final BotListSessionRequestLog sess);
	
}
