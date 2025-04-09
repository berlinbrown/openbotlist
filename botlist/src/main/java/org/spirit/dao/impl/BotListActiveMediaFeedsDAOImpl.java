/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.dao.BotListActiveMediaFeedsDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public class BotListActiveMediaFeedsDAOImpl extends HibernateDaoSupport implements BotListActiveMediaFeedsDAO {
	
	public List readActiveMediaList(final String type, final int maxFeeds) throws DataAccessException {		
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						String queryStr = "from BotListActiveMediaFeeds where display_type = :type";						
						Query query = session.createQuery(queryStr);
						query.setString("type", type);
						query.setMaxResults(maxFeeds);
						List data = query.list();						
						return data;
					}
				});		
	}
}
