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
import org.hibernate.criterion.Expression;
import org.spirit.bean.impl.BotListCityListing;
import org.spirit.bean.impl.BotListPostListing;
import org.spirit.bean.impl.BotListPostSections;
import org.spirit.dao.BotListPostSectionsDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListPostSectionsDAOImpl extends HibernateDaoSupport implements BotListPostSectionsDAO {

	/**
	 * @see org.spirit.dao.BotListPostSectionsDAO#listSections(java.lang.String)
	 */
	public List listSections(final String queryStr, final BotListCityListing city) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);
						// Set the maximum results
						query.setMaxResults(400);
						List data = query.list();
						for (Iterator it = data.iterator(); it.hasNext();) {
							// Sections have "listings", check the number of listings per section.
							BotListPostSections section = (BotListPostSections) it.next();							
							//List listings = section.getListings();
							List curdata =  session.createCriteria(BotListPostListing.class)
								.add(Expression.eq("cityParent", city)).add(Expression.eq("sectionParent", section)).list();
							curdata.size();
							section.setListings(curdata);
							
						}
						return query.list();
					}
				});		
	} 
	
	public BotListPostSections readSection(final String id) throws DataAccessException {			
		return (BotListPostSections) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListPostSections section where section.generatedId = '" + id + "'");
						return query.uniqueResult();
					}
				});		
	}


}
