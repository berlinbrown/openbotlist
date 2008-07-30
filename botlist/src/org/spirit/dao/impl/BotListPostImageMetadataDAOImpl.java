/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.dao.BotListPostImageMetadataDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListPostImageMetadataDAOImpl extends HibernateDaoSupport implements BotListPostImageMetadataDAO {
	
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public List readPostListingImages(final int id) throws DataAccessException {			
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListPostImageMetadata image where image.adlistId = " + id);						
						return query.list();
					}
				});		
	}
	
}
