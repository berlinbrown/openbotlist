/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.dao.BotListSearchQueryFiltersDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListSearchQueryFiltersDAOImpl 
	extends HibernateDaoSupport implements BotListSearchQueryFiltersDAO {
	
	public final static int MAX_TOPIC_RESULTS = 5;
		
	public List listHotTopics() {
		return this.listHotTopics(MAX_TOPIC_RESULTS);
	}
	
	public List listHotTopics(final int maxResults) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery("from org.spirit.bean.impl.BotListSearchQueryFilters topic order by topic.id desc");
						// Set the maximum results
						query.setMaxResults(maxResults);
						List data = query.list();						
						return data;
					}
				});		
	} 

}
