/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListForumGroup;
import org.spirit.dao.BotListForumGroupDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListForumGroupDAOImpl extends HibernateDaoSupport implements BotListForumGroupDAO {
	
	/**
	 * @see org.spirit.dao.BotListEntityLinksDAO#listEntityLinks(java.lang.String)
	 */
	public List listForums(final String queryStr) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);

						// Set the maximum results
						query.setMaxResults(400);
						return query.list();
					}
				});		
	} 
	
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public BotListForumGroup readForum(final int id) 
				throws DataAccessException {			
		return (BotListForumGroup) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListForumGroup forum where forum.id = " + id);
						return query.uniqueResult();
					}
				});		
	}
	
}
