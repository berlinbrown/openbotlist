/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListGroupLinks;
import org.spirit.dao.BotListGroupLinksDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */
public class BotListGroupLinksDAOImpl extends HibernateDaoSupport implements BotListGroupLinksDAO {

	public void createGroupLink(BotListGroupLinks link) {
		getHibernateTemplate().save(link);
	}

	/**
	 * List the Entity Links including paging the results
	 */
	public List pageGroupLinks(final String queryStr, final int page, final int pageSize, final int groupId) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);

						// Set the maximum results
						query.setFirstResult(page * pageSize);						
						query.setMaxResults(pageSize);
						query.setInteger("groupId", groupId);
						return query.list();
					}
				});		
	}
	
	/**
	 * @see org.spirit.dao.BotListEntityLinksDAO#getLinkCount()
	 */
	public long getLinkCount(final int groupId) {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListGroupLinks where groupId = " + groupId);		
		if (list == null)
			return -1;
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}	
}
