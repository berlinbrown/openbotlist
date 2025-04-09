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
import org.spirit.bean.impl.BotListUserComments;
import org.spirit.dao.BotListUserCommentsDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserCommentsDAOImpl 
	extends HibernateDaoSupport implements BotListUserCommentsDAO {

	/**
	 * @see org.spirit.dao.BotListPostListingDAO#listPostListings()
	 */
	public List listComments(final String queryStr) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);
						// Set the maximum results
						query.setMaxResults(140);
						List data = query.list();
						for (Iterator it = data.iterator(); it.hasNext();) {
							BotListUserComments comment = (BotListUserComments) it.next();
							List child_comments = comment.getChildComments();
							child_comments.size();
						}
						return data;
					}
				});
	}
	
	public List listLinkComments(final String queryStr, final int maxResults) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = session.createQuery(queryStr);
						// Set the maximum results
						query.setMaxResults(maxResults);
						List data = query.list();					
						return data;
					}
				});
	}
	
	public BotListUserComments readComment(final int id) throws DataAccessException {
		// Fetch the comments and any other reply comments
		return (BotListUserComments) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListUserComments comment left outer join fetch comment.childComments where comment.id = :commentId");
						query.setInteger("commentId", id);
						return query.uniqueResult();
					}
				});		
	}
	
}
