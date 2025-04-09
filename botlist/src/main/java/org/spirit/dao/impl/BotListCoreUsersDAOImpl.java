/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.dao.BotListCoreUsersDAO;
import org.spirit.util.text.KeywordProcessor;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListCoreUsersDAOImpl extends HibernateDaoSupport implements BotListCoreUsersDAO {

	/**
	 * @see org.spirit.dao.BotListCityListingDAO#readCityListing(int)
	 */
	public BotListCoreUsers readUser(final String userName) throws DataAccessException {			
		return (BotListCoreUsers) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListCoreUsers user where user.userName = :userName");
						String finUser = KeywordProcessor.filterAlphaNumeric(userName);
						query.setString("userName", finUser);
						return query.uniqueResult();
					}
				});
	}
	/**
	 * Read the user object as well as lazy initialize entity links
	 * @param userName
	 * @return
	 * @throws DataAccessException
	 */
	public BotListCoreUsers readUserGetLinks(final String userName) throws DataAccessException {			
		return (BotListCoreUsers) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListCoreUsers user where user.userName = :userName order by user.id desc LIMIT 0, 50");
						String finUser = KeywordProcessor.filterAlphaNumeric(userName);
						query.setString("userName", finUser);
						query.setMaxResults(50);
						BotListCoreUsers user = (BotListCoreUsers) query.uniqueResult();						
						List l = user.getLinks();						
						// lazy load the link
						for (Iterator it = l.iterator(); it.hasNext(); ) {
							Object obj = it.next();
							// Note: obj does not have to be used
						}
						return user;
					}
				});
	}
	public BotListCoreUsers readUserId(final Long userId) throws DataAccessException {			
		return (BotListCoreUsers) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListCoreUsers user where user.id = :userId");						
						query.setLong("userId", userId.longValue());
						return query.uniqueResult();
					}
				});
	}
	/**
	 * Find the user object based on user name and account number.
	 * 
	 * @param userName
	 * @return
	 * @throws DataAccessException
	 */
	public BotListCoreUsers readUserAcctVerify(final String userName, final String acctNumber) throws DataAccessException {			
		return (BotListCoreUsers) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListCoreUsers user where user.userName = :userName and user.accountNumber = :acctNumber");
						String finUser = KeywordProcessor.filterAlphaNumeric(userName);
						query.setString("userName", finUser);
						query.setString("acctNumber", acctNumber);
						return query.uniqueResult();
					}
				});
	}
	public long getCoreUsersCount() {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListCoreUsers");
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
}
