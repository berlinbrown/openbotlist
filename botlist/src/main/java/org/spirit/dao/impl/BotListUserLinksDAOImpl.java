/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListCoreUsers;
import org.spirit.dao.BotListUserLinksDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserLinksDAOImpl extends HibernateDaoSupport implements BotListUserLinksDAO {

	public List listUserLinks(final BotListCoreUsers coreUser) throws DataAccessException {			
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListUserLinks links where links.userId = :userId");		
						query.setLong("userId", coreUser.getId().longValue());
						return query.list();
					}
				});
	}
	
}
