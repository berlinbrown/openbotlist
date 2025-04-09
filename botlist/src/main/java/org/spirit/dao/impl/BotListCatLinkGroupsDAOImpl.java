/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListCatLinkGroups;
import org.spirit.dao.BotListCatLinkGroupsDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown 
 */

public class BotListCatLinkGroupsDAOImpl extends HibernateDaoSupport implements BotListCatLinkGroupsDAO {
	
	/**
	 * List the Entity Links including paging the results
	 */
	public BotListCatLinkGroups readGroup(final String group) {
		return (BotListCatLinkGroups) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery("from org.spirit.bean.impl.BotListCatLinkGroups groups where groups.categoryName = :group");
						query.setMaxResults(1);
						query.setString("group", group);											
						BotListCatLinkGroups groups = (BotListCatLinkGroups) query.uniqueResult();
						if (groups != null) {
							List list = groups.getTerms();
							// Ensure lazy - load is initialized
							for (Iterator it = list.iterator(); it.hasNext();) {
								it.next();
							}
						}
						return groups;
					}
				});		
	}
	public List readGroups() {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery("from org.spirit.bean.impl.BotListCatLinkGroups groups");
						query.setMaxResults(100);																						
						return query.list();
					}
				});		
	}
	
	public List listTerms(final BotListCatLinkGroups group) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery("from org.spirit.bean.impl.BotListCatGroupTerms terms where terms.category_name = :group");						
						query.setParameter("group", group);											
						return query.list();
					}
				});		
	}
	
	
}
