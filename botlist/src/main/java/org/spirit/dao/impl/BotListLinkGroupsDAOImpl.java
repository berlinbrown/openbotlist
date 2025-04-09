/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListLinkGroups;
import org.spirit.dao.BotListLinkGroupsDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListLinkGroupsDAOImpl extends HibernateDaoSupport implements BotListLinkGroupsDAO {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public BotListLinkGroups readLinkGroup(final String genid) throws DataAccessException {			
		return (BotListLinkGroups) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListLinkGroups group where group.generatedId = :genid");
						query.setString("genid", genid);
						return query.uniqueResult();
					}
				});		
	}	
	
}
