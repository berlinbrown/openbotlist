/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import org.spirit.bean.impl.BotListUserVisitLog;


/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListUserVisitLogDAO  {
	
	public long getVisitAuditCount();
	public long getUniqueVisitAuditCount();
	public void createVisitLog(BotListUserVisitLog link);	
	
}
