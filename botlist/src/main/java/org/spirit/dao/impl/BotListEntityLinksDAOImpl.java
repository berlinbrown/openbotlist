/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.bean.impl.BotListEntityLinks;
import org.spirit.dao.BotListCoreUsersDAO;
import org.spirit.dao.BotListEntityLinksDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 */
public class BotListEntityLinksDAOImpl 
	extends HibernateDaoSupport implements BotListEntityLinksDAO {
	
	public static final int MAX_DAO_RESULTS = 400; 
	public static final int MAX_MAX_RESULTS = 2000;
	public static final int MAX_RESULTS_DAY = 8;
		
	private BotListCoreUsersDAO userDao;
	
	/**
	 * @see org.spirit.dao.BotListUserLinkDAO#createLink(org.spirit.bean.impl.BotListUserLink)
	 */
	public void createLink(BotListEntityLinks link) {
		getHibernateTemplate().saveOrUpdate(link);
	}
	
	/**
	 * @see org.spirit.dao.BotListEntityLinksDAO#listEntityLinks(java.lang.String)
	 */
	public List listEntityLinks(final String queryStr) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);

						// Set the maximum results
						query.setMaxResults(MAX_DAO_RESULTS);
						List data = query.list();						
						return data;
					}
				});		
	} 
	
	/**
	 * When iterating through the entity links, do any preperations on the link
	 * before they are displayed to the user.
	 */
	private void processListEntityLinks(BotListEntityLinks link) {
		
		// Ensure a non-lazy load of the entity links.		
		List comments = link.getListings();														
		comments.size();
		
		// Get the core username for use with mapping entity
		// links to user.
		if (link.getUserId() != null) {
			// Link saved based on user id
			// Get core user object.
			if (this.userDao != null) {
				BotListCoreUsers coreUser = this.userDao.readUserId(link.getUserId());
				link.setCoreUsername(coreUser.getUserName());
			}
		} else {
			// Ensure that the core user name is null.
			link.setCoreUsername(null);
		}
	}
	
	/**
	 * List the Entity Links including paging the results.
	 */
	public List pageEntityLinks(final String queryStr, final int page, final int pageSize) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);
						
						// Set the maximum results
						query.setFirstResult(page * pageSize);						
						query.setMaxResults(pageSize);
						List data = query.list();
						for (Iterator it = data.iterator(); it.hasNext();) {
							BotListEntityLinks link = (BotListEntityLinks) it.next();
							processListEntityLinks(link);
						}
						return data;
					}
				});		
	}
	
	/**
	 * List the Entity Links including paging the results, but avoid the lazy initialization
	 * of unneeded data and only load user data.
	 */
	public List pageEntityLinksUsers(final String queryStr, final int page, final int pageSize) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);
						
						// Set the maximum results
						query.setFirstResult(page * pageSize);						
						query.setMaxResults(pageSize);
						
						List data = query.list();
						//for (Iterator it = data.iterator(); it.hasNext();) {
						//	BotListEntityLinks link = (BotListEntityLinks) it.next();
						//	processListEntityLinks(link);
						//}
						return data;
					}
				});		
	}
		
	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public BotListEntityLinks readLinkListing(final int id) throws DataAccessException {			
		return (BotListEntityLinks) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListEntityLinks link where link.id = " + id);
						return query.uniqueResult();
					}
				});
	}
	
	public BotListEntityLinks readLinkListing(final String id) throws DataAccessException {
		String sId = id != null ? id.trim() : "";
		int _id = Integer.parseInt(sId);
		return readLinkListing(_id);
	}
	
	/**
	 * Get the posts on a particular date.
	 */
	public List readListingOnDate(final Calendar calendar) {			
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 							
							session.createQuery("from org.spirit.bean.impl.BotListEntityLinks links where date(links.createdOn) = date(:curDate)");						
						query.setMaxResults(MAX_RESULTS_DAY);
						query.setCalendar("curDate", calendar); 
						return query.list();
					}
				});		
	}

	public List readListingUpToCurrentDate(final Calendar calendar) {			
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 							
							session.createQuery("from org.spirit.bean.impl.BotListEntityLinks links where (date(links.createdOn) > date(:curDate)) AND (links.createdOn < NOW())");						
						query.setMaxResults(MAX_MAX_RESULTS);
						query.setCalendar("curDate", calendar); 
						return query.list();
					}
				});		
	}

	
	public long getLinkCommentCount(final int id) {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListUserComments where linkId = " + id);
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
	public long getLinkCommentCountByForum(final int id) {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListUserComments where forumId = " + id);
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
	/**
	 * @see org.spirit.dao.BotListEntityLinksDAO#getLinkCount()
	 */
	public long getLinkCount() {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListEntityLinks");
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}

	public BotListCoreUsersDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(BotListCoreUsersDAO userDao) {
		this.userDao = userDao;
	}	

}
