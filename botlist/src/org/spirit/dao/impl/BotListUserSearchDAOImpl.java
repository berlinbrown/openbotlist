/**
 * Berlin Brown
 * Dec 24, 2006
 */
package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.dao.BotListUserSearchDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListUserSearchDAOImpl 
	extends HibernateDaoSupport implements BotListUserSearchDAO {

	/**
	 * @see org.spirit.dao.BotListUserSearchDAO#list(java.lang.String)
	 */
	public List list(final String searchQueryStr) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery("from org.spirit.bean.impl.BotListEntityLinks link where (link.urlTitle like '%" +  searchQueryStr + "%' OR link.keywords like '%" + searchQueryStr + "%') order by link.id desc");

						// Set the maximum results
						query.setMaxResults(400);
						return query.list();
					}
				});				
	}
	

}
