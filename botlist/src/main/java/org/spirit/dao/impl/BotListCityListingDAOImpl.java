/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListCityListing;
import org.spirit.dao.BotListCityListingDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListCityListingDAOImpl extends HibernateDaoSupport 
	implements BotListCityListingDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5404767492071334422L;

	private Log log = LogFactory.getLog(getClass());
	
	private final String DEFAULT_QUERY = "from org.spirit.bean.impl.BotListCityListing city order by city.cityName";	
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#findWebFiles()
	 */
	public Collection findCityListings(final String query_str) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(query_str);						
						// Set the maximum results						
						query.setMaxResults(50);
						List data = query.list();
						for (Iterator it = data.iterator(); it.hasNext(); ) {
							BotListCityListing element = (BotListCityListing) it.next();
							List curdata = element.getListings();									
							curdata.size();							
					    }
						return data;
						
					}
				});	
	}
	
	public Collection findCityListings() {
		return findCityListings(DEFAULT_QUERY);
	}
	
	/**
	 * Find city listings by type.
	 * 
	 * @return
	 */
	public Collection findCityListingsType(final String city_type, final int page, final int pageSize) {		
			return getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session) throws HibernateException {
							Query query = session.createQuery("from org.spirit.bean.impl.BotListCityListing city where city.cityCategory = :city_type order by city.stateAbbr, city.cityName");
							query.setString("city_type", city_type);
							// Set the maximum results
							query.setFirstResult(page * pageSize);						
							query.setMaxResults(pageSize);
							List data = query.list();
							for (Iterator it = data.iterator(); it.hasNext(); ) {								
								BotListCityListing element = (BotListCityListing) it.next();
								List curdata = element.getListings();									
								curdata.size();							
						    }
							return data;
							
						}
					});			
	}
	
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public BotListCityListing readCityListing(final int id) throws DataAccessException {		
		return (BotListCityListing) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListCityListing city where city.id = " + id);
						return query.uniqueResult();
					}
				});		
	}	
	
	public long getCityPostCount(final int id) {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListPostListing where cityId = " + id);
		if (list == null)
			return -1;
		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
}
