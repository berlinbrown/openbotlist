/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import org.spirit.bean.impl.BotListSessionRequestLog;
import org.spirit.dao.BotListSessionRequestLogDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListSessionRequestLogDAOImpl extends HibernateDaoSupport implements BotListSessionRequestLogDAO {

	public void createSessionLog(final BotListSessionRequestLog sess) {
		getHibernateTemplate().save(sess);
	}
	
}	
